package gamedata.gamecomponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public abstract class Grid {
	private int myRow;
	private int myColumn;

	protected Map<Point2D, Patch> myPatches;
	protected Map<Point2D, Piece> myPieces;
	private static final int DEFAULT_PATCH_STATE = 1;
	private static final int DEFAULT_PATCH_ID = 1;
	private static final String DEFAULT_PATCH_IMAGE_LOCATION = "";

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
						DEFAULT_PATCH_ID, DEFAULT_PATCH_IMAGE_LOCATION,
						new Point2D(x, y));
				myPatches.put(new Point2D(x, y), patch);
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

	public Piece getPiece(int id) {
		for (Map.Entry<Point2D, Piece> entry : myPieces.entrySet()) {
			if (entry.getValue().getUniqueID() == id)
				return entry.getValue();
		}
		return null;
	}

	public List<Piece> getAllPieces() {
		List<Piece> all = new ArrayList<Piece>();
		for (Map.Entry<Point2D, Piece> entry : myPieces.entrySet()) {
			all.add(entry.getValue());
		}
		return all;
	}

}