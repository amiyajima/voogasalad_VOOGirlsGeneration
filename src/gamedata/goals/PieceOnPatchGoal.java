package gamedata.goals;

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
		// If piece is on patch
		return 0;
	}

}
