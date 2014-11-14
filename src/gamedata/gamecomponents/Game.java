package gamedata.gamecomponents;

import java.util.ArrayList;
import java.util.List;

import gameengine.player.*;
import gamedata.gamecomponents.*;

public class Game {

	private List<Player> myPlayers;
	private List<Level> myLevels;
	private Level currentLevel;
	private Player currentPlayer;

	public Game() {
		myPlayers = new ArrayList<Player>();
		myLevels = new ArrayList<Level>();
	}

	public void nextPlayer() {
		if (myPlayers.indexOf(currentPlayer) == myPlayers.size() - 1) {
			resetPlayer();
		} else {
			currentPlayer = myPlayers.get(myPlayers.indexOf(currentPlayer) + 1);
		}
	}

	public void resetPlayer() {
		currentPlayer = myPlayers.get(0);
	}

	/**
	 * Iterates the Current Level to the Next Level
	 */
	public void nextLevel() {
		if (!isWin()) {
			currentLevel = myLevels.get(myLevels.indexOf(currentLevel) + 1);
		}
	}

	/**
	 * Restarts the Level
	 */

	public void restartLevel() {
		currentLevel = myLevels.get(myLevels.indexOf(currentLevel));
	}

	/**
	 * Checks to see if game has been beaten
	 * 
	 * @return True is game has been won. False otherwise
	 */
	public boolean isWin() {
		if (myLevels.indexOf(currentLevel) == myLevels.size() - 1) {
			return true;
		}
		return false;
	}

}
