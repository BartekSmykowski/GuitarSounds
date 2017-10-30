package sample.model.melodies;

import sample.model.Neck;

import java.util.ArrayList;

/**
 * Created by Bartek on 30.10.2017.
 */
public class MultiSound {

    private ArrayList<NeckCoords> coords;
    private Neck neck;

    public MultiSound(ArrayList<NeckCoords> coords, Neck neck){

        this.coords = coords;
        this.neck = neck;
    }

    public void play(){
        for(NeckCoords coords: coords){
            neck.playSound(coords);
        }
    }

}
