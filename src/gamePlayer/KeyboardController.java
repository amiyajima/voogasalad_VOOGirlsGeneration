package gamePlayer;
import gamedata.action.Action;  
import gameengine.player.Player;
import java.util.Map;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;




public class KeyboardController {
    Player myCurrentPlayer;
    Point2D myCurrentLocation;
    //starting action with the keyboard
    
    public void setActionKeyControl(Map<KeyCode, Action> actionKeyMap, Scene gameScene) {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
    public void setMovementKeyControl(Map<KeyCode, Point2D> movementKeyMap, Scene gameScene) {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Set<KeyCode> movementKeyList = movementKeyMap.keySet();
            @Override
            public void handle (KeyEvent key) {
               for (KeyCode kc: movementKeyList){
                   if (key.getCode() == kc) {
//                       getting something like {0,1}, {-1,1}, etc
                       myCurrentLocation = new Point2D(myCurrentLocation.getX() + movementKeyMap.get(kc).getX(),
                                                       myCurrentLocation.getY() + movementKeyMap.get(kc).getY());
                       System.out.println("key pressed");
                   }
               }
            }
        });
        
    }
}

