package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.model.Neck;
import sample.model.SoundOnNeck;
import sample.model.SoundsNames;
import sample.model.chords.Chord;
import sample.model.chords.DurChord;
import sample.model.chords.MolChord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Controller {
    public Pane menuButtonsPane;
    public Pane neckPane;
    public Pane chordsButtonsPane;
    private Neck neck;

    @FXML
    public void initialize(){
        neck = new Neck();
        addNeckButtons();
        addMenuButtons();
        addChords();
    }

    private void addNeckButtons() {
        Collection<Collection<SoundOnNeck>> sounds = neck.getSounds();
        VBox vBox = new VBox();
        for(Collection<SoundOnNeck> stringSounds : sounds) {
            HBox hBox = new HBox();
            hBox.getChildren().addAll(stringSounds.stream().map(SoundOnNeck::getButton).collect(Collectors.toList()));
            vBox.getChildren().add(hBox);
        }
        neckPane.getChildren().add(vBox);
    }

    private void addMenuButtons(){
        VBox vBox = new VBox();
        for(SoundsNames sound : SoundsNames.values()){
            Button button = new Button(sound.toString());
            button.setId(sound.toString() + "Button");
            button.setOnMouseClicked(event -> neck.getSounds().forEach(stringSounds -> stringSounds.forEach(stringSound -> {
                if(stringSound.getName().equals(sound)){
                    stringSound.changeHighlight();
                }
            })));
            vBox.getChildren().add(button);
        }
        menuButtonsPane.getChildren().add(vBox);
    }

    private void addChords() {
        VBox vBox = new VBox();
        Chord chord2 = new Chord(SoundsNames.C, new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11)), neck);
        Button button = chord2.getButton();
        button.textProperty().unbind();
        button.setText("Light all.");
        vBox.getChildren().add(button);
        DurChord chordcmol = new DurChord(SoundsNames.C, neck);
        vBox.getChildren().add(chordcmol.getButton());
        MolChord chordcdur = new MolChord(SoundsNames.C, neck);
        vBox.getChildren().add(chordcdur.getButton());
        chordsButtonsPane.getChildren().add(vBox);
    }

}
