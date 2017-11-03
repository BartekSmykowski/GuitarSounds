package sample.model.melodies;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sample.model.neckModel.Neck;

import java.util.ArrayList;
import java.util.List;

public class Melody {

    private Neck neck;
    private ArrayList<MultiSound> multiSounds;
    private boolean isPaused;
    private double frequency;
    private IntegerProperty length;
    private IntegerProperty progress;
    private DoubleProperty percentageProgress;

    public Melody(Neck neck){
        this(neck, new ArrayList<>());
    }

    public Melody(Neck neck, ArrayList<MultiSound> multiSounds){
        this.neck = neck;
        this.multiSounds = multiSounds;
        isPaused = false;
        frequency = 5;
        length = new SimpleIntegerProperty(multiSounds.size());
        progress = new SimpleIntegerProperty(0);
        percentageProgress = new SimpleDoubleProperty(0);
    }

    public void play() throws InterruptedException {
        int i = 0;
        for(MultiSound sound : multiSounds){
            while(isPaused){
                Thread.sleep(50);
            }
            sound.play();
            Thread.sleep((int) (1000/frequency));
            i++;
            progress.setValue(i);
            percentageProgress.setValue(progress.doubleValue()/length.doubleValue());
        }
    }

    public void pause(){
        isPaused = true;
    }

    public void resume(){
        isPaused = false;
    }

    private void trySleep(int time) throws InterruptedException {
    }

    public void addParallelSounds(List<NeckCoords> coords){
        MultiSound sound = new MultiSound(coords, neck);
        multiSounds.add(sound);
        length.set(length.getValue() + 1);
    }

    public void setFrequency(double frequency){
        this.frequency = frequency;
    }

    public DoubleProperty percentageProgressProperty(){
        return percentageProgress;
    }

    public void stop() {
        progress.set(0);
        percentageProgress.set(0);
    }
}
