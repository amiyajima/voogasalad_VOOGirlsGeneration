package gamedata.gamecomponents;

import java.awt.geom.Point2D;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.ImageView;

/**
 * Contains the Grid defined for a level. Contains the pieces and patches
 * 
 */
public abstract class Grid {
	private int myRow;
	private int myColumn;

	protected Map<Point2D, Patch> myPatches;
	protected Map<Point2D, Piece> myPieces;
	private static final int DEFAULT_PATCH_STATE = 1;
	private static final int DEFAULT_PATCH_ID = 1;
	private static final ImageView DEFAULT_PATCH_IMAGE = null;

	public Grid() {
		this(1, 1);
	}

	public Grid(int row, int column) {
		row = myRow;
		column = myColumn;
		myPatches = new HashMap<Point2D, Patch>();
		myPieces = new HashMap<Point2D, Piece>();

		setGrid();

	}

	// set up grid by initializing patches on it
	private void setGrid() {
		for (int x = 0; x < myColumn; x++) {
			for (int y = 0; y < myRow; y++) {
				Patch patch = new SquarePatch(DEFAULT_PATCH_STATE,
						DEFAULT_PATCH_ID, DEFAULT_PATCH_IMAGE,
						new Point2D.Double(x, y));
				myPatches.put(new Point2D.Double(x, y), patch);
			}
		}
	}

	public void setPiece(Piece piece, Point2D coord) {
		for (Point2D coordinate : myPieces.keySet()) {
			if (coordinate.equals(coord)) {
				myPieces.put(coord, piece);
			}
		}
	}

	public void setPatch(Patch patch, Point2D coord) {
		for (Point2D coordinate : myPieces.keySet()) {
			if (coordinate.equals(coord)) {
				myPatches.put(coord, patch);
			}
		}
	}

	public Patch getPatch(Point2D coord) {
		for (Point2D coordinate : myPatches.keySet()) {
			{
				if (coordinate.equals(coord)) {
					return myPatches.get(coord);
				}
			}
		}
		// no patch exists on such coord
		return null;
	}

	public Piece getPiece(Point2D coord) {
		for (Point2D coordinate : myPieces.keySet()) {
			{
				if (coordinate.equals(coord)) {
					return myPieces.get(coord);
				}
			}
		}
		// no piece exists on such coord
		return null;
	}

	public void removePiece(Point2D coord) {
		Piece piece = myPieces.get(coord);
		myPieces.remove(coord, piece);
	}

	public void removePatch(Point2D coord) {
		Patch patch = myPatches.get(coord);
		myPatches.remove(coord, patch);
	}

	public int getColumn() {
		return myColumn;
	}

	public int getRow() {
		return myRow;
	}

	public void draw() {

	}

	/**
	 * Returns a Piece of a given ID
	 * 
	 * @param id
	 * @return
	 */
	public Piece getPiece(int id) {
		for (Map.Entry<Point2D, Piece> entry : myPieces.entrySet()) {
			if (entry.getValue().getUniqueID() == id)
				return entry.getValue();
		}
		return null;
	}

	/**
	 * Returns a list of all pieces
	 * 
	 * @return
	 */
	public List<Piece> getAllPieces() {
		List<Piece> all = new ArrayList<Piece>();
		for (Map.Entry<Point2D, Piece> entry : myPieces.entrySet()) {
			all.add(entry.getValue());
		}
		return all;
	}

	/**
	 * Returns a list of all patches
	 * 
	 * @return
	 */
	public List<Patch> getAllPatches() {
		List<Patch> all = new ArrayList<Patch>();
		for (Map.Entry<Point2D, Patch> entry : myPatches.entrySet()) {
			all.add(entry.getValue());
		}
		return all;
	}
	
	public Map<Point2D, Patch> getPatches(){
	    return myPatches;
	}
        
	
	
	public Map<Point2D, Piece> getPieces(){
	    return myPieces;
	}
	
	
	
	
	
	
	

}
