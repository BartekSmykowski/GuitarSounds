package sample.model;

import java.util.ArrayList;

/**
 * Created by Bartek on 20.10.2017.
 */
public enum SoundsNames {
    C, Cis, D, Dis, E,  F, Fis, G, Gis, A, B, H;

    public static ArrayList<Sound> getStandardGuitarSounds(){
        ArrayList<Sound> sounds = new ArrayList<>(6);
        sounds.add(new Sound(E, 0));
        sounds.add(new Sound(A, 0));
        sounds.add(new Sound(D, 1));
        sounds.add(new Sound(G, 1));
        sounds.add(new Sound(H, 1));
        sounds.add(new Sound(E, 2));
        return sounds;
    }

    public static ArrayList<Sound> getStandardUculeleSounds(){
        ArrayList<Sound> sounds = new ArrayList<>(6);
        sounds.add(new Sound(G, 0));
        sounds.add(new Sound(C, 0));
        sounds.add(new Sound(E, 1));
        sounds.add(new Sound(A, 1));
        return sounds;
    }
}
