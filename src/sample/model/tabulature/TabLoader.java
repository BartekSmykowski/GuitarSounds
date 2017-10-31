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
            if(!line.equals("")){
                counter++;
                stringsLines.add(line);
            }
            if(counter == neck.getNumberOfStrings()){
                counter = 0;
                processStringLines(stringsLines);
                stringsLines.clear();
            }

        }

    }

    private void processStringLines(List<String> stringsLines) {
        for(int i = 0; i < stringsLines.get(0).length(); i++){
            List<NeckCoords> coords = new ArrayList<>();
            for(int string = 0; string < 6; string++){
                char sign = stringsLines.get(string).charAt(i);
                if(sign != '-'){
                    coords.add(new NeckCoords(string, Integer.parseInt(sign + "")));
                }
            }
            melody.addParallelSounds(coords);
        }
    }


    public Melody getMelody() {
        return melody;
    }
}
