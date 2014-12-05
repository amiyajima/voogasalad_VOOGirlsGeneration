package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Authoring, engine, and player may all use this grid!!
 * 
 * @author Jennie Ju
 *
 */
public class GUIGrid extends SuperGrid {

	private List<Piece> myPieceData;
	private List<Patch> myPatchData;

	public GUIGrid(int cols, int rows, int tileSize, String shape) {
		super(cols,rows,tileSize,shape);
		myPieceData = new LinkedList<Piece>();
		myPatchData = new LinkedList<Patch>();
	}

	protected void addPiece(Piece pieceType, Point2D loc) {
		// create new instance of pieceType with this location
		// add to PieceData and set image within tile at this location
	}

	protected void addPatch(Patch patchType, Point2D loc) {
		// create new instance of patchType with this location
		// add to PatchData and set image within tile at this location
	}

	protected void removePiece(Point2D loc) {
		// remove piece at this location from data and tiles
	}
	
	protected void removePatch(Point2D loc) {
		// remove patch at this location from data and tiles
	}
	
	protected void removePieceType(Piece pieceType) {
		// remove all instances of this type of piece
	}
	
	protected void removePatchType(Patch patchType) {
		// remove all instances of this type of patch
	}

}
