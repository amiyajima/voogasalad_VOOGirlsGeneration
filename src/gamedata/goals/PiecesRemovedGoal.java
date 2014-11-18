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
		for (Piece p : myPieces) {
			if (l.getGrid().getPiece(p.getUniqueID()) == null) {
				myPieces.remove(p);
			}
		}
		if (myPieces.size() == 0) {
			return 1;
		}
		return 0;
	}

}
