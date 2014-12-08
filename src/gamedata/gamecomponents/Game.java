package gamedata.gamecomponents;

import gameengine.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private int myGameWon;
	private int myNumPlayers;

	/**
	 * Default Constructor
	 */
	public Game() {
		this(1, null);
	}

	/**
	 * Instantiate a new Game given a list of players and levels that exist.
	 * 
	 * @param players
	 *            List of players
	 * @param levels
	 *            List of levels that compose the game
	 */
	public Game(int numPlayers, List<Level> levels) {
		this(numPlayers, levels, null);
	}

	public Game(int numPlayers, List<Level> levels, Level currentLevel) {
		myGameWon = 0;
		myNumPlayers = numPlayers;
		myPlayers = new ArrayList<Player>();
		myLevels = levels;
		myCurrentLevel = currentLevel;
		myCurrentPlayer = null;
	}

	public void addPlayers(List<Player> p) {
		myPlayers = p;
		if (myPlayers.size() > 0) {
			myCurrentPlayer = myPlayers.get(0);
		}
	}

	/**
	 * Iterates the Current Level to the Next Level If no more levels, game is
	 * won.
	 */
	public void nextLevel() {
		if (!isWin()) {
			myCurrentLevel = myLevels.get(myLevels.indexOf(myCurrentLevel) + 1);
		} else {
			myGameWon = 1;
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
	 * Jumps to the level specified by looking it up using the ID
	 * 
	 * @param levelToJumpTo
	 */
	/*
	 * public void jumpToLevel(String levelToJumpTo) { for (Level level :
	 * myLevels) { if (level.getId().equals(levelToJumpTo)) { myCurrentLevel =
	 * level; if (level.isWinningLevel()) { myGameWon = true; } break; } } }
	 */

	public void changeTurn(int playerToChangeTo) {
		for (Player player : myPlayers) {
			if (player.getID() == playerToChangeTo) {
				setCurrentPlayer(player);
			}
		}
	}

	public void nextPlayer() {
		int next = myPlayers.indexOf(myCurrentPlayer) + 1;
		if (next >= myPlayers.size()) {
			resetPlayer();
		} else {
			myCurrentPlayer = myPlayers.get(next);
		}
	}

	/**
	 * Resets the active player to be the first player who has played
	 */
	private void resetPlayer() {
		myCurrentPlayer = myPlayers.get(0);
		myCurrentPlayer.resetMovesPlayed();
	}

	/**
	 * Restarts the level
	 */
	private void restartLevel() {
		myCurrentLevel.restart();
	}

	public String toString() {
		return "game with " + myPlayers.size() + " players and "
				+ myLevels.size() + " levels";
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

	public List<Level> getLevels() {
		return myLevels;
	}

	public void setPlayer(Player p, int pos) {
		myPlayers.set(pos, p);
	}

	/**
	 * Used by Global Actions. Set the current player to the player with a given
	 * ID
	 * 
	 * @param levelID
	 */
	public void setCurrentLevel(Level l) {
		myCurrentLevel = l;
	}

	public void setCurrentPlayer(Player p) {
		myCurrentPlayer = p;
		p.startTurn(myCurrentLevel);
	}

	/**
	 * Used by global action. Status set as 1 if game is won, set as -1 if game
	 * is lost. game continues to run if value is 0
	 * 
	 * @param gameStatus
	 */
	public void endGame(int gameStatus) {
		myGameWon = gameStatus;
	}

}
