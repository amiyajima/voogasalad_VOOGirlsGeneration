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
public class PieceInstanceData implements AuthoringData<Piece> {
	
    private List<Piece> myPieces;
    
    /**
     * Constructor for new PieceData,
     * initializes empty list of Pieces.
     */
    public PieceInstanceData() {
        myPieces = new LinkedList<Piece>();
    }
    
    /**
     * Constructor for piece from already existent data, aka for game data
     * @param pieces
     */
    public PieceInstanceData(List<Piece> pieces) {
        myPieces = pieces;
    }
    
    @Override
	public void add(Piece p) {
    	//removePieceAtLoc(p.getLoc());
		myPieces.add(p);
	}
    
	@Override
	public boolean canAdd(Piece element) {
		String elementID = element.getID();
		Point2D.Double elementLoc = element.getLoc();
		for (Piece piece : myPieces){
			String id = piece.getID();
			Point2D.Double loc = piece.getLoc();
			if ((elementID.equals(id)) && (elementLoc.equals(loc))){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void remove(Piece p) {
		myPieces.remove(p);
	}
    
	@Override
	public void replace(Piece origEl, Piece newEl) {
		remove(origEl);
		add(new Piece(newEl, origEl.getLoc()));
	}

	@Override
	public List<Piece> getData() {
		return myPieces;
	}
    
    public List<Point2D.Double> replace(Piece pieceType) {
    	List<Point2D.Double> pointsToReplace = new ArrayList<Point2D.Double>();
    	myPieces.forEach(piece -> {
    		if (piece.getID().equals(pieceType.getID())) {
    			replace(piece, pieceType);
    			pointsToReplace.add(piece.getLoc());
    		}
    	});
    	return pointsToReplace;
    }
	
	public List<Point2D.Double> removeUnknown(Set<String> idSet) {
		List<Piece> piecesToRemove = new ArrayList<Piece>();
		List<Point2D.Double> pointsToRemove = new ArrayList<Point2D.Double>();
		for (Piece piece : myPieces) {
    		if (!idSet.contains(piece.getID())) {
				piecesToRemove.add(piece);
				piece.getImageView().setImage(null);
    			pointsToRemove.add(piece.getLoc());
    		}
		}
		myPieces.removeAll(piecesToRemove);
		return pointsToRemove;
	}
	
	public void removePieceAtLoc(Point2D.Double location){
		for(Piece piece : myPieces){
			if(location.equals(piece.getLoc())){
				myPieces.remove(piece);
				return;
			}
		}
	}
	
	// Can we also rename this?
	public boolean unitAtLoc(Piece unit, int x, int y){
		Point2D.Double location = new Point2D.Double(x, y);
		for(Piece piece : myPieces){
			if(location.equals(piece.getLoc()) && unit.toString().equals(piece.toString())){
				myPieces.remove(piece);
				return true;
			}
		}
		return false;
	}
}
