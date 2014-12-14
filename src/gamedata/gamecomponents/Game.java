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
 * @author Jesse, Jennie
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
	/**
	 * Current Level being Player
	 */
	private Level myCurrentLevel;
	/**
	 * Current Player Making Plays
	 */
	private Player myCurrentPlayer;
	/**
	 * Integer representing the win state of the game
	 */
	private int myGameWon;
	/**
	 * Number of players in the game
	 */
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
	 * @param numPlayers
	 *            Number of Players
	 * @param levels
	 *            List of levels that compose the game
	 */
	public Game(int numPlayers, List<Level> levels) {
		this(numPlayers, levels, null);
	}

	/**
	 * Instantiate a new Game given a list of players and levels that exist and
	 * a specific currentLevel
	 * 
	 * @param numPlayers
	 *            Number of Players
	 * @param levels
	 *            List of levels that compose the game
	 * @param currentLevel
	 *            Current Level that the game starts on
	 */
	public Game(int numPlayers, List<Level> levels, Level currentLevel) {
		myGameWon = 0;
		myNumPlayers = numPlayers;
		myPlayers = new ArrayList<Player>();
		myLevels = levels;
		myCurrentLevel = currentLevel;
		myCurrentPlayer = null;
	          System.out.println("Game initialized");

	}

	/**
	 * Starts the game by calling on the current player to start the turn
	 */
	public void startGame() {
		// System.out.println("Starting the Game");
		myCurrentPlayer.startTurn(myCurrentLevel);
	}

	/**
	 * Adds the list of players whom will by playing the game in order
	 * 
	 * @param p
	 *            List of Players
	 */
	public void addPlayers(List<Player> p) {
		myPlayers = p;
		if (myPlayers.size() > 0) {
			myCurrentPlayer = myPlayers.get(0);
		}
	}

	/**
	 * Changes the player to the player with the int position in the list of
	 * players
	 * 
	 * @param playerToChangeTo
	 *            Integer to change the current player to
	 */
	public void changeTurn(int playerToChangeTo) {
		for (Player player : myPlayers) {
			if (player.getID() == playerToChangeTo) {
				setCurrentPlayer(player);
			}
		}
	}

	/**
	 * Resets the active player to be the first player who has played
	 */
	private void resetPlayer() {
		myCurrentPlayer = myPlayers.get(0);
		myCurrentPlayer.startTurn(myCurrentLevel);
	}

	/**
	 * Restarts the level
	 */
	private void restartLevel() {
		myCurrentLevel.restart();
	}

	/**
	 * Formats the information regarding a game to a string output
	 */
	public String toString() {
		return "game with " + myNumPlayers + " players and "
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

	/**
	 * Getter for the list of players
	 * 
	 * @return Returns the list of players
	 */
	public List<Player> getPlayers() {
		return myPlayers;
	}

	/**
	 * Getter for the list of levels
	 * 
	 * @return Returns the list of levels
	 */
	public List<Level> getLevels() {
		return myLevels;
	}

	/**
	 * Setter for the player to set a player to a specific position in the list
	 * 
	 * @param p
	 *            Player to set
	 * @param pos
	 *            Int Position to set the player in the list
	 */
	public void setPlayer(Player p, int pos) {
		myPlayers.set(pos, p);
	}

	/**
	 * Iterates the current player to the next chronological player in the list
	 * of players
	 */
	public void nextPlayer() {
		int next = myPlayers.indexOf(myCurrentPlayer) + 1;
		if (next == myPlayers.size()) {
			this.resetPlayer();
		} else {
			myCurrentPlayer = myPlayers.get(next);
			myCurrentPlayer.startTurn(myCurrentLevel);
		}
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

	/**
	 * Sets the current player to a specific player p
	 * 
	 * @param p
	 *            Player p to set as the current player
	 */
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

	/**
	 * Used by global action.
	 * 
	 * @param name
	 */
	public void changeLevel(String name) {
		for (Level level : myLevels) {
			if (level.getId().equals(name)) {
				myCurrentLevel = level;
			}
		}
	}

	/**
	 * Used by global action.
	 */
	public void endTurn() {
		int next = myPlayers.indexOf(myCurrentPlayer) + 1;
		if (next >= myPlayers.size()) {
			resetPlayer();
		} else {
			myCurrentPlayer = myPlayers.get(next);
		}
	}

}
