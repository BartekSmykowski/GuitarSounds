package sample.model.melodies;

import sample.model.neckModel.Neck;

import java.util.ArrayList;
import java.util.List;

public class Melody {

    private Neck neck;
    private ArrayList<MultiSound> multiSounds;
    private double frequency = 20;

    public Melody(Neck neck){
        this(neck, new ArrayList<>());
    }

    public Melody(Neck neck, ArrayList<MultiSound> multiSounds){
        this.neck = neck;
        this.multiSounds = multiSounds;
    }

    public void play(){
        for(MultiSound sound : multiSounds){
            sound.play();
            trySleep((int) (1000/frequency));
        }
    }

    private void trySleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addParallelSounds(List<NeckCoords> coords){
        MultiSound sound = new MultiSound(coords, neck);
        multiSounds.add(sound);
    }

    public void addSound(NeckCoords coord){
        ArrayList<NeckCoords> coords = new ArrayList<>();
        coords.add(coord);
        MultiSound sound = new MultiSound(coords, neck);
        multiSounds.add(sound);
    }

    public void addBreak(){
        ArrayList<NeckCoords> coords = new ArrayList<>();
        MultiSound sound = new MultiSound(coords, neck);
        multiSounds.add(sound);
    }

    public void setFrequency(double frequency){
        this.frequency = frequency;
    }

}
