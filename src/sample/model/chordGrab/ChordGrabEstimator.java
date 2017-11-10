package sample.model.chordGrab;

import java.util.List;

/**
 * Created by Bartek on 10.11.2017.
 */
public class ChordGrabEstimator {

    public static double estimate(ChordGrab grab){
        double grade;

        List<Integer> indicesOnStrings = grab.getIndicesOnStrings();
        double maxDist = maxDistBetweenIndices(indicesOnStrings);
        double lastFret = lastFretPosition(indicesOnStrings);

        grade = (100-maxDist) + 10/lastFret;

        return grade;
    }

    private static double lastFretPosition(List<Integer> indices){
        double lastFretPosition = 0;
        for(int index : indices){
            if(index > lastFretPosition)
                lastFretPosition = index;
        }
        return lastFretPosition;
    }

    private static double maxDistBetweenIndices(List<Integer> indices){
        double maxDist = 0;
        for(int index : indices) {
            for (int indexCompare : indices) {
                if (index != 0 && indexCompare != 0) {
                    int dist = Math.abs(index - indexCompare);
                    if (dist > maxDist) {
                        maxDist = dist;
                    }
                }
            }
        }
        return maxDist;
    }

}
