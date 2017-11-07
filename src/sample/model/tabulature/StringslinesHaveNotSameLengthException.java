package sample.model.tabulature;

import java.util.List;

/**
 * Created by Bartek on 04.11.2017.
 */
public class StringslinesHaveNotSameLengthException extends RuntimeException{
    public StringslinesHaveNotSameLengthException(List<String> stringsLines){
        super(stringsLines.toString());
        stringsLines.forEach(System.out::println);
    }
}
