package gamedata.gamecomponents;

import gameengine.player.Player;
import java.util.ArrayList;
import java.util.List;


/**
 * A game that contains a list of players and the levels that players can play
 * 
 * The play method in this class is called in every iteration of the game loop.
 *
 */
public class Game {

    /**
     * Contains player in order of their turns
     */
    private List<Player> myPlayers;
    /**
     * Contains ordered list of levels that compose the game
     */
    private List<Level> myLevels;
    private transient Level myCurrentLevel;
    private transient Player myCurrentPlayer;

    /**
     * Default Constructor
     */
    public Game () {
        this(new ArrayList<Player>(), new ArrayList<Level>());
    }

    /**
     * Instantiate a new Game given a list of players and levels that exist.
     * 
     * @param players
     *        List of players
     * @param levels
     *        List of levels that compose the game
     */
    public Game (List<Player> players, List<Level> levels) {
        myPlayers = players;
        myLevels = levels;

        if (levels.size() > 0 && players.size() > 0) {
            myCurrentLevel = levels.get(0);
            myCurrentPlayer = players.get(0);
        }

    }

    /**
     * Method called every iteration of the game loop. For each player that
     * exists, play the current level. If the level's goals are met, go to the
     * next level. If the player's turn is over, end the method so the loop can
     * call it again.
     */
    public void play () {
        if (myCurrentPlayer.levelWon(myCurrentLevel)) {
            nextLevel();
        }
        nextPlayer();
    }

    /**
     * Iterates the Current Level to the Next Level
     */
    private void nextLevel () {
        if (!isWin()) {
            myCurrentLevel = myLevels.get(myLevels.indexOf(myCurrentLevel) + 1);
        }
        else {
            System.out.println("GAME HAS BEEN WON");
        }
    }

    /**
     * Checks to see if game has been beaten
     * 
     * @return True is game has been won. False otherwise
     */
    private boolean isWin () {
        if (myLevels.indexOf(myCurrentLevel) == myLevels.size() - 1) { return true; }
        return false;
    }

    /**
     * Iterates to the next player to start that players turn. If the last
     * player has just played the first player is active again
     */
    private void nextPlayer () {
        if (myPlayers.indexOf(myCurrentPlayer) == myPlayers.size() - 1) {
            resetPlayer();
        }
        else {
            myCurrentPlayer = myPlayers
                    .get(myPlayers.indexOf(myCurrentPlayer) + 1);
        }
    }

    private void resetPlayer () {
        myCurrentPlayer = myPlayers.get(0);
    }

    /**
     * Restarts the Level Note: This doesn't actually work. Need deep cloning
     */

    private void restartLevel () {
        myCurrentLevel = myLevels.get(myLevels.indexOf(myCurrentLevel));
    }

    public String toString () {
        return "game with " + myPlayers.size() + " players and " + myLevels.size() + " levels";
    }

    /**
     * Getter for the Current Level
     * 
     * @return Returns the Current Level
     */
    public Level getCurrentLevel () {
        return myCurrentLevel;
    }

}
