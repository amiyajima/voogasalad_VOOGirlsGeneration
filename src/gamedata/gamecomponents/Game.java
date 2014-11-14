package gamedata.gamecomponents;

import java.util.ArrayList;
import java.util.List;

import gameengine.player.*;
import gamedata.gamecomponents.*;

public class Game {

	private List<Player> myPlayers;
	private List<Level> myLevels;
	private Level currentLevel;

	public Game() {
		myPlayers = new ArrayList<Player>();
		myLevels = new ArrayList<Level>();
	}

	/**
	 * Method called every iteration of the game loop
	 */
	public void playRound() {
		for (Player p : myPlayers) {
			p.play();
		}
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
