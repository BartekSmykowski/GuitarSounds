package sample.model.melodies;

import sample.model.Neck;

import java.util.ArrayList;

/**
 * Created by Bartek on 30.10.2017.
 */
public class Melody {

    private Neck neck;
    private ArrayList<MultiSound> multiSounds;

    public Melody(Neck neck){
        this(neck, new ArrayList<>());


        addSound(new NeckCoords(5, 0));
        addSound(new NeckCoords(2, 0));
        addSound(new NeckCoords(1, 0));
        addSound(new NeckCoords(0, 0));
        addSound(new NeckCoords(1, 0));
        addSound(new NeckCoords(2, 0));
        addSound(new NeckCoords(5, 0));
        addSound(new NeckCoords(2, 0));
        addSound(new NeckCoords(1, 0));
        addSound(new NeckCoords(0, 0));
        addSound(new NeckCoords(1, 0));
        addSound(new NeckCoords(2, 0));
        addSound(new NeckCoords(5, 0));
        addSound(new NeckCoords(2, 0));
        addSound(new NeckCoords(1, 0));
        addSound(new NeckCoords(0, 0));
        addSound(new NeckCoords(1, 0));
        addSound(new NeckCoords(2, 0));
    }

    public Melody(Neck neck, ArrayList<MultiSound> multiSounds){
        this.neck = neck;
        this.multiSounds = multiSounds;
    }

    public void play(){
        for(MultiSound sound : multiSounds){
            sound.play();
            trySleep(500);
        }
    }

    private void trySleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

}
