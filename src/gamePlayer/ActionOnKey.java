package gamePlayer;

import gamedata.action.Action;
import gameengine.player.HumanPlayer;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

// probably not going to use this class
// TODO: refactor keyboard controller
// needs to specify what piece to perform the action on.!

/**
 *TODO:Explain what this is...
 * @author 
 *
 */
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
