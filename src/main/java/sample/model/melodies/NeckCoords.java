package sample.model.melodies;

/**
 * Created by Bartek on 30.10.2017.
 */
public class NeckCoords {

    private int string;
    private int fret;

    public NeckCoords(int string, int fret){
        this.string = string;
        this.fret = fret;
    }


    public int getString() {
        return string;
    }

    public int getFret() {
        return fret;
    }
}
