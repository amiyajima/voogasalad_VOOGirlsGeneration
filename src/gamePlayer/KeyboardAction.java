package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gameengine.player.HumanPlayer;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardAction {
    HumanPlayer myCurrentPlayer;
    Point2D myCurrentLocation;
    GameGridEffect myGameGridEffect;
    
    /**
     * 
     * Action map (which maps Actions to KeyCodes) is passed by the game engine.
     * This method creates key event handlers so that when such a keycode is
     * pressed, its corresponding action is implemented.
     * 
     * @param actionKeyMap maps actions to keycodes
     * @param gameScene is the scene for GUI
     */

    // private void setActionKeyControl(Player p){
    // Map<KeyCode, Action> actionKeyMap = p.getActionKeyMap();
    // myController.getGrid().setOnKeyPressed(event->bindActionKey(event, actionKeyMap));
    // }
    // private void bindActionKey(KeyEvent ke, Map<KeyCode, Action> actionKeyMap){
    // actionKeyMap.keySet().forEach(kc->{if(ke.getCode().equals(kc)){
    //
    // }
    // });
    // }

    public void setActionKeyControl (GameGrid grid, Game game) {
        myCurrentPlayer = (HumanPlayer) game.getCurrentPlayer();
        Map<KeyCode, Action> actionKeyMap = myCurrentPlayer.getActionKeyMap();
        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Set<KeyCode> actionKeyList = actionKeyMap.keySet();

            @Override
            public void handle (KeyEvent key) {
                for (KeyCode kc : actionKeyList) {
                    if (key.getCode() == kc) {
                        // do the corresponding action
                        // actionKeyMap.get(kc);
                    }
                }
            }
        });
    }
}
