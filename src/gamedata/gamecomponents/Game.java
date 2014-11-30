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
	private Level myCurrentLevel;
	private Player myCurrentPlayer;
	private boolean myGameWon;

	/**
	 * Default Constructor
	 */
	public Game() {
		this(null, null);
	}

	/**
	 * Instantiate a new Game given a list of players and levels that exist.
	 * 
	 * @param players
	 *            List of players
	 * @param levels
	 *            List of levels that compose the game
	 */
	public Game(List<Player> players, List<Level> levels) {
		myGameWon = false;
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
	 * 
	 * GameLoop moved to Player. Method will be removed eventually. 
	 */
	public void play() {
		while (!myGameWon) {
			if (myCurrentPlayer.levelWon(myCurrentLevel)) {
				nextLevel();
			}
			nextPlayer();
		}
		// Call DisplayWin Method Here :) Yatta
	}

	/**
	 * Iterates the Current Level to the Next Level If no more levels, game is
	 * won.
	 */
	public void nextLevel() {
		if (!isWin()) {
			myCurrentLevel = myLevels.get(myLevels.indexOf(myCurrentLevel) + 1);
		} else {
			myGameWon = true;
		}
	}

	/**
	 * Checks to see if game has been beaten
	 * 
	 * @return True is game has been won. False otherwise
	 */
	private boolean isWin() {
		if (myLevels.indexOf(myCurrentLevel) == myLevels.size() - 1) {
			return true;
		}
		return false;
	}

	/**
	 * Iterates to the next player to start that players turn. If the last
	 * player has just played the first player is active again
	 */
	public void nextPlayer() {
		if (myPlayers.indexOf(myCurrentPlayer) == myPlayers.size() - 1) {
			resetPlayer();
		} else {
			myCurrentPlayer = myPlayers
					.get(myPlayers.indexOf(myCurrentPlayer) + 1);
		}
	}

	/**
	 * Resets the active player to be the first player who has played
	 */
	private void resetPlayer() {
		myCurrentPlayer = myPlayers.get(0);
	}

	/**
	 * Restarts the Level Note: This doesn't actually work. Need deep cloning
	 */
	private void restartLevel() {
		myCurrentLevel = myLevels.get(myLevels.indexOf(myCurrentLevel));
	}
	
	public String toString(){
	    return "game with " + myPlayers.size() + " players and " + myLevels.size() + " levels";
	}

	/**
	 * Getter for the Current Level
	 * 
	 * @return Returns the Current Level
	 */
	public Level getCurrentLevel() {
		return myCurrentLevel;
	}

	/**
	 * Getter for the Current Player
	 * 
	 * @return Returns the Current Player
	 */
	public Player getCurrentPlayer() {
		return myCurrentPlayer;
	}

	public List<Player> getPlayers() {
		return myPlayers;
	}
	
	public List<Level> getLevels(){
	    return myLevels;
	}
}
