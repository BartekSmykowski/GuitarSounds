package sample.model.chords;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import sample.model.neckModel.Neck;
import sample.model.neckModel.Sound;
import sample.model.neckModel.SoundOnNeck;
import sample.model.neckModel.SoundsNames;

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
        button.setOnMouseClicked(event ->{
            neck.getSounds().forEach(stringSounds -> stringSounds.forEach(this::highlightIfIncluded));
            play();
        });
    }

    private void play() {
        for(SoundsNames sound : includedSounds){
            new Sound(sound).playSound();
        }
    }

    private void highlightIfIncluded(SoundOnNeck stringSound) {
        for (SoundsNames sound : includedSounds) {
            if (stringSound.getName().equals(sound)) {
                stringSound.highlight();
                return;
            }
        }
        stringSound.unhighlight();
    }

    public Button getButton(){
        return button;
    }

    public ArrayList<SoundsNames> getIncludedSoundsNames(){
        return includedSounds;
    }

    public ArrayList<Integer> getPattern(){
        return pattern;
    }

    public SoundsNames getFirstSound(){
        return firsSound;
    }

    public String getType(){
        return type.get();
    }

    public StringProperty typeProperty(){
        return type;
    }
}
