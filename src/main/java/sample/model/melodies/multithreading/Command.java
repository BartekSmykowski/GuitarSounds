package sample.model.melodies.multithreading;

/**
 * Created by Bartek on 09.11.2017.
 */
public interface Command<T> {
    void execute(T i) throws InterruptedException;
}
