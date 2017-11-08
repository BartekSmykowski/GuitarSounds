package sample.model.chordGrab;

import java.util.Comparator;

/**
 * Created by Bartek on 08.11.2017.
 */
public class ChordGrabsComparator implements Comparator<ChordGrab> {
    @Override
    public int compare(ChordGrab o1, ChordGrab o2) {
        return (int) (o2.getGrade() - o1.getGrade());
    }
}
