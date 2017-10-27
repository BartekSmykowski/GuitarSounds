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
import sample.model.chords.ChordsFactory;

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
        Chord chordcmol = ChordsFactory.produce(SoundsNames.E, "Mol", neck);
        vBox.getChildren().add(chordcmol.getButton());
        Chord chordcdur = ChordsFactory.produce(SoundsNames.E, "7dur", neck);
        vBox.getChildren().add(chordcdur.getButton());
        Chord chordgdur = ChordsFactory.produce(SoundsNames.G, "7dur", neck);
        vBox.getChildren().add(chordgdur.getButton());
        Chord chordhmol = ChordsFactory.produce(SoundsNames.H, "mol", neck);
        vBox.getChildren().add(chordhmol.getButton());
        Chord chordhdur = ChordsFactory.produce(SoundsNames.H, "7dur", neck);
        vBox.getChildren().add(chordhdur.getButton());
        Chord adur = ChordsFactory.produce(SoundsNames.A, "dur", neck);
        vBox.getChildren().add(adur.getButton());
        chordsButtonsPane.getChildren().add(vBox);
    }

}
