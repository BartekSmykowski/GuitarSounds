package sample.model.melodies.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Bartek on 09.11.2017.
 */
public class PausePlayExecutor {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private RepeaterTask task;
    private Future future;

    public PausePlayExecutor(RepeaterTask task){
        this.task = task;
    }

    public void play() {
        future = executorService.submit(task);
    }

    public void stop() {
        future.cancel(true);
    }

    public void pause() {
        task.pause();
    }

    public void resume(){
        task.resume();
    }

}
