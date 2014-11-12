package gamedata.goals;

import gamedata.patch.Patch;
import gamedata.piece.Piece;

public class PieceOnPatchGoal extends Goal {
	
	private Piece myPiece;
	private Patch myPatch;
	
	public PieceOnPatchGoal(Piece pie, Patch pat){
		myPiece = pie;
		myPatch = pat;
	}

	@Override
	int checkGameState(Level l) {
		
		return 0;
	}

}
