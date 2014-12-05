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

public abstract class Player {

    private int myNumMovesPlayed;
    private int myID;

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

    }

    public abstract void play ();

    /**
     * Resets number of moves played for the player
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

    public int getNumMovesPlayed () {
        return myNumMovesPlayed;
    }

    public String toString () {
        return "ID:" + myID + " NumMovesPlayed:" + myNumMovesPlayed;
    }

}
