package gamedata.gamecomponents;

import java.util.ArrayList;
import java.util.List;
import gameengine.player.*;


/**
 * A game that contains a list of players and the levels that players can play
 * 
 * The play method in this class is called in every iteration of the game loop.
 *
 */
public class Game {

    private List<Player> myPlayers;
    private List<Level> myLevels;
    private Level myCurrentLevel;
    private Player myCurrentPlayer;

    public Game(){
        this(null,null);
    }

    /**
     * Instantiate a new Game given a list of players and levels that exist.
     * 
     * @param players
     * @param levels
     */
    public Game (List<Player> players, List<Level> levels) {
        myPlayers = players;
        myLevels = levels;
        myCurrentLevel = levels.get(0);
        myCurrentPlayer = players.get(0);
    }

    /**
     * Method called every iteration of the game loop.
     * For each player that exists, play the current level.
     * If the level's goals are met, go to the next level.
     * If the player's turn is over, end the method so the loop can call it again.
     */
    public void play () {
        if(myCurrentPlayer.levelWon(myCurrentLevel)){
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
        else{
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

    private void nextPlayer () {
        if (myPlayers.indexOf(myCurrentPlayer) == myPlayers.size() - 1) {
            resetPlayer();
        }
        else {
            myCurrentPlayer = myPlayers.get(myPlayers.indexOf(myCurrentPlayer) + 1);
        }
    }

    private void resetPlayer () {
        myCurrentPlayer = myPlayers.get(0);
    }

    /**
     * Restarts the Level
     */

    private void restartLevel () {
        myCurrentLevel = myLevels.get(myLevels.indexOf(myCurrentLevel));
    }

}
