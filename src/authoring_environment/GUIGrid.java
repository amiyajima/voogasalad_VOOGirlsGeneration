package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Authoring, engine, and player may all use this grid!!
 * 
 * @author Jennie Ju
 *
 */
public class GUIGrid extends SuperGrid implements Observer {

	private List<Piece> myPieceData;
	private List<Patch> myPatchData;

	public GUIGrid() {
		super();
	}

	public GUIGrid(int cols, int rows, int tileSize, String shape) {
		super(cols, rows, tileSize, shape);
		myPieceData = new LinkedList<Piece>();
		myPatchData = new LinkedList<Patch>();
	}

	public void addPiece(Piece pieceType, Point2D loc) {
		// create new instance of pieceType with this location
		// add to PieceData and set image within tile at this location
	}

	public void addPatch(Patch patchType, Point2D loc) {
		// create new instance of patchType with this location
		// add to PatchData and set image within tile at this location
	}

	public void removePiece(Piece p) {
		// remove piece at this location from data and tiles
	}

	public void removePatch(Patch p) {
		// remove patch at this location from data and tiles
	}

	public void removePiece(Point2D loc) {
		// remove piece at this location from data and tiles
	}

	public void removePatch(Point2D loc) {
		// remove patch at this location from data and tiles
	}

	public void removePieceType(Piece pieceType) {
		// remove all instances of this type of piece
	}

	public void removePatchType(Patch patchType) {
		// remove all instances of this type of patch
	}

	public List<Piece> getPiece(Piece p) {
		return null;
	}

	public List<Patch> getPatche(Patch p) {
		return null;
	}
	
	public List<Piece> getPiece(int x, int y) {
		return null;
	}

	public List<Patch> getPatche(int x, int y) {
		return null;
	}

	public List<Piece> getPieces() {
		return myPieceData;
	}

	public List<Patch> getPatches() {
		return myPatchData;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
