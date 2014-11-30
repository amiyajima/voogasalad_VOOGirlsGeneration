package authoring.data;

import gamedata.gamecomponents.Piece;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PieceTypeData implements AuthoringData<Piece> {
	
    private List<Piece> myPieces;
    
    public PieceTypeData() {
        myPieces = new ArrayList<Piece>();
    }
    
    @Override
    public void add(Piece...pieces) {
        System.out.println("PieceTypeData - Adding piece");
        for (Piece p : pieces ) {
            myPieces.add(p);
        }
        System.out.println("My pieces is now: " + myPieces.size() + " long");
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
    
    public Piece getPiece(String s) {
        for (Piece p : myPieces) {
            if (p.getName().equals(s)) {
                System.out.println("PieceTypeData - Here is your piece: " + p.getName());
                return p;
            }
        }
        System.out.println("PieceTypeData - No piece exists by the name of: " + s);
        return null;
    }

	@Override
	public void clear() {
		myPieces.clear();
	}
}