package sample.model.chordGrab;

import sample.model.chords.Chord;
import sample.model.neckModel.Neck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bartek on 27.10.2017.
 */
public class ChordGrab {
    private List<Integer> indicesOnStrings;
    private Chord chord;
    private Neck neck;
    private double grade;

    public ChordGrab(ChordGrab source){
        this(source.chord, source.neck);
        this.indicesOnStrings.clear();
        this.indicesOnStrings.addAll(source.indicesOnStrings);
        grade = ChordGrabEstimator.estimate(this);
    }

    public ChordGrab(List<Integer> indices, Chord chord, Neck neck){
        this.chord = chord;
        this.neck = neck;
        indicesOnStrings = indices;
        makeFirst();
        grade = ChordGrabEstimator.estimate(this);
    }

    public ChordGrab(Chord chord, Neck neck){
        this(new ArrayList<>(Collections.nCopies(neck.getNumberOfStrings(), 0)), chord, neck);
    }

    private void makeFirst() {
        for(int string = 0; string < neck.getNumberOfStrings(); string++){
            searchSoundOnString(string);
        }
        highlight();
    }

    private void searchSoundOnString(int string) {
        int fret = 0;
        while(!isSoundFound(string, fret)){
            fret++;
        }
        indicesOnStrings.set(string, fret);
    }

    private boolean isSoundFound(int string, int fret) {
        return chord.getIncludedSoundsNames().contains(neck.getString(string).getSound(fret).getName());
    }

    public void highlight(){
        for(int i = 0; i < neck.getNumberOfStrings(); i++){
            for(int j = 0; j < neck.getString(i).getNumberOfSounds(); j++){
                if(indicesOnStrings.get(i) == j)
                    neck.getString(i).getSound(j).highlight();
                else
                    neck.getString(i).getSound(j).unhighlight();
            }
        }
    }

    public boolean makeNext(){
        boolean isLast = false;
        boolean foundNext = false;
        int string = 0;
        while(!foundNext && string < neck.getNumberOfStrings()){
            int nextIndexOnString = findNextSoundIndexOnString(string, indicesOnStrings.get(string));
            if(nextIndexOnString == -1){
                if(string == neck.getNumberOfStrings() - 1){
                    isLast = true;
                }
                searchSoundOnString(string);
            } else {
                indicesOnStrings.set(string, nextIndexOnString);
                foundNext = true;
            }
            string++;
        }
        grade = ChordGrabEstimator.estimate(this);
        return !isLast;
    }

    private int findNextSoundIndexOnString(int string, int fret){
        int index = fret + 1;
        int stringsLength = neck.getStringsLength();
        while(index < stringsLength && !isSoundFound(string, index)){
            index++;
        }
        if(index >= stringsLength)
            index = -1;
        return index;
    }

    public List<Integer> getIndicesOnStrings(){
        return indicesOnStrings;
    }

    public double getGrade() {
        return grade;
    }

    public Neck getNeck(){
        return neck;
    }
}
