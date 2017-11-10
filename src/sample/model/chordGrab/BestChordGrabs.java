package sample.model.chordGrab;

import javafx.scene.control.Button;
import sample.model.chords.Chord;
import sample.model.neckModel.Neck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartek on 08.11.2017.
 */
public class BestChordGrabs {
    private List<ChordGrab> bestGrabs;
    private Chord chord;
    private Neck neck;
    private Button button;
    private int highlightIndex;
    private static final int MAX_GRABS = 10;

    public BestChordGrabs(Chord chord, Neck neck){
        this.chord = chord;
        this.neck = neck;
        highlightIndex = 0;
        bestGrabs = new ArrayList<>();
        findBestGrabs();
        initButton();
    }

    private void initButton() {
        button = new Button("Find best: " + chord.getFirstSound() + " " + chord.getType());
        button.getStyleClass().add("ChordGrab");
        button.setOnMouseClicked(event -> {
            bestGrabs.get(highlightIndex).highlightGrab();
            System.out.println(highlightIndex + " " + bestGrabs.get(highlightIndex).getGrade());
            highlightIndex++;
            highlightIndex %= MAX_GRABS;
        });
    }

    private void findBestGrabs() {
        ChordGrab grab = new ChordGrab(chord, neck);
        while(grab.makeNext()){
            addGrab(new ChordGrab(grab));
        }
    }

    private void addGrab(ChordGrab grab){
        bestGrabs.add(grab);
        bestGrabs.sort(new ChordGrabsComparator());
        if(bestGrabs.size() > MAX_GRABS){
            bestGrabs.remove(bestGrabs.size() - 1);
        }
    }

    public Button getButton(){
        return button;
    }

}
