package gamedata.rules;

/**
 * REPLACED BY MOVECOUNTCONDITION
 * A rule that checks if the player has moves remaining in the current turn
 */
public class MoveCountRule extends Rule {

	private int myNumMoves;

	/**
	 * Constructor
	 * 
	 * @param moves
	 *            Number of mounts a player can make in a turn
	 */
	public MoveCountRule(int moves) {
		myNumMoves = moves;
	}

	@Override
	public boolean conditionsMet(int turnCount) {
		return (turnCount >= myNumMoves);
	}

	/**
	 * FOR TESTING PURPOSES
	 */
	public String toString() {
		return "moves left are" + myNumMoves;
	}

}
