package sample.model.melodies;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import sample.model.melodies.multithreading.PausePlayExecutor;
import sample.model.melodies.multithreading.RepeaterTask;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bartek on 30.10.2017.
 */
public class MelodyPlayer {
    private PausePlayExecutor executor;
    private Melody melody;
    private DoubleProperty percentageProgress;
    private ReentrantLock speedLock;
    private DoubleProperty speed;

    public MelodyPlayer(Melody melody) {
        this.melody = melody;
        speedLock = new ReentrantLock();
        speed = new SimpleDoubleProperty(5);
        percentageProgress = new SimpleDoubleProperty(0);
        PlayingMelodySoundTask playingTask = new PlayingMelodySoundTask(this);
        RepeaterTask repeaterTask = new RepeaterTask(playingTask, melody.getLenght());
        executor = new PausePlayExecutor(repeaterTask);
    }

    public void play() {
        executor.play();
    }

    public void stop() {
        executor.stop();
        percentageProgress.set(0);
    }

    public void pause() {
        executor.pause();
    }

    public void resume(){
        executor.resume();
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

    public double getSpeed() {
        speedLock.lock();
        double speedRet = speed.get();
        speedLock.unlock();
        return speedRet;
    }
}
