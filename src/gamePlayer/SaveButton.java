package gamePlayer;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import GameEngine.GameModel;

public class SaveButton extends ControlLabel{
    
    public static final String SAVE_GAME_TEXT = "Save Game";
    
    public SaveButton (GameModel model, Stage stage, Image i) {
        super(model, stage, i, SAVE_GAME_TEXT);
    }

    @Override
    protected void performOnClick () {
        getModel().save();
    }
}
