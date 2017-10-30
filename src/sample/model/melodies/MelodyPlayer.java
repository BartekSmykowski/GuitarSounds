package sample.model.melodies;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bartek on 30.10.2017.
 */
public class MelodyPlayer extends Timer {

    private Melody melody;

    public MelodyPlayer(Melody melody){
        this.melody = melody;
    }

    public void play(){
        super.schedule(new TimerTask() {
            @Override
            public void run() {
                melody.play();
            }
        }, 0);
    }
}
