package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gameengine.player.HumanPlayer;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import authoring_environment.GUIGrid;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

//probably won't use this class later?
public class KeyboardController {
    HumanPlayer myCurrentPlayer;
    Point2D myCurrentLocation;
    GameGridEffect myGameGridEffect;
    
     public KeyboardController(){
//         setActionKeyControl(vc.getGame().getCurrentPlayer());
     }

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

//    /**
//     * 
//     * Movement map (which maps movements to keycodes) is passed by the game engine.
//     * This method creates key event handlers so that when such a keycode is
//     * pressed, its corresponding movement is implemented. Movement is "moving the
//     * cursor" with the keyboard.
//     * 
//     * @param movementKeyMap
//     * @param gameScene
//     */
//    public void setMovementKeyControl (ViewController vc, GUIGrid grid, Game game) {
//        myCurrentLocation = new Point2D.Double(0.0, 0.0);
//        // Map<KeyCode, Point2D> movementKeyMap = myCurrentPlayer.getMovementKeyMap();
//        System.out.println("movement key control added");
//
//        // for testing!!!
//        Map<KeyCode, Point2D> movementKeyMap = new HashMap<KeyCode, Point2D>();
//        movementKeyMap.put(KeyCode.A, new Point2D.Double(-1.0, 0.0));
//        movementKeyMap.put(KeyCode.D, new Point2D.Double(1.0, 0.0));
//        movementKeyMap.put(KeyCode.W, new Point2D.Double(0.0, 1.0));
//        movementKeyMap.put(KeyCode.S, new Point2D.Double(0.0, -1.0));
//
//        grid.requestFocus();
//        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//            Set<KeyCode> movementKeyList = movementKeyMap.keySet();
//
//            @Override
//            public void handle (KeyEvent key) {
//                if (key.getCode() == KeyCode.F) {
//                    System.out.println(myCurrentLocation);
//                    vc.performActionKeyboard(myCurrentLocation);
//                }
//
//                for (KeyCode kc : movementKeyList) {
//                    if (key.getCode() == kc) {
//                        Point2D newCurrentLocation =
//                                new Point2D.Double(myCurrentLocation.getX() -
//                                                   movementKeyMap.get(kc).getY(),
//                                                   myCurrentLocation.getY() +
//                                                           movementKeyMap.get(kc).getX());
//
//                        if ((newCurrentLocation.getX() > grid.r - 1) |
//                            (newCurrentLocation.getY() > grid.c - 1)
//                            | (newCurrentLocation.getX() < 0) | (newCurrentLocation.getY() < 0)) {
//                            newCurrentLocation.setLocation(myCurrentLocation.getX(),
//                                                           myCurrentLocation.getY());
//                        }
//
//                        myGameGridEffect.getHighlighter().unhighlight(grid, myCurrentLocation);
//                        myCurrentLocation = newCurrentLocation;
//                        myGameGridEffect.highlightCurrent(grid, myCurrentLocation, Color.PURPLE);
//
//                        // ArrayList<Label> actions = new ArrayList<Label>();
//                        // if (vc.getPiece(vc.findPosition(myCurrentLocation.getX(),
//                        // myCurrentLocation.getY())) !=null){
//                        // vc.getPiece(vc.findPosition(myCurrentLocation.getX(),myCurrentLocation.getY())).getActions()
//                        // .forEach(action->{
//                        // Label l = new Label(action.toString());
//                        // actions.add(l);});
//                        //
//                        // vc.updateActionList(actions);
//                        // }
//                        // vc.updateActions( );
//                        // vc.unhighlight(myCurrentLocation);
//                    }
//                }
//            }
//        });
//    }
//
//    public Point2D getCurrentLocation () {
//        return myCurrentLocation;
//    }
}