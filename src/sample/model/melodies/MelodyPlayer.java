package sample.model.melodies;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
    private IntegerProperty progress;
    private DoubleProperty percentageProgress;
    private ReentrantLock pauseLock;
    private Condition unPaused;
    private boolean isPaused;
    private ReentrantLock speedLock;
    private DoubleProperty speed;

    public MelodyPlayer(Melody melody) {
        this.melody = melody;
        pauseLock = new ReentrantLock();
        speedLock = new ReentrantLock();
        speed = new SimpleDoubleProperty(5);
        isPaused = false;
        progress = new SimpleIntegerProperty(0);
        percentageProgress = new SimpleDoubleProperty(0);
        unPaused = pauseLock.newCondition();
        task = () -> {
            try {
                playMelody();
            } catch (InterruptedException ignored) {
                stopMelody();
            }
            return null;
        };
    }

    private void stopMelody() {
        percentageProgress.set(0);
        progress.set(0);
    }

    private void playMelody() throws InterruptedException {
        isPaused = false;
        int i = 0;
        for(MultiSound sound : melody.getMultiSounds()){
            pauseLock.lock();
            try {
                while (isPaused) {
                    unPaused.await();
                }
            } finally {
                pauseLock.unlock();
            }
            Platform.runLater(sound::play);
            speedLock.lock();
            try {
                int sleepTime = (int) (1000 / speed.getValue());
                Thread.sleep(sleepTime);
            } finally {
                speedLock.unlock();
            }
            i++;
            percentageProgress.setValue((double)i/melody.getLenght());
        }
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

    public Melody getMelody() {
        return melody;
    }

    public void setSpeed(double speed){
        speedLock.lock();
        this.speed.setValue(speed);
        speedLock.unlock();
    }

    public DoubleProperty percentageProgressProperty(){
        return percentageProgress;
    }

    public DoubleProperty speedProperty() {
        return speed;
    }
}
