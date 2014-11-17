package gameengine.player;

import gamedata.gamecomponents.Level;

/**
 * A player object that contains the logic for playing each level. This object
 * requires no parameters for initialization.
 *
 */

public class Player {

	private int myNumMovesPlayed;
	private int myID;

	public Player(int id) {
		myNumMovesPlayed = 0;
		myID = id;
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

	public int getID() {
		return myID;
	}
}
