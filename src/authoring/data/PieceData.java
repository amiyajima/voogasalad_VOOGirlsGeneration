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
	public void add(Piece piece) {
		System.out.println("Added piece at location x = " + piece.getLoc().getX() + ", y = " + piece.getLoc().getY());
		myPieces.add(piece);
		for(Piece p : myPieces){
			double x = p.getLoc().getX();
			double y = p.getLoc().getY();
			System.out.println(x + ", " + y);
		}
	}

	@Override
	public void remove(Piece piece) {
		System.out.println("Removed piece at location x = " + piece.getLoc().getX() + ", y = " + piece.getLoc().getY());
		myPieces.remove(piece);
		for(Piece p : myPieces){
			double x = p.getLoc().getX();
			double y = p.getLoc().getY();
			System.out.println(x + ", " + y);
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
			if(location.equals(piece.getLoc()) && unit.getTypeID() == piece.getTypeID()){
				myPieces.remove(piece);
				return true;
			}
		}
		return false;
	}
	
	public List<Piece> getPieces(){
        return myPieces;
    }
}