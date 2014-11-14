package gamePlayer;

import javafx.stage.Stage;
import GameEngine.GameModel;

public class EndTurnButton extends GameButton {
    
    public static final String END_TURN_TEXT = "End Turn";
    

    public EndTurnButton (GameModel model, Stage stage) {
        super(model, stage, END_TURN_TEXT);
    
    }

    @Override
    protected void onClickAction () {
       
       // getModel().playNextTurn();
       // graphically highlight active actors
        
        
    }
    

}
