package gamePlayer;

import javafx.stage.Stage;
import GameEngine.GameModel;

public class SettingButton extends GameButton {

    public static final String SETTING_TEXT = "settings";

    public SettingButton (GameModel model, Stage stage) {
        super(model, stage, SETTING_TEXT);
    }



    @Override
    protected void onClickAction () {
       
        //whether sound is on,
        // other possible settings....

    }

}
