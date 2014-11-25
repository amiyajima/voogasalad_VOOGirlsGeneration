package authoring.data;

import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
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
	public void clear() {
		myPieces.clear();
	}
	
	public void removePiece(Point2D location){
		for(Piece piece : myPieces){
			if(location.equals(piece.getLoc())){
				myPieces.remove(piece);
				return;
			}
		}
	}
	
	public boolean unitAtLoc(Piece unit, int x, int y){
		Point2D location = new Point2D.Double(x, y);
		for(Piece piece : myPieces){
			if(location.equals(piece.getLoc()) && unit.getName().equals(piece.getName())){
				myPieces.remove(piece);
				return true;
			}
		}
		return false;
	}
	
	public List<Piece> getPieces(){
        return myPieces;
    }

    @Override
    public List<Piece> get () {
        return myPieces;
    }
}