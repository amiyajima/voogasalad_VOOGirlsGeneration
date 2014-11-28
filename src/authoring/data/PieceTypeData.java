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
    public void add(Piece...pieces) {
        for (Piece p : pieces ) {
            myPieces.add(p);
        }
    }

    @Override
    public void remove(Piece...pieces) {
        for (Piece p : pieces) {
            myPieces.remove(p);
        }
    }
    
    @Override
    public List<Piece> get () {
        return myPieces;
    }

	@Override
	public void clear() {
		myPieces.clear();
	}
}