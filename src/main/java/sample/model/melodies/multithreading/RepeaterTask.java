package sample.model.melodies.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bartek on 09.11.2017.
 */
public class RepeaterTask implements Callable<Object> {
    private Command<Integer> command;
    private int nTimes;
    private ReentrantLock pauseLock;
    private Condition unPaused;
    private boolean isPaused;

    public RepeaterTask(Command<Integer> command, int nTimes){
        this.command = command;
        this.nTimes = nTimes;
        pauseLock = new ReentrantLock();
        isPaused = false;
        unPaused = pauseLock.newCondition();
    }

    @Override
    public Object call() throws Exception {
        for(int i = 0; i < nTimes; i++){
            pauseLock.lock();
            try {
                while (isPaused) {
                    unPaused.await();
                }
            } finally {
                pauseLock.unlock();
            }
            command.execute(i);
        }
        return null;
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
