package sample.model.melodies;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bartek on 09.11.2017.
 */
public class PausePlayExecutor {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ReentrantLock pauseLock;
    private Condition unPaused;
    private boolean isPaused;
    private Callable<Object> task;
    private Future future;

    private PausePlayExecutor(Callable<Object> task){
        this.task = task;
        pauseLock = new ReentrantLock();
        isPaused = false;
        unPaused = pauseLock.newCondition();
    }

    public void play() {
        future = executorService.submit(task);
    }

    public void stop() {
        future.cancel(true);
    }

    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume(){
        pauseLock.lock();
        try {
            isPaused = false;
            unPaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

}
