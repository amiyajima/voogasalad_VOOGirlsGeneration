package gamePlayer;

import gamedata.action.Action;  
import gamedata.gamecomponents.Game;
import gameengine.player.Player;

import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardController {
    Player myCurrentPlayer;
    Point2D myCurrentLocation;
    //starting action with the keyboard
    
    /**
     * 
     * Action map (which maps Actions to KeyCodes) is passed by the game engine.
     * This method creates key event handlers so that when such a keycode is
     * pressed, its corresponding action is implemented.
     * 
     * @param actionKeyMap maps actions to keycodes
     * @param gameScene is the scene for GUI
     */
   
    public void setActionKeyControl(SquareGameGrid grid, Game game) {
        
//        //for testing: 
//        myCurrentPlayer = getCurrentPlayer(game);
        
        myCurrentPlayer = game.getCurrentPlayer(); //this method will exist in Game later.
        Map<KeyCode, Action> actionKeyMap = myCurrentPlayer.getActionKeyMap();
        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Set<KeyCode> actionKeyList = actionKeyMap.keySet();
            @Override
            public void handle (KeyEvent key) {
                for (KeyCode kc: actionKeyList) {
                    if (key.getCode() == kc) {
                        //do the corresponding action
                        actionKeyMap.get(kc);
                    }
                }
            }    
        });
        }

    //"moving the cursor" with the keyboard
    /**
     * 
     * Movement map (which maps movements to keycodes) is passed by the game engine.
     * This method creates key event handlers so that when such a keycode is
     * pressed, its corresponding movement is implemented. Movement is "moving the
     * cursor" with the keyboard.
     * 
     * @param movementKeyMap
     * @param gameScene
     */
    public void setMovementKeyControl(SquareGameGrid grid, Game game) {
//      //for testing: 
//      myCurrentPlayer = getCurrentPlayer(game);
        
      myCurrentPlayer = game.getCurrentPlayer(); // this method will exist in Game later
        Map<KeyCode, Point2D> movementKeyMap = myCurrentPlayer.getMovementKeyMap();
        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Set<KeyCode> movementKeyList = movementKeyMap.keySet();
            @Override
            public void handle (KeyEvent key) {
               for (KeyCode kc: movementKeyList){
                   if (key.getCode() == kc) {
//                       getting something like {0,1}, {-1,1}, etc
<<<<<<< HEAD
                       myCurrentLocation = new Point2D.Double(myCurrentLocation.getX() + movementKeyMap.get(kc).getX(),
                                                       myCurrentLocation.getY() + movementKeyMap.get(kc).getY());
                       System.out.println("key pressed");
=======
                       myCurrentLocation = new Point2D(myCurrentLocation.getX() + movementKeyMap.get(kc).getX(),
                                                       myCurrentLocation.getY() + movementKeyMap.get(kc).getY()); 
>>>>>>> 2a1f75701e683330e4e0cc9f276350c9395fd242
                   }
               }
            }
        });
    }
    
//    // this is just for testing... will delete later.
//    public Player getCurrentPlayer(Game game){
//        Player testPlayer = new Player();
//        return testPlayer;
//    }
}

