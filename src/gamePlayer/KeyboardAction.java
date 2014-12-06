package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gameengine.player.HumanPlayer;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardAction {
    HumanPlayer myCurrentPlayer;
    Point2D myCurrentLocation;
    GameGridEffect myGameGridEffect;
//    Piece myPiece;
    List<Action> myActions;
//    Action myCurrentAction;
    int myActionIndex;
    
    
    public void setActionKeyControl(ScrollPane sp, Piece p){
        System.out.println("KeyboardAction ON");
        myActions = p.getActions();
        myActionIndex = 0;
        
        //using the movement key map to determine which keys=up&down
////        myCurrentPlayer = do something with p.getPlayerID() to get the HumanPlayer?;
////         Map<KeyCode, Point2D> movementKeyMap = myCurrentPlayer.getMovementKeyMap();

        //for testing!!!
        Map<KeyCode, Point2D> movementKeyMap = new HashMap<KeyCode, Point2D>();
        movementKeyMap.put(KeyCode.A, new Point2D.Double(-1.0, 0.0));
        movementKeyMap.put(KeyCode.D, new Point2D.Double(1.0, 0.0));
        movementKeyMap.put(KeyCode.W, new Point2D.Double(0.0, 1.0));
        movementKeyMap.put(KeyCode.S, new Point2D.Double(0.0, -1.0));
        
        sp.requestFocus();
        sp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Set<KeyCode> movementKeyList = movementKeyMap.keySet();
            @Override
            public void handle (KeyEvent key) {
                
                //Do action here
                if (key.getCode() == KeyCode.F) {
//                    vc.performActionKeyboard(myCurrentLocation);
//                    performAction(myActions.get(myActionIndex));
                    
                    System.out.println("Selected action with key");
                }
                
                //Select action here
                for (KeyCode kc : movementKeyList) {
                    if (key.getCode() == kc) {
                        if ((myActionIndex != 0) & (myActionIndex != myActions.size()-1)){
                            myActionIndex = myActionIndex + (int)movementKeyMap.get(key).getY();
                        }
                    }
                }
                System.out.println("myActionIndex: " + myActionIndex);
             }
            });
        
        
        }
    
//    // Doesn't make sense to get ActionKeyMap from the player anymore...since each piece has diff actions.
//    /**
//     * 
//     * Action map (which maps Actions to KeyCodes) is passed by the game engine.
//     * This method creates key event handlers so that when such a keycode is
//     * pressed, its corresponding action is implemented.
//     * 
//     * @param actionKeyMap maps actions to keycodes
//     * @param gameScene is the scene for GUI
//     */
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
    
//    public void setActionKeyControl (GameGrid grid, Game game) {
//        
//        myCurrentPlayer = (HumanPlayer) game.getCurrentPlayer();
//        Map<KeyCode, Action> actionKeyMap = myCurrentPlayer.getActionKeyMap();
//        
//        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            Set<KeyCode> actionKeyList = actionKeyMap.keySet();
//
//            @Override
//            public void handle (KeyEvent key) {
//                for (KeyCode kc : actionKeyList) {
//                    if (key.getCode() == kc) {
//                        // do the corresponding action
//                        // actionKeyMap.get(kc);
//                    }
//                }
//            }
//        });
//    }
}
