package sample.model.tabulature;

/**
 * Created by Bartek on 04.11.2017.
 */
public class StringslinesHaveNotSameLengthException extends RuntimeException{
    public StringslinesHaveNotSameLengthException(String difference){
        super(difference);
    }
}
