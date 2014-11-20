package gamePlayer;
import gamedata.action.Action;
import gameengine.player.Player;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardController {
    Player myCurrentPlayer;
    
    //starting action with the keyboard
    public void setActionKeyControl(Map<KeyCode, Action> ActionKeyMap, Scene gameScene) {
//        myKeyMap = just get it from Player... getKeyMap()
////        for (KeyCode keyCode:keyMap.keySet()){
////            gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        }
    
    
    //"moving the cursor" with the keyboard
    public void setMovementKeyControl(Map<KeyCode, Point2D> MovementKeyMap, Scene gameScene) {
        
    }
}

