package sample.model.chords;

import sample.model.neckModel.Neck;
import sample.model.neckModel.SoundsNames;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bartek on 23.10.2017.
 */
public class MolChord extends Chord {

    public MolChord(SoundsNames firstSound, Neck neck) {
        super(firstSound, new ArrayList<>(Arrays.asList(0,3,7)), neck);
        typeProperty().setValue("Mol");
    }
}
