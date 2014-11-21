package gamedata.goals;

import java.util.List;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;

/**
 * Defines a win condition to be when specified pieces have been
 * defeated/removed from the board
 * 
 * @author Jesse
 *
 */
public class PiecesRemovedGoal extends Goal {

	private List<Piece> myPieces;

	/**
	 * Constructor
	 * 
	 * @param args
	 *            Pieces to be removed
	 */
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
