package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.model.Neck;
import sample.model.SoundOnNeck;
import sample.model.SoundsNames;

import java.util.Collection;
import java.util.stream.Collectors;

public class Controller {
    public Pane menuButtonsPane;
    public Pane neckPane;
    private Neck neck;

    @FXML
    public void initialize(){
        neck = new Neck();
        Collection<Collection<SoundOnNeck>> sounds = neck.getSounds();
        VBox vBox = new VBox();
        for(Collection<SoundOnNeck> stringSounds : sounds) {
            HBox hBox = new HBox();
            hBox.getChildren().addAll(stringSounds.stream().map(SoundOnNeck::getButton).collect(Collectors.toList()));
            vBox.getChildren().add(hBox);
        }
        neckPane.getChildren().add(vBox);
        addMenuButtons();
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

}
