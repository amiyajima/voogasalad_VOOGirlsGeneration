package gameengine.player;

import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Level;
import gamedata.stats.Stats;

import java.awt.geom.Point2D;


/**
 * A player object that contains the logic for playing each level. This object
 * requires no parameters for initialization.
 *
 * @Author Jesse, Rica, Sandy
 */

public abstract class Player implements IHasStats {

    /**
     * Number of moves a player has made in a given turn
     */
    protected int myNumMovesPlayed;
    /**
     * ID representing the player
     */
    protected int myID;

    /**
     * Stats ranging from score to gold
     */
    protected Stats myStats;
    
    protected String myScoreStat;

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
   
    protected Player (int id, Stats stats, String scoreStat) {
        myNumMovesPlayed = 0;
        myID = id;
        myStats = stats;
        myScoreStat = scoreStat;
    }

    public Stats getStats () {
        return myStats;

    }
    
    public double getScore() {
    	return myStats.getValue(myScoreStat);
    }

    public Point2D getLoc () {
        return null;
    }

    /**
     * Modify a stat of the player based on info from a global action
     * @param statName
     * @param value
     */
    public void changeStat(String statName, double value){
        double curVal = myStats.getValue(statName);
        myStats.setValue(statName, curVal + value);
    }
    
    /**
     * Sets the player to start a turn on a level
     * 
     * @param l
     */
    public abstract void startTurn (Level l);

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

    /**
     * Implements the play behavior of the player (allowing the user to become
     * active and play)
     */
    public abstract void play ();

    /**
     * Increments the number moves played during a turn
     */
    public void playTurn () {
        myNumMovesPlayed++;
    }

    /**
     * Getter for Number of Moves Played
     * 
     * @return
     */
    public int getNumMovesPlayed () {
        return myNumMovesPlayed;
    }

    /**
     * ToString Method for Player Information. For debugging purposes.
     */
    
    public String printString () {
        return "Type:" + this.getClass().getName() + " ID:" + myID
               + " NumMovesPlayed:" + myNumMovesPlayed;
    }
    
    @Override
    public String toString() {
        return "Player " + myID;
    }

    /**
     * Returns the type of the player
     * 
     * @return String representing the type of the player
     */
    public abstract String getType ();

}
