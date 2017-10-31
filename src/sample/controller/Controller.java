package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.model.neckModel.Neck;
import sample.model.neckModel.SoundOnNeck;
import sample.model.neckModel.SoundsNames;
import sample.model.chordGrab.ChordGrab;
import sample.model.chords.Chord;
import sample.model.chords.ChordsFactory;
import sample.model.melodies.Melody;
import sample.model.melodies.MelodyPlayer;
import sample.model.tabulature.TabLoader;

import java.util.*;
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
        List<List<SoundOnNeck>> sounds = neck.getSounds();
        VBox vBox = new VBox();
        for(int i = sounds.size() - 1; i >= 0; i--){
            HBox hBox = new HBox();
            hBox.getChildren().addAll(sounds.get(i).stream().map(SoundOnNeck::getButton).collect(Collectors.toList()));
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
        Button button = getLightAllButton();
        vBox.getChildren().add(button);

        addChordButtons(SoundsNames.A, "Mol", vBox);
        addChordButtons(SoundsNames.G, "Mol", vBox);
        addChordButtons(SoundsNames.D, "Dur", vBox);
        addChordButtons(SoundsNames.C, "Dur", vBox);

        Button melodyButton = new Button("Play melody.");
        melodyButton.setOnMouseClicked(event -> {
            TabLoader tabLoader = new TabLoader("src/resources/tabulatury/gnrCivilWar.txt", neck);
            Melody melody = tabLoader.getMelody();
            MelodyPlayer melodyPlayer = new MelodyPlayer(melody);
            melodyPlayer.play();
        });
        vBox.getChildren().add(melodyButton);

        chordsButtonsPane.getChildren().add(vBox);
    }

    private void addChordButtons(SoundsNames name, String type, VBox vBox){
        HBox hBox = new HBox();
        Chord chord = ChordsFactory.produce(name, type, neck);
        hBox.getChildren().add(chord.getButton());

        ChordGrab grab = new ChordGrab(chord, neck);
        hBox.getChildren().add(grab.getButton());
        vBox.getChildren().add(hBox);
    }

    private Button getLightAllButton() {
        Chord chord2 = new Chord(SoundsNames.C, new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11)), neck);
        Button button = chord2.getButton();
        button.textProperty().unbind();
        button.setText("Light all.");
        return button;
    }

}
