package gamedata.action;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.Piece;

public interface ActionConclusion {

	/**
	 * Conclusion that runs at the end of each action
	 * Could be piece removal, more modifying of stats, etc.
	 * Will be pre-coded in code and chosen by the user.
	 * @param grid TODO
	 * @param actor - Piece that calls action
	 * @param receivers - Pieces that receive the action
	 */
	public void runConclusion(GUIGrid grid, Piece actor, Piece... receivers);
}
