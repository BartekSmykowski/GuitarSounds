package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Bartek on 20.10.2017.
 */
public class Neck {
    private ObservableMap<Integer, MusicString> guitarStrings = FXCollections.observableHashMap();
    private int numberOfStrings;

    public Neck(ArrayList<Sound> firstSounds, int dlugoscStrun){
        int i = 0;
        for(Sound sound : firstSounds){
            guitarStrings.put(i, new MusicString(dlugoscStrun, sound));
            i++;
        }
        numberOfStrings = guitarStrings.size();
    }

    public Neck(ArrayList<Sound> firstSounds){
        this(firstSounds, 19);
    }

    public Neck(){
        this(SoundsNames.getStandardGuitarSounds(), 19);
    }

    public Collection<Collection<SoundOnNeck>> getSounds(){
        Collection<Collection<SoundOnNeck>> sounds = new ArrayList<>();
        for(MusicString string : guitarStrings.values()){
            sounds.add(string.getSounds());
        }
        return sounds;
    }

    public Collection getStringSounds(int i){
        return guitarStrings.get(i).getSounds();
    }

    public MusicString getString(int i){
        return guitarStrings.get(i);
    }

    public Collection<MusicString> getStrings(){
        return guitarStrings.values();
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }
}
