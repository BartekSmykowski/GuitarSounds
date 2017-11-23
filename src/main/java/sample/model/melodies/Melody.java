package sample.model.melodies;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sample.model.neckModel.Neck;

import java.util.ArrayList;
import java.util.List;

public class Melody {

    private Neck neck;
    private ArrayList<MultiSound> multiSounds;
    private IntegerProperty length;

    public Melody(Neck neck){
        this(neck, new ArrayList<>());
    }

    public Melody(Neck neck, ArrayList<MultiSound> multiSounds){
        this.neck = neck;
        this.multiSounds = multiSounds;
        length = new SimpleIntegerProperty(multiSounds.size());
    }

    public void addParallelSounds(List<NeckCoords> coords){
        MultiSound sound = new MultiSound(coords, neck);
        multiSounds.add(sound);
        length.set(length.getValue() + 1);
    }

    public ArrayList<MultiSound> getMultiSounds(){
        return multiSounds;
    }

    public int getLenght(){
        return length.getValue();
    }
}
