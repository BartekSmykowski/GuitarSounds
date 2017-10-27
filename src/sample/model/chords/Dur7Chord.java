package sample.model.chords;

import sample.model.Neck;
import sample.model.SoundsNames;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bartek on 26.10.2017.
 */
public class Dur7Chord extends Chord {
    public Dur7Chord(SoundsNames firstSound, Neck neck) {
        super(firstSound, new ArrayList<>(Arrays.asList(0,4,7, 10)), neck);
        typeProperty().setValue("7Dur");
    }
}
