package authoring.data;

import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	public void add(Piece p) {
		myPieces.add(p);
	}
    
    public List<Point2D> replace(Piece pieceType) {
    	List<Point2D> pointsToReplace = new ArrayList<Point2D>();
    	myPieces.forEach(piece -> {
    		if (piece.getID().equals(pieceType.getID())) {
    			replace(piece, pieceType);
    			pointsToReplace.add(piece.getLoc());
    		}
    	});
    	return pointsToReplace;
    }
    
	@Override
	public void replace(Piece origEl, Piece newEl) {
		origEl.setName(newEl.getName());
		origEl.setImageLocation(newEl.getImageLocation());
	}
    
	@Override
	public void remove(Piece p) {
		myPieces.remove(p);
	}

	@Override
	public List<Piece> getData() {
		return myPieces;
	}
	
	public List<Point2D> removeUnknown(Set<String> idSet) {
		List<Piece> piecesToRemove = new ArrayList<Piece>();
		List<Point2D> pointsToRemove = new ArrayList<Point2D>();
		for (Piece piece : myPieces) {
    		if (!idSet.contains(piece.getID())) {
				piecesToRemove.add(piece);
				piece.getImageView().setImage(null);
    			pointsToRemove.add(piece.getLoc());
    		}
		}
		myPieces.remove(piecesToRemove);
		return pointsToRemove;
	}
	
	public void removePieceAtLoc(Point2D location){
		for(Piece piece : myPieces){
			if(location.equals(piece.getLoc())){
				myPieces.remove(piece);
				return;
			}
		}
	}
	
	// Can we also rename this?
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
}
