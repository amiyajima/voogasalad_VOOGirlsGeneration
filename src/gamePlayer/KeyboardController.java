package gamePlayer;
import gamedata.action.Action;
import gameengine.player.Player;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyboardController {
//    Map<KeyCode, Action> myKeyMap;
    Player myCurrentPlayer;
    
    public void setKeyboardControl(Map<KeyCode, Action> myKeyMap, Scene myGameScene){
        
//        myKeyMap = just get it from Player... getKeyMap()
        for (KeyCode key:myKeyMap.keySet()){

        }
    }
}
