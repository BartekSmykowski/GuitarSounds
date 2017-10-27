package sample.model.chordGrab;

import sample.model.Neck;
import sample.model.chords.Chord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartek on 27.10.2017.
 */
public class ChordGrab {
    private List<Integer> indicesOnStrings;
    private Chord chord;
    private Neck neck;

    public ChordGrab(Chord chord, Neck neck){
        this.chord = chord;
        this.neck = neck;
        indicesOnStrings = new ArrayList<>(neck.getNumberOfStrings());
        makeNext();
    }

    public void makeNext(){
        for(int i = 0; i < neck.getNumberOfStrings(); i++){
            boolean soundNotFound = true;
            int j = indicesOnStrings.get(i);
            while(soundNotFound){
                neck.getString(i).getSounds
            }
        }
    }

    public int estimate(){
        return 0;
    }

    public List getIndicesOnStrings(){
        return indicesOnStrings;
    }
}
