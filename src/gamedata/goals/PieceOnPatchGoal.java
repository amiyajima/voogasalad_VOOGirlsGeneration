package gamedata.goals;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

/**
 * Defines a win situation to be when a specified piece is on a specified patch
 * 
 * @author Jesse
 *
 */
public class PieceOnPatchGoal extends Goal {

	private Piece myPiece;
	private Patch myPatch;

	/**
	 * Constructor
	 * 
	 * @param pie
	 *            Target Piece
	 * @param pat
	 *            Target Patch
	 */
	public PieceOnPatchGoal(Piece pie, Patch pat) {
		myPiece = pie;
		myPatch = pat;
	}

	@Override
	public int checkGameState(Level l) {
		Grid g = l.getGrid();
		Piece p = g.getPiece(myPiece.toString());
		if ((p.getLoc().getX() == myPatch.getLoc().getX())
				&& (p.getLoc().getY() == myPatch.getLoc().getY())) {
			return 1;
		}
		return 0;
	}
}
