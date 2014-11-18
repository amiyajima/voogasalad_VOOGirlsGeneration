package gamedata.action;

import gamedata.gamecomponents.Piece;

public interface ActionConclusion {

	/**
	 * Conclusion that runs at the end of each action
	 * Could be piece removal, more modifying of stats, etc.
	 * Will be pre-coded in code and chosen by the user.
	 * @param actor - Piece that calls action
	 * @param receivers - Pieces that receive the action
	 */
	public void runConclusion(Piece actor, Piece... receivers);
}
