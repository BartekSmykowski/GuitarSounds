package sample.model.melodies;

import sample.model.neckModel.Neck;

import java.util.List;

/**
 * Created by Bartek on 30.10.2017.
 */
public class MultiSound {

    private List<NeckCoords> coords;
    private Neck neck;

    public MultiSound(List<NeckCoords> coords, Neck neck){

        this.coords = coords;
        this.neck = neck;
    }

    public void play(){
        if(!coords.isEmpty()) {
            neck.unhightlightAll();
            for (NeckCoords coords : coords) {
                neck.playSound(coords);
            }
        }
    }

}
