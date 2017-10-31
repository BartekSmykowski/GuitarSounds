package sample.model.chords;

import sample.model.neckModel.Neck;
import sample.model.neckModel.SoundsNames;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bartek on 22.10.2017.
 */
public class DurChord extends Chord {

    public DurChord(SoundsNames firstSound, Neck neck) {
        super(firstSound, new ArrayList<>(Arrays.asList(0,4,7)), neck);
        typeProperty().setValue("Dur");
    }

}
