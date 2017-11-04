package sample.model.chordGrab;

import javafx.scene.control.Button;
import sample.model.neckModel.Neck;
import sample.model.chords.Chord;

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
    private Button button;

    public ChordGrab(Chord chord, Neck neck){
        this.chord = chord;
        this.neck = neck;
        indicesOnStrings = new ArrayList<>(Collections.nCopies(neck.getNumberOfStrings(), 0));
        makeFirst();
        makeBest();
        initButton();
    }

    private void initButton() {
        button = new Button("Find best: " + chord.getFirstSound() + " " + chord.getType());
        button.getStyleClass().add("ChordGrab");
        button.setOnMouseClicked(event -> highlightGrab());
    }

    private void makeFirst() {
        for(int string = 0; string < neck.getNumberOfStrings(); string++){
            searchSoundOnString(string);
        }
        highlightGrab();
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

    public void highlightGrab(){
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
        boolean foundNext = false;
        boolean isLast = false;
        int string = 0;
        while(!foundNext && string < neck.getNumberOfStrings()){
            int fret = indicesOnStrings.get(string) + 1;
            searchSoundOnString(string);
            while(!foundNext && fret < neck.getString(string).getNumberOfSounds()){
                if(isSoundFound(string, fret)) {
                    indicesOnStrings.set(string, fret);
                    foundNext = true;
                }
                if(isLastTry(string, fret)){
                    isLast = true;
                }
                fret++;
            }
            string++;
        }
        highlightGrab();
        return isLast;
    }

    private boolean isLastTry(int string, int fret) {
        return string == neck.getNumberOfStrings() - 1 && fret == neck.getString(string).getNumberOfSounds() - 1;
    }

    public void makeBest(){
        int bestGrade;
        List<Integer> bestIndices = new ArrayList<>(Collections.nCopies(neck.getNumberOfStrings(), 0));
        makeFirst();
        bestGrade = estimate();
        bestIndices.clear();
        bestIndices.addAll(indicesOnStrings);
        boolean isLast = false;
        while(!isLast){
            isLast = makeNext();
            int tmpGrade = estimate();
            if(tmpGrade > bestGrade) {
                bestGrade = tmpGrade;
                bestIndices.clear();
                bestIndices.addAll(indicesOnStrings);
            }
        }
        indicesOnStrings = bestIndices;
        highlightGrab();
    }

    public int estimate(){
        int grade = 0;
        int maxDist = 0;
        for(int index : indicesOnStrings){
            for(int indexCompare: indicesOnStrings){
                if(index != 0 && indexCompare != 0) {
                    int dist = Math.abs(index - indexCompare);
                    if (dist > maxDist) {
                        maxDist = dist;
                    }
                }
            }
        }

        grade -= maxDist;
        return grade;
    }

    public List getIndicesOnStrings(){
        return indicesOnStrings;
    }

    public Button getButton(){
        return button;
    }
}
