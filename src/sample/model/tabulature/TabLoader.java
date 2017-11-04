package sample.model.tabulature;

import sample.model.melodies.Melody;
import sample.model.melodies.NeckCoords;
import sample.model.neckModel.Neck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartek on 31.10.2017.
 */
public class TabLoader {
    private Path filePath;
    private Melody melody;
    private Neck neck;

    public TabLoader(String tabFilePath, Neck neck){
        filePath = Paths.get(tabFilePath);
        this.neck = neck;
        melody = new Melody(neck);

        List<String> lines = tryReadLines();
        processLines(lines);
    }

    private List<String> tryReadLines() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void processLines(List<String> lines){
        List<String> stringsLines = new ArrayList<>(neck.getNumberOfStrings());
        int counter = 0;
        for(String line: lines){
            if(isValidLine(line)){
                counter++;
                line = prepareLine(line);
                stringsLines.add(line);
            }
            if(counter == neck.getNumberOfStrings()){
                counter = 0;
                validLinesLength(stringsLines);
                processStringLines(stringsLines);
                stringsLines.clear();
            }
        }
    }

    private boolean isValidLine(String line) {
        boolean isValiLine = false;
        if(line.contains("-"))
            isValiLine = true;
        return isValiLine;
    }

    private String prepareLine(String line) {
        String preparedLine = line.replaceAll("[pbhs/()x^ ]", "-");
        preparedLine = preparedLine.replaceAll("[^\\d-]", "");
        return preparedLine;
    }

    private void processStringLines(List<String> stringsLines) {
        for(int i = 0; i < stringsLines.get(0).length(); i++){
            List<NeckCoords> coords = new ArrayList<>();
            for(int string = 0; string < neck.getNumberOfStrings(); string++){
                char sign = stringsLines.get(string).charAt(i);
                char nextSign = '-';
                char prevSign = '-';
                if(stringsLines.get(string).length() > i + 1)
                    nextSign = stringsLines.get(string).charAt(i+1);
                if(i > 0)
                    prevSign = stringsLines.get(string).charAt(i-1);
                if(sign != '-'){
                    if(nextSign !=  '-')
                        coords.add(new NeckCoords(string, Integer.parseInt("" + sign + nextSign)));
                    else if(prevSign == '-')
                        coords.add(new NeckCoords(string, Integer.parseInt(sign + "")));
                }
            }
            melody.addParallelSounds(coords);
        }
    }

    private void validLinesLength(List<String> stringsLines) {
        int lineLength = stringsLines.get(0).length();
        for(int i = 1; i < neck.getNumberOfStrings(); i++){
            int tmpLength = stringsLines.get(i).length();
            if(tmpLength != lineLength){
                throw new StringslinesHaveNotSameLengthException(tmpLength - lineLength + "");
            }
        }
    }


    public Melody getMelody() {
        return melody;
    }
}
