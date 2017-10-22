package sample.model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
        button = new Button(name.toString() + octave);
        EventHandler<MouseEvent> mouseEventEventHandler = event -> {
            File soundFile = new File("src/resources/" + name.toString() + ".mp3");
            Media media = new Media(soundFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        };
        button.setOnMouseClicked(mouseEventEventHandler);
        isHighlighted = false;
        changeHighlight();
    }

    public void changeHighlight(){
        if(isHighlighted){
            unhighlight();
        } else {
            highlight();
        }
    }

    public void highlight(){
        isHighlighted = true;
        button.setId(getName().toString() + "Button");
    }

    public void unhighlight(){
        isHighlighted = false;
        button.setId("DefaultButton");
    }

    public Button getButton(){
        return button;
    }

    public boolean isHighlighted(){
        return isHighlighted;
    }
}
