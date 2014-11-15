package gamePlayer;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import GameEngine.GameModel;

public class ToggleScoreLabel extends ControlLabel{
    
   // public static final String SCORE_IMAGE = 
    public static final String SCORE_TEXT = "Score";

    public ToggleScoreLabel (GameModel model, Stage stage) {
        super(model, stage, null, SCORE_TEXT);
        
        
    }

    @Override
    protected void performOnClick () {
        setChanged();
        notifyObservers(this);
        
    }
    

}
