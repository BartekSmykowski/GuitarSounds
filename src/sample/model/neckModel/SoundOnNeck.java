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
        isHighlighted = false;
        changeHighlight();
    }

    public void changeHighlight(){
        if(isHighlighted){
            unhighlight();
        } else {
            highlight();
        }
    }

    public void highlight(){
        isHighlighted = true;
        button.setId(getName().toString() + "Button");
    }

    public void unhighlight(){
        isHighlighted = false;
        button.setId("DefaultButton");
    }

    public Button getButton(){
        return button;
    }

    public boolean isHighlighted(){
        return isHighlighted;
    }
}
