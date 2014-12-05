package gameengine.player;

import gamedata.action.Action;
import gamedata.gamecomponents.Grid;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;


/**
 * Defines a Human Player
 * 
 * @author Jesse, Sandy
 *
 */
public class HumanPlayer extends Player {

    private Map<KeyCode, Action> myActionKeyMap;
    // for keypressed, when keys don't trigger actions. ex) arrow keys
    private Map<KeyCode, Point2D> myMovementKeyMap;

    public HumanPlayer (int id, Grid g) {
        super(id, g);
        setActionKeyMap(myActionKeyMap);
        setMovementKeyMap(myMovementKeyMap);
    }

    @Override
    public void startTurn () {
        this.resetMovesPlayed();
        // TODO: Map Keys Here

    }

    /**
     * used by game player (GUI) so that it knows what action to perform when
     * certain keycodes are pressed/used.
     * 
     * @return myKeyMap which maps actions to pre-defined keycodes
     */
    public Map<KeyCode, Action> getActionKeyMap () {
        return myActionKeyMap;
    }

    /**
     * Returns the Key Mapping for the Player
     * 
     * @return
     */
    public Map<KeyCode, Point2D> getMovementKeyMap () {
        return myMovementKeyMap;
    }

    /**
     * needs to get info from the authoring environment to set up the map..
     * 
     * @param myKeyMap
     */
    public void setActionKeyMap (Map<KeyCode, Action> myActionKeyMap) {
        myActionKeyMap = new HashMap<KeyCode, Action>();
    }

    /**
     * Sets the Key Mapping for the Player
     * 
     * @param myMovementKeyMap2
     */
    public void setMovementKeyMap (Map<KeyCode, Point2D> myMovementKeyMap2) {
        myMovementKeyMap2 = new HashMap<KeyCode, Point2D>();
    }

}
