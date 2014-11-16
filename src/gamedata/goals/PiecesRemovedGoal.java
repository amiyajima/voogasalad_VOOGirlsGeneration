package gamedata.goals;

import java.util.List;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;

public class PiecesRemovedGoal extends Goal {

	private List<Piece> myPieces;

	public PiecesRemovedGoal(Piece... args) {
		for (Piece p : args) {
			myPieces.add(p);
		}
	}

	@Override
	public int checkGameState(Level l) {
		// TODO Auto-generated method stub
		// Check to see if all pieces are removed
		return 0;
	}

}
