package gamedata.gamecomponents;

import java.util.ArrayList;
import java.util.List;
import gameengine.player.*;
import gamedata.gamecomponents.*;


public class Game {

    private List<Player> myPlayers;
    private List<Level> myLevels;
    private Level myCurrentLevel;
    private Player myMainPlayer;

    // rules such as "each player gets 3 moves"

    /**
     * Instantiate a new Game given a list of players and levels that exist.
     * 
     * @param players
     * @param levels
     */
    public Game (ArrayList<Player> players, ArrayList<Level> levels) {
        myPlayers = players;
        myLevels = levels;
        myCurrentLevel = levels.get(0);
        myMainPlayer = players.get(0);
    }

    /**
     * Method called every iteration of the game loop.
     * For each player that exists, play the current level.
     */
    public void playRound () {
        for (Player p : myPlayers) {
            myCurrentLevel.playLevel(p);
        }
    }

    /**
     * Iterates the Current Level to the Next Level
     */
    public void nextLevel () {
        if (!isWin()) {
            myCurrentLevel = myLevels.get(myLevels.indexOf(myCurrentLevel) + 1);
        }
    }

    /**
     * Checks to see if game has been beaten
     * 
     * @return True is game has been won. False otherwise
     */
    public boolean isWin () {
        if (myLevels.indexOf(myCurrentLevel) == myLevels.size() - 1) { return true; }
        return false;
    }

}
