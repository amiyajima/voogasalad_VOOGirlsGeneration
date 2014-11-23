package gamePlayer;

import gamedata.action.Action;  
import gamedata.gamecomponents.Game;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

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
   
    public void setActionKeyControl(GameGrid grid, Game game) {
        myCurrentPlayer = game.getCurrentPlayer();
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
    public void setMovementKeyControl(ViewController vc, GameGrid grid, Game game) {
        myCurrentLocation = new Point2D.Double(0.0,0.0);
//        Map<KeyCode, Point2D> movementKeyMap = myCurrentPlayer.getMovementKeyMap();
      
      //for testing!!!
      Map<KeyCode, Point2D> movementKeyMap = new HashMap<KeyCode, Point2D>();
      movementKeyMap.put(KeyCode.A, new Point2D.Double(-1.0,0.0));
      movementKeyMap.put(KeyCode.D, new Point2D.Double(1.0,0.0));
      movementKeyMap.put(KeyCode.W, new Point2D.Double(0.0,1.0));
      movementKeyMap.put(KeyCode.S, new Point2D.Double(0.0,-1.0));
      
        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Set<KeyCode> movementKeyList = movementKeyMap.keySet();
            @Override
            public void handle (KeyEvent key) {
               for (KeyCode kc: movementKeyList){
                   if (key.getCode() == kc) {
//                       getting something like {0,1}, {-1,1}, etc
                       
                       Point2D newCurrentLocation = new Point2D.Double(myCurrentLocation.getX() + movementKeyMap.get(kc).getX(),
                                                       myCurrentLocation.getY() + movementKeyMap.get(kc).getY()); 
                       System.out.println("wtf");
                       vc.highlightCurrentLocation(Color.BLUE, myCurrentLocation, newCurrentLocation);
//                       myCurrentLocation = newCurrentLocation;
                   }
               }
            }
        });
    }
}

