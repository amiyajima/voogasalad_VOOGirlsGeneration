package gamedata.rules;

/**
 * A rule that checks if a player has any possible moves left
 * 
 */
public class AvailableMovesRule extends Rule {

	private int myMinNumUnit;

	/**
	 * Constructor
	 */
	public AvailableMovesRule() {
	}

	@Override
	public boolean conditionsMet(int playerID) {
		//TODO:I need list of units here and rule differentiation. Look into this! 
		return false;
	}

}
