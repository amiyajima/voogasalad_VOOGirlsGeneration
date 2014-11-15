package gamePlayer;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import GameEngine.GameModel;

public abstract class ControlLabel extends ViewComponent{
    
    private ImageView myImage;
    private String myControlString;

    public ControlLabel (GameModel model, Stage stage, Image i, String s) {
        super(model, stage);
        myImage = new ImageView(i);
        myControlString = s;
        
    }
    
    
    @Override
    protected Node setUpDisplay(){
        Label l = new Label(myControlString, myImage);
        l.setOnMouseClicked(event->performOnClick());
        return l;
        
    }
    
    protected abstract void performOnClick();
   

}
