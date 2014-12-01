package gameengine.player;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import gamedata.action.Action;
import gamedata.gamecomponents.Level;
import java.awt.geom.Point2D;


/**
 * A player object that contains the logic for playing each level. This object
 * requires no parameters for initialization.
 *
 * @Author Jesse, Sandy
 */

public class Player {

    private int myNumMovesPlayed;
    private int myID;
    private Map<KeyCode, Action> myActionKeyMap;
    // for keypressed, when keys don't trigger actions. ex) arrow keys
    private Map<KeyCode, Point2D> myMovementKeyMap;

    /**
     * Default constructor
     */
    public Player () {
        this(0);
    }

    /**
     * Constructs a player with a specific ID
     * 
     * @param id
     *        int ID corresponding to the Player
     */
    public Player (int id) {
        myNumMovesPlayed = 0;
        myID = id;
        setActionKeyMap(myActionKeyMap);
        setMovementKeyMap(myMovementKeyMap);

    }

    /**
     * Resets number of moves player for the player
     */
    public void resetMovesPlayed () {
        myNumMovesPlayed = 0;
    }

    /**
     * Getter to return the ID of the player
     * 
     * @return int ID of the player
     */
    public int getID () {
        return myID;
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
     * needs to get info from the authoring environment to set up the map..
     * 
     * @param myKeyMap
     */
    public void setActionKeyMap (Map<KeyCode, Action> myActionKeyMap) {
        myActionKeyMap = new HashMap<KeyCode, Action>();
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
     * Sets the Key Mapping for the Player
     * 
     * @param myMovementKeyMap2
     */
    public void setMovementKeyMap (Map<KeyCode, Point2D> myMovementKeyMap2) {
        myMovementKeyMap2 = new HashMap<KeyCode, Point2D>();
    }

    public String toString () {
        return "ID:" + myID + " NumMovesPlayed:" + myNumMovesPlayed + " movement key map:" +
               myMovementKeyMap;
    }
    
    public int getNumMovesPlayed(){
    	return myNumMovesPlayed;
    }

}
