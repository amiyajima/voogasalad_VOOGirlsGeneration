package gamedata.goals;

import gamedata.grid.Level;
import gamedata.patch.Patch;
import gamedata.piece.Piece;

public class PieceOnPatchGoal extends Goal {

	private Piece myPiece;
	private Patch myPatch;

	public PieceOnPatchGoal(Piece pie, Patch pat) {
		myPiece = pie;
		myPatch = pat;
	}

	@Override
	public int checkGameState(Level l) {
		// If piece is on patch
		return 0;
	}

}
