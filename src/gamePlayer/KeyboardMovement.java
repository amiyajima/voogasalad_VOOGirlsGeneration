package gamePlayer;

import gameengine.player.HumanPlayer;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Allows users to use keyboard to "move the cursor" around the game grid.
 * KeyboardMovement is strictly used for moving and selecting objects on the game grid.
 * 
 * @author Yoonhyung Choi
 *
 */
public class KeyboardMovement {
    private static final String KEY_MOVEMENT_COLOR = "#AC58FA";
    private static final String TYPE_HUMAN = "Human";

    private Point2D myCurrentLocation;
    private Map<KeyCode, Point2D> myKeyMap;

    public KeyboardMovement () {
        myCurrentLocation = new Point2D.Double(0.0, 0.0);
        myKeyMap = new HashMap<KeyCode, Point2D>();
    }

    /**
     * 
     * mKeyCodeMap map (which maps up,down,left,right,select movements to keycodes) is passed by the
     * game engine. This method creates key event handlers so that when such a keycode is
     * pressed, its corresponding movement is implemented in the game grid display.
     * Movement is "moving the cursor" and "selecting with cursor" with the keyboard.
     * Here, the selection is defined as Point2D Double (1.0,1.0).
     * 
     * @param myKeyMap
     * @param gameScene
     */
    public void setMovementKeyControl (ViewController vc) {
        if ((vc.getGame().getCurrentPlayer().getType().equals(TYPE_HUMAN))) {

            myKeyMap = ((HumanPlayer) vc.getGame().getCurrentPlayer()).getMovementKeyMap();

            // first highlight where the keyboard movement starts on the game grid.
            vc.getGrid().findTile(myCurrentLocation).selectTile(KEY_MOVEMENT_COLOR);

            vc.getGridPane().requestFocus();
            vc.getGridPane().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle (KeyEvent key) {
                    for (KeyCode kc : myKeyMap.keySet()) {
                        if (key.getCode() == kc) {
                            if (myKeyMap.get(kc).equals(new Point2D.Double(1.0, 1.0))) {
                                vc.performAction(myCurrentLocation);
                            }

                            // unhighlight before you highlight the next one
                            vc.getGrid().findTile(myCurrentLocation).deselectTile();

                            Point2D newCurrentLocation =
                                    new Point2D.Double(myCurrentLocation.getX() + myKeyMap.get(kc).getX(),
                                                       myCurrentLocation.getY() - myKeyMap.get(kc).getY());

                            if ((newCurrentLocation.getX() <= vc.getGrid().getNumRows() - 1)
                                && (newCurrentLocation.getY() <= vc.getGrid().getNumCols() - 1)
                                && (newCurrentLocation.getX() >= 0) &&
                                (newCurrentLocation.getY() >= 0)) {
                                myCurrentLocation = newCurrentLocation;
                            }

                            // highlight the location now
                            vc.getGrid().findTile(myCurrentLocation).selectTile(KEY_MOVEMENT_COLOR);
                        }
                    }
                }
            });
        }
    }

    /**
     * Returns the current location selected by keyboard movement.
     * 
     * @return current keyboard control location on the game grid
     */
    public Point2D getCurrentLocation () {
        return myCurrentLocation;
    }
}
