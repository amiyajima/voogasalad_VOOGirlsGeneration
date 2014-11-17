package gamedata.action;

import gamedata.gamecomponents.Piece;

/**
 * Interface of a game component that performs
 * an action on another component in the game.
 * Pieces will contain a list of modules.
 */
public interface Action {
	
	/**
	 * Executes an action on a component of
	 * the game (i.e. a piece, patch, or other module)
	 */
	public void doBehavior(Piece actor, Piece receiver);

}
