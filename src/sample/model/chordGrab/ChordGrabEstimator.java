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
        double fingers = numberOfFingers(indicesOnStrings);

        grade = (100-maxDist) + 10/lastFret + 5/fingers;

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

    private static double firstFretPosition(List<Integer> indices){
        double firstFretPosition = indices.get(0);
        for(int index : indices){
            if(index < firstFretPosition)
                firstFretPosition = index;
        }
        return firstFretPosition;
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

    private static int numberOfFingers(List<Integer> indices){
        int fingers = 0;
        for(int index : indices){
            if(index != 0)
                fingers++;
        }
        if(fingers == indices.size()){
            double firstFretPosition = firstFretPosition(indices);
            for(int index : indices){
                if(index == firstFretPosition)
                    fingers--;
            }
            fingers++;
        }
        return fingers;
    }

}
