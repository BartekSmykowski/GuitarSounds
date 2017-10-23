package sample.model.chords;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import sample.model.Neck;
import sample.model.SoundsNames;

import javafx.beans.binding.*;
import java.util.ArrayList;

/**
 * Created by Bartek on 22.10.2017.
 */
public class Chord {
    private ArrayList<Integer> pattern;
    private SoundsNames firsSound;
    private ArrayList<SoundsNames> includedSounds;
    private StringProperty type;
    private Button button;
    private Neck neck;

    public Chord(SoundsNames firstSound, ArrayList<Integer> pattern, Neck neck){
        this.firsSound = firstSound;
        this.pattern = pattern;
        type = new SimpleStringProperty("Custom");
        this.neck = neck;
        includedSounds = new ArrayList<>();
        includedSounds.add(firstSound);
        for(Integer soundId : pattern){
            includedSounds.add(SoundsNames.values()[(soundId + firstSound.ordinal())%12]);
        }
        initButton();
    }

    private void initButton() {
        button = new Button("Akord: " + firsSound.toString() + " " + type);
        button.textProperty().bind(Bindings.concat("Chord: ", firsSound, " ", type));
        button.setId("Chord");
        button.setOnMouseClicked(event -> neck.getSounds().forEach(stringSounds -> stringSounds.forEach(stringSound -> {
            boolean contains = false;
            for(SoundsNames sound : includedSounds) {
                if (stringSound.getName().equals(sound)) {
                    contains = true;
                }
            }
            if(contains){
                stringSound.highlight();
            }else{
                stringSound.unhighlight();
            }
        })));
    }

    public Button getButton(){
        return button;
    }

    public ArrayList<SoundsNames> getIncludedSounds(){
        return includedSounds;
    }

    public ArrayList<Integer> getPattern(){
        return pattern;
    }

    public SoundsNames getFirstSound(){
        return firsSound;
    }

    public String getType(){
        return type.toString();
    }

    public StringProperty typeProperty(){
        return type;
    }
}