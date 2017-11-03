package sample.model.neckModel;

import javafx.scene.control.Button;

/**
 * Created by Bartek on 22.10.2017.
 */
public class SoundOnNeck extends Sound {
    private boolean isHighlighted;
    private Button button;


    public SoundOnNeck(SoundsNames name, int octave) {
        super(name, octave);
        button = new Button(name.toString() + octave);
        button.setOnMouseClicked(event -> playSound());
        button.getStyleClass().add("neck-button");
        highlight();
    }

    public void changeHighlight(){
        if(isHighlighted){
            unhighlight();
        } else {
            highlight();
        }
    }

    public void highlight(){
        if(!isHighlighted) {
            isHighlighted = true;
            button.getStyleClass().add(getName().toString() + "Button");
        }
    }

    public void unhighlight(){
        if(isHighlighted) {
            isHighlighted = false;
            button.getStyleClass().remove(getName().toString() + "Button");
        }
    }

    public Button getButton(){
        return button;
    }

    public boolean isHighlighted(){
        return isHighlighted;
    }
}
