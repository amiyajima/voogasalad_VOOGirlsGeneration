package gamedata.gamecomponents;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * creates square grid
 *
 */
public class SquareGrid extends Grid {
	private static final int DEFAULT_PATCH_STATE = 1;
	private static final int DEFAULT_PATCH_ID = 1;
	private static final ImageView DEFAULT_PATCH_IMAGE = null;

	/**
	 * Default constructor for square grid
	 */
	public SquareGrid() {
		super();
	}

	/**
	 * constructor of square grid
	 * 
	 * @param x
	 *            number of rows
	 * @param y
	 *            number of columns
	 */
	public SquareGrid(int x, int y) {
		super(x, y);
	}

	/**
	 * set up grid by initializing patches on it
	 */
	void setGrid() {
		for (int x = 0; x < super.getColumn(); x++) {
			for (int y = 0; y < super.getRow(); y++) {
				Patch patch = new SquarePatch(DEFAULT_PATCH_STATE,
						DEFAULT_PATCH_ID, DEFAULT_PATCH_IMAGE,
						new Point2D(x, y));
				super.myPatches.put(new Point2D(x, y), patch);
			}
		}
	}

	/**
	 * places a piece on the grid
	 * 
	 * @param piece
	 *            to be put on grid
	 * @param coord
	 *            of piece
	 */
	public void setPiece(Piece piece, Point2D coord) {
		for (Point2D coordinate : super.myPieces.keySet()) {
			if (coordinate.equals(coord)) {
				super.myPieces.put(coord, piece);
			}
		}
	}

	/**
	 * places a patch on the grid
	 * 
	 * @param patch
	 *            to be put on grid
	 * @param coord
	 *            of patch
	 */
	public void setPatch(Patch patch, Point2D coord) {
		for (Point2D coordinate : myPieces.keySet()) {
			if (coordinate.equals(coord)) {
				myPatches.put(coord, patch);
			}
		}
	}

	/**
	 * gets the patch on the given coordinate
	 * 
	 * @param coord
	 *            of patch
	 * @return patch
	 */
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

	/**
	 * gets the piece on the given coordinate
	 * 
	 * @param coord
	 *            of piece
	 * @return piece
	 */
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

	/**
	 * removes the piece on the given coordinate
	 * 
	 * @param coord
	 *            of piece
	 */
	public void removePiece(Point2D coord) {
		Piece piece = super.myPieces.get(coord);
		super.myPieces.remove(coord, piece);
	}

	/**
	 * removes the patch on the given coordinate
	 * 
	 * @param coord
	 *            of remove
	 */
	public void removePatch(Point2D coord) {
		Patch patch = myPatches.get(coord);
		myPatches.remove(coord, patch);
	}
}