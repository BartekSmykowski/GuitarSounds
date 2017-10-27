package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by Bartek on 20.10.2017.
 */
public class Sound {
    private SoundsNames name;
    private IntegerProperty octave;
    private Media media;

    public Sound(SoundsNames name, int octave){
        this.name = name;
        this.octave = new SimpleIntegerProperty(octave);
        File soundFile = new File("src/resources/" + name.toString() + ".mp3");
        media = new Media(soundFile.toURI().toString());
    }

    public Sound(SoundsNames name){
        this(name, 0);
    }

    public void playSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public SoundsNames getName(){
        return name;
    }

    public int getOctave(){
        return octave.get();
    }


}
