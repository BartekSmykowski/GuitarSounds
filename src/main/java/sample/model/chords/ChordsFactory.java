package sample.model.chords;

import sample.model.neckModel.Neck;
import sample.model.neckModel.SoundsNames;

/**
 * Created by Bartek on 23.10.2017.
 */
public class ChordsFactory {
    public static Chord produce(SoundsNames soundName, String type, Neck neck){
        if(type.equalsIgnoreCase("mol"))
            return new MolChord(soundName, neck);
        else if(type.equalsIgnoreCase("dur"))
            return new DurChord(soundName, neck);
        else if(type.equalsIgnoreCase("7dur") || type.equalsIgnoreCase("dur7"))
            return new Dur7Chord(soundName, neck);

        throw new NoSuchChordTypeException(type);
    }

    private static class NoSuchChordTypeException extends RuntimeException {
        public NoSuchChordTypeException(String type) {
            super(type);
        }
    }
}
