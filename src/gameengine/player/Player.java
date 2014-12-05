package gameengine.player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.input.KeyCode;
import gamedata.action.Action;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
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
    private List myPieces;

    /**
     * Default constructor
     */
    public Player (Grid g) {
        this(0, g);
    }

    /**
     * Constructs a player with a specific ID
     * 
     * @param id
     *        int ID corresponding to the Player
     */
    public Player (int id, Grid g) {
        myNumMovesPlayed = 0;
        myID = id;
        myPieces = populatePieces(g);
    }

    /**
     * Iterate through the list of pieces available on the grid.
     * 
     * @param g
     * @return
     * TODO: itereate through pieces in grid, determine which ones have corresponding Player ID
     */
    private List<Piece> populatePieces (Grid g) {
        // Set allPieces = g.get
        return null;
    }
    
    public List<Piece> getPieces(){
        return Collections.unmodifiableList(myPieces);
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
