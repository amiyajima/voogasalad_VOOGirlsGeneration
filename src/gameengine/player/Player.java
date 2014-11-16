package gameengine.player;

import gamedata.gamecomponents.Level;

public class Player {

	private int myNumMoves;
	private int myNumMovesPlayed;

	public Player() {
		this(0);
	}

	public Player(int num) {
		myNumMovesPlayed = 0;
		myNumMoves = num;
	}

	/**
	 * While you're playing the level, check after each turn to see if the turn
	 * has been won. If it has been won, tell the game to move to the next
	 * level.
	 * 
	 * @param level
	 */
	public boolean levelWon(Level level) {
		if (level.levelCompleted()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks to see if the player's turn has ended
	 * 
	 * @param level
	 * @return
	 */
	public boolean endTurn(Level level) {
		myNumMovesPlayed++;
		return level.checkTurnEnd(myNumMovesPlayed);

	}

}
