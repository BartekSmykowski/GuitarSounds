package sample.model.melodies;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bartek on 30.10.2017.
 */
public class MelodyPlayer {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Melody melody;
    private Future future;
    private Callable<Void> task;
    private ReentrantLock pauseLock;
    private Condition unPaused;

    public MelodyPlayer(Melody melody){
        this.melody = melody;
        pauseLock = new ReentrantLock();
        unPaused = pauseLock.newCondition();
        task = () -> {
            try {
                melody.play();
            } catch (InterruptedException ignored) {
                melody.stop();
            }
            return null;
        };
    }

    public void play(){
        future = executorService.submit(task);
    }

    public void stop(){
        future.cancel(true);
    }

    public void pause() {
        pauseLock.lock();
        try {
            melody.pause();
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume(){
        pauseLock.lock();
        try {
            melody.resume();
            unPaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

    public Melody getMelody() {
        return melody;
    }

}
