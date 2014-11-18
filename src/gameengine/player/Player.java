package gameengine.player;

import java.util.HashMap;
import java.util.Map;
import com.sun.javafx.scene.accessibility.Action;
import javafx.scene.input.KeyCode;
import gamedata.gamecomponents.Level;


/**
 * A player object that contains the logic for playing each level. This object
 * requires no parameters for initialization.
 *
 */

public class Player {

    private int myNumMovesPlayed;
    private int myID;
    private Map<KeyCode, Action> myKeyMap;

    public Player (int id) {
        myNumMovesPlayed = 0;
        myID = id;
        setKeyMap(myKeyMap);
    }

    /**
     * Until you run out of moves, play the level. Return true if the level was
     * won. Otherwise, play the next move until your turn is over. Return false
     * if your turn is over and the level was not won
     * 
     * @param level
     */
    public boolean levelWon (Level level) {
        while (!level.checkTurnEnd(myNumMovesPlayed)) {
            // play a move
            if (level.levelCompleted()) { return true; }
            myNumMovesPlayed++;
        }
        this.resetMovesPlayed();
        return false;
    }

    public void resetMovesPlayed () {
        myNumMovesPlayed = 0;
    }

    public int getID () {
        return myID;
    }

    /**
     * used by game player (GUI) so that it knows what action to perform
     * when certain keycodes are pressed/used.
     * 
     * @return myKeyMap which maps actions to pre-defined keycodes
     */
    public Map<KeyCode, Action> getKeyMap () {
        return myKeyMap;
    }

    /**
     * needs to get info from the authoring environment to set up the map..
     * 
     * @param myKeyMap
     */
    public void setKeyMap (Map<KeyCode, Action> myKeyMap) {
        myKeyMap = new HashMap<KeyCode, Action>();
    }
}
