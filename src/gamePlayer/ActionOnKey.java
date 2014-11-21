package gamePlayer;

import gamedata.action.Action;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

// TODO: refactor keyboard controller
// needs to specify what piece to perform the action on.!

public class ActionOnKey implements GridOnKeyActor{
    
    ViewController myController;
    Map<KeyCode, Action> myActions;
    
    public ActionOnKey(ViewController controller)
    {
        myController = controller;
        // myActions = get player from Game
        
    }
    @Override
    public void actOnKeyPress (KeyEvent keyEvent) {
        
        
    }
    
    

}
