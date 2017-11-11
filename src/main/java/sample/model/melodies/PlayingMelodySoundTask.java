package sample.model.melodies;

import javafx.application.Platform;
import sample.model.melodies.multithreading.Command;

/**
 * Created by Bartek on 09.11.2017.
 */
public class PlayingMelodySoundTask implements Command<Integer> {

    private MelodyPlayer melodyPlayer;

    public PlayingMelodySoundTask(MelodyPlayer melodyPlayer){
        this.melodyPlayer = melodyPlayer;
    }

    @Override
    public void execute(Integer i) throws InterruptedException {
        Melody melody = melodyPlayer.getMelody();
        Platform.runLater(melody.getMultiSounds().get(i)::play);
        int sleepTime = (int) (1000 / melodyPlayer.getSpeed());
        Thread.sleep(sleepTime);
        melodyPlayer.percentageProgressProperty().set((double)i/melody.getLenght());
    }

}
