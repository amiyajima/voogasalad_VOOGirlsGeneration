package gamedata.gamecomponents;

import gameengine.player.Player;

import java.util.List;

public class GameState {

	public static boolean gameWon;
	public static boolean gameLost;
	public static boolean turnOver;
	public static String nextLevelID;
	public static List<Player> playersList;

	public static boolean getGameWon() {
		return gameWon;
	}

	public static boolean getGameLost() {
		return gameLost;
	}

	public static boolean getTurnOver() {
		return turnOver;
	}

	public static String getNextLevelID() {
		return nextLevelID;
	}

	public static void setTurnFalse() {
		turnOver = false;
	}

	/**
	 * IChangeGameState interface methods
	 */
	public static void winGame() {
		gameWon = true;
	}

	public static void loseGame() {
		gameLost = true;
	}

	public static void endTurn() {
		turnOver = true;
	}

	public static void changeLevel(String name) {
		nextLevelID = name;
	}

	public static void printState() {
		System.out.println(String.format(
				"GameWon %b GameLost %b TurnOver %b NextLevel %b", gameWon,
				gameLost, turnOver, nextLevelID));
	}
}
