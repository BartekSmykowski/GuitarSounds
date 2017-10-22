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

    public Neck(ArrayList<Sound> firstSounds, int dlugoscStrun){
        int i = 0;
        for(Sound sound : firstSounds){
            guitarStrings.put(i, new MusicString(dlugoscStrun, sound));
            i++;
        }
    }

    public Neck(){
        this(SoundsNames.getStandardGuitarSounds(), 19);
    }

    public Collection<Collection<SoundOnNeck>> getSounds(){
        Collection<Collection<SoundOnNeck>> buttons = new ArrayList<>();
        for(MusicString string : guitarStrings.values()){
            buttons.add(string.getSounds());
        }
        return buttons;
    }

}
