package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Bartek on 20.10.2017.
 */
public class Sound {
    private SoundsNames name;
    private IntegerProperty octave;

    public Sound(SoundsNames name, int octave){
        this.name = name;
        this.octave = new SimpleIntegerProperty(octave);
    }

    public SoundsNames getName(){
        return name;
    }

    public int getOctave(){
        return octave.get();
    }


}
