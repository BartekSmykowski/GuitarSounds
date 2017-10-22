package sample.model;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by Bartek on 22.10.2017.
 */
public class SoundOnNeck extends Sound {
    private boolean isHighlighted;
    private Button button;


    public SoundOnNeck(SoundsNames name, int octave) {
        super(name, octave);
        button = new Button(name.toString());
        button.setOnMouseClicked(event -> {
            File soundFile = new File("src/resources/" + name.toString() + ".mp3");
            Media media = new Media(soundFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        });
        isHighlighted = false;
        changeHighlight();
    }

    public void changeHighlight(){
        if(isHighlighted){
            isHighlighted = false;
            button.setId("DefaultButton");
        } else {
            isHighlighted = true;
            button.setId(getName().toString() + "Button");
        }
    }

    public Button getButton(){
        return button;
    }

    public boolean isHighlighted(){
        return isHighlighted;
    }
}
