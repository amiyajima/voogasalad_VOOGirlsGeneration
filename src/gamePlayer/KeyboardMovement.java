package gamePlayer;

import gamedata.gamecomponents.Game;
import gameengine.player.HumanPlayer;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import authoring_environment.SuperTile;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Allows users to use keyboard to "move the cursor" around the game grid.
 * KeyboardMovement is strictly used for moving and selecting objects on the game grid.
 * 
 * @author Yoonhyung
 *
 */
public class KeyboardMovement {
    private static final String KEY_MOVEMENT_COLOR = "#AC58FA";
    HumanPlayer myCurrentPlayer;
    Point2D myCurrentLocation;
    GameGridEffect myGameGridEffect;
    Map<KeyCode, Point2D> movementKeyMap;
    SuperTile currentTile;
    List<SuperTile> myHighlightedTiles;

    public KeyboardMovement () {
        myCurrentLocation = new Point2D.Double(0.0, 0.0);
        // Map<KeyCode, Point2D> movementKeyMap = myCurrentPlayer.getMovementKeyMap();
        System.out.println("Keyboard Movement ON");

        // for testing!!!
        movementKeyMap = new HashMap<KeyCode, Point2D>();
        movementKeyMap.put(KeyCode.A, new Point2D.Double(-1.0, 0.0));
        movementKeyMap.put(KeyCode.D, new Point2D.Double(1.0, 0.0));
        movementKeyMap.put(KeyCode.W, new Point2D.Double(0.0, 1.0));
        movementKeyMap.put(KeyCode.S, new Point2D.Double(0.0, -1.0));
    }

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
    public void setMovementKeyControl (ViewController vc, ScrollPane sp, Game game) {
        sp.requestFocus();
        sp.setOnKeyPressed(new EventHandler<KeyEvent>() {

            Set<KeyCode> movementKeyList = movementKeyMap.keySet();

            @Override
            public void handle (KeyEvent key) {
                if (key.getCode() == KeyCode.F) {
                    System.out.println("Selected with key: " + myCurrentLocation);
                    vc.performAction(myCurrentLocation);
                }

                for (KeyCode kc : movementKeyList) {
                    if (key.getCode() == kc) {
                        Point2D newCurrentLocation =
                                new Point2D.Double(myCurrentLocation.getX() + movementKeyMap.get(kc).getX(),
                                                   myCurrentLocation.getY() - movementKeyMap.get(kc).getY());

                        if ((newCurrentLocation.getX() > sp.getWidth() - 1) | (newCurrentLocation.getY() > sp.getHeight() - 1)
                            | (newCurrentLocation.getX() < 0) | (newCurrentLocation.getY() < 0)) {
                            newCurrentLocation.setLocation(myCurrentLocation.getX(), myCurrentLocation.getY());
                        }

                        // unhighlight before you highlight the next one
                        currentTile = vc.getGrid().findClickedTile(myCurrentLocation);
                        currentTile.deselectTile();
                        myCurrentLocation = newCurrentLocation;

                        // highlight the new location now
                        currentTile = vc.getGrid().findClickedTile(myCurrentLocation);
                        currentTile.selectTile(KEY_MOVEMENT_COLOR);
                    }
                }
            }
        });
    }

    /**
     * Returns the current location selected by keyboard movement.
     * 
     * @return
     */
    public Point2D getCurrentLocation () {
        return myCurrentLocation;
    }
}
