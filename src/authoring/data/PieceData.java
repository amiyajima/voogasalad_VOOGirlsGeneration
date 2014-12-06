package authoring.data;

import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Class for storing the each type of piece created by the user.
 * Stored in the game authoring environment.
 * 
 * @author Martin Tamayo
 */
public class PieceData implements AuthoringData<Piece>, Observer {
	
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
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof PieceTypeData) {
			List<Piece> toRemove = new ArrayList<Piece>();
			PieceTypeData typeData = (PieceTypeData) o;
			for (Piece p : myPieces) {
				if (!typeData.containsName(p.getName())) {
					toRemove.add(p);
				}
			}
			
			myPieces.removeAll(toRemove);

			if (arg instanceof Piece) {
				Piece patchType = (Piece) arg;
				for (Piece p : myPieces) {
					replace(p, patchType);
				}
			}
		}
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