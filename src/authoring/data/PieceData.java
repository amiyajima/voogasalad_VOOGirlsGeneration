package authoring.data;

import gamedata.gamecomponents.Piece;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for storing the each type of piece created by the user.
 * Stored in the game authoring environment.
 * 
 * @author Martin Tamayo
 */
public class PieceData implements AuthoringData<Piece> {
	
    private List<Piece> myPieces;
    
    /**
     * Constructor for new PieceData,
     * initializes empty list of Pieces.
     */
    public PieceData() {
        myPieces = new LinkedList<Piece>();
    }
    
	@Override
	public void add(Piece piece) {
		myPieces.add(piece);
	}

	@Override
	public void remove(Piece piece) {
		myPieces.remove(piece);
	}

	@Override
	public void clear() {
		myPieces.clear();
	}
}