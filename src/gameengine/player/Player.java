package gameengine.player;

import gamedata.gamecomponents.Level;

/**
 * A player object that contains the logic for playing each level. This object
 * requires no parameters for initialization.
 *
 */

public class Player {

	private int myNumMovesPlayed;

	public Player() {
		myNumMovesPlayed = 0;
	}

	/**
	 * Until you run out of moves, play the level. Return true if the level was
	 * won. Otherwise, play the next move until your turn is over. Return false
	 * if your turn is over and the level was not won
	 * 
	 * @param level
	 */
	public boolean levelWon(Level level) {
		while (!level.checkTurnEnd(myNumMovesPlayed)) {
			// play a move
			if (level.levelCompleted()) {
				return true;
			}
			myNumMovesPlayed++;
		}
		this.resetMovesPlayed();
		return false;
	}

	public void resetMovesPlayed() {
		myNumMovesPlayed = 0;
	}
}
