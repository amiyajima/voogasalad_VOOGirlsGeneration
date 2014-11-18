package gamedata.goals;

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

public class PieceOnPatchGoal extends Goal {

	private Piece myPiece;
	private Patch myPatch;

	public PieceOnPatchGoal(Piece pie, Patch pat) {
		myPiece = pie;
		myPatch = pat;
	}

	@Override
	public int checkGameState(Level l) {
		Grid g = l.getGrid();
		Piece p = g.getPiece(myPiece.getUniqueID());
		if ((p.getLoc().getX() == myPatch.getLoc().getX())
				&& (p.getLoc().getY() == myPatch.getLoc().getY())) {
			return 1;
		}
		return 0;
	}
}
