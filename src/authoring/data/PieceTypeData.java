package authoring.data;

import gamedata.gamecomponents.Piece;

import java.util.LinkedList;
import java.util.List;

public class PieceTypeData implements AuthoringData<Piece> {
	
    private List<Piece> myPieces;
    
    public PieceTypeData() {
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