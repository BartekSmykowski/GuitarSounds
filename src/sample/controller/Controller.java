package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import sample.model.chordGrab.ChordGrab;
import sample.model.chords.Chord;
import sample.model.chords.ChordsFactory;
import sample.model.melodies.Melody;
import sample.model.melodies.MelodyPlayer;
import sample.model.neckModel.Neck;
import sample.model.neckModel.SoundOnNeck;
import sample.model.neckModel.SoundsNames;
import sample.model.tabulature.TabLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Controller {
    public Pane menuButtonsPane;
    public Pane neckPane;
    public Pane chordsButtonsPane;
    public Pane tabChoosePane;
    public Label tabNameLabel;
    public Button tabChooseButton;
    public Button playTabButton;
    public TextField frequencyTextField;
    public ProgressBar melodyProgressBar;
    public Button stopButton;
    public Button pauseButton;
    public Button resumeButton;
    private Neck neck;
    private MelodyPlayer melodyPlayer;

    @FXML
    public void initialize(){
        neck = new Neck();
        melodyPlayer = new MelodyPlayer(new Melody(neck));
        addNeckButtons();
        addMenuButtons();
        addChords();
    }

    private void addNeckButtons() {
        List<List<SoundOnNeck>> sounds = neck.getSounds();
        VBox vBox = new VBox();

        HBox fretNumbers = new HBox();
        Stream<Integer> intStream = IntStream.range(0, neck.getStringsLength()).boxed();
        List<Label> labels = intStream.map(n -> {
            Label label = new Label(n + "");
            label.getStyleClass().add("neck-label");
            return label;
        }).collect(Collectors.toList());
        fretNumbers.getChildren().addAll(labels);
        vBox.getChildren().add(fretNumbers);

        for(int i = neck.getNumberOfStrings() - 1; i >= 0; i--){
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
            button.getStyleClass().add("menu-button");
            button.getStyleClass().add(sound.toString() + "Button");
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

    public void openTabChooser(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(tabChoosePane.getScene().getWindow());
        if (selectedFile != null) {
            tabNameLabel.setText(selectedFile.getName());
            TabLoader tabLoader = new TabLoader(selectedFile.getPath(), neck);
            Melody melody = tabLoader.getMelody();
            melodyPlayer = new MelodyPlayer(melody);
            melodyProgressBar.progressProperty().bind(melodyPlayer.percentageProgressProperty());
        }
    }

    public void playLoadedTabulature(MouseEvent mouseEvent) {
        melodyPlayer.setFrequency(Double.parseDouble(frequencyTextField.getText()));
        melodyPlayer.play();
    }

    public void stopPlaying(MouseEvent mouseEvent) {
        melodyPlayer.stop();
    }

    public void resumeClicked(MouseEvent mouseEvent) {
        melodyPlayer.resume();
    }

    public void pauseClicked(MouseEvent mouseEvent) {
        melodyPlayer.pause();
    }
}
