package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import authoring.data.PatchData;
import authoring.data.PatchTypeData;
import authoring.data.PieceData;
import authoring.data.PieceTypeData;

// TODO: REMOVE THE DUPLICATED CODE. SO MUCH.
/**
 * Authoring, engine, and player may all use this grid!!
 * 
 * @author Jennie Ju
 *
 */

public class GUIGrid extends SuperGrid implements Observer {

	private PieceData myPieceData;
	private PatchData myPatchData;

	/**
	 * Default constructor for GUIGrid. Only for testing purposes.
	 */
	public GUIGrid () {
		super();
	}

	/**
	 * Constructor for a new GUIGrid
	 * 
	 * @param cols - number of columns (NOTE: cols = X direction)
	 * @param rows - number of rows (NOTE: rows = Y direction)
	 * @param tileHeight - height of tiles in pixels
	 * @param shape - shape of the grid given as string identifier in properties
	 */
	public GUIGrid (int cols, int rows, double tileHeight, String shape) {
		super(cols, rows, tileHeight, shape);
		myPieceData = new PieceData();
		myPatchData = new PatchData();
	}

	/**
	 * Constructor for a new GUIGrid given saved PieceData and PatchData
	 * Specifically for JSON and game loading
	 * 
	 * @param cols - number of columns (NOTE: cols = X direction)
	 * @param rows - number of rows (NOTE: rows = Y direction)
	 * @param tileHeight - height of tiles in pixels
	 * @param shape - shape of the grid given as string identifier in properties
	 * @param pieceData - saved pieceData
	 * @param patchData - saved patchData
	 */
	public GUIGrid (int cols, int rows, double tileHeight, String shape,
			PieceData pieceData, PatchData patchData) {
		super(cols, rows, tileHeight, shape);
		myPieceData = pieceData;
		myPatchData = patchData;
		//TODO: repopulate grid
	}

	/**
	 * Constructor for make a new GUIGrid after editing
	 * a GUIGrid's dimensions or size
	 * 
	 * @param cols - number of columns (NOTE: cols = X direction)
	 * @param rows - number of rows (NOTE: rows = Y direction)
	 * @param tileHeight - height of tiles in pixels
	 * @param shape - shape of the grid given as string identifier in properties
	 * @param pieceData - saved pieceData
	 * @param patchData - saved patchData
	 */
	public GUIGrid (int cols, int rows, double tileHeight, String shape,
			GUIGrid copyGrid) {
		super(cols, rows, tileHeight, shape);
		myPieceData = copyGrid.myPieceData;
		myPatchData = copyGrid.myPatchData;
		//TODO: repopulate grid and remove runoff pieces/patches
	}

	/**
	 * Returns number of rows
	 * 
	 * @return int number of rows
	 */
	public int getNumRows () {
		return super.myNumRows;
	}

	/**
	 * Returns number of columns
	 * 
	 * @return int number of columns
	 */
	public int getNumCols () {
		return super.myNumCols;
	}


	public void addPiece (Piece pieceType, Point2D loc) {
		Piece clone = new Piece(pieceType, loc);
		if (isPieceOccupied(loc)){
			removePieceAtCoordinate(loc);
		}
		myPieceData.add(clone);
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPieceImage(clone.getImageView());
	}

	public void addPatch (Patch patchType, Point2D loc) {
		Patch clone = new Patch(patchType, loc);
		if (isPatchOccupied(loc)){
			removePatchAtCoordinate(loc);
		}
		myPatchData.add(clone);
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPatchImage(clone.getImageView());
	}

	public boolean isPatchOccupied (Point2D loc){
		for (Patch p: myPatchData.getData()){
			if (p.getLoc().equals(loc)){
				return true;
			}
		}
		return false;
	}

	public boolean isPieceOccupied (Point2D loc){
		for (Piece p: myPieceData.getData()){
			if (p.getLoc().equals(loc)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Removes a piece at the given coordinates.
	 * NOTE: Point2D coordinates given as
	 * X = column number, Y = row number
	 * 
	 * @param coor - Point2D containing coordinates of
	 *        the piece to remove given as [Col, Row]
	 */
	public void removePieceAtCoordinate (Point2D coor) {
		Piece toRemove = getPiece(coor);
		removePiece(toRemove);
	}

	/**
	 * Removes a piece at the given coordinates.
	 * NOTE: Point2D coordinates given as
	 * X = column number, Y = row number
	 * 
	 * @param coor - Point2D containing coordinates of
	 *        the piece to remove given as [Col, Row]
	 */
	public void removePatchAtCoordinate (Point2D coor) {
		Patch toRemove = getPatch(coor);
		//TODO: remove only if there is a patch to be removed
		//to avoid nullerpt..
		if (isPatchOccupied(coor)){
			removePatch(toRemove);
		}
	}

	private void replacePieceType (Piece pieceType) {
		List<Point2D> pointsToReplace = myPieceData.replace(pieceType);
		for (Point2D loc : pointsToReplace) {
			SuperTile tile = super.findTile(loc);
			tile.setPieceImage(pieceType.getImageView());
		}
	}

	private void replacePatchType (Patch patchType) {
		List<Point2D> pointsToReplace = myPatchData.replace(patchType);
		for (Point2D loc : pointsToReplace) {
			SuperTile tile = super.findTile(loc);
			tile.setPatchImage(patchType.getImageView());
		}
	}

	private void removePieceType (PieceTypeData typeData) {
		List<Point2D> pointsToRemove = myPieceData.removeUnknown(typeData.getIdSet());
		for (Point2D loc : pointsToRemove) {
			SuperTile tile = super.findTile(loc);
			tile.removePieceImage();
		}
	}

	private void removePatchType (PatchTypeData typeData) {
		List<Point2D> pointsToRemove = myPatchData.removeUnknown(typeData.getIdSet());
		for (Point2D loc : pointsToRemove) {
			SuperTile tile = super.findTile(loc);
			tile.removePatchImage();
		}
	}

	/**
	 * Returns the piece at loc
	 * 
	 * @param loc
	 * @return
	 */
	public Piece getPiece (Point2D loc) {
		for (Piece p : myPieceData.getData()) {
			if ((p.getLoc().equals(loc))) { return p; }
		}
		return null;
	}

	/**
	 * Returns the patch at loc
	 * 
	 * @param loc
	 * @return
	 */
	public Patch getPatch (Point2D loc) {
		for (Patch p : myPatchData.getData()) {
			if ((p.getLoc().equals(loc))) { return p; }
		}
		return null;
	}

	public void removePiece (Piece p) {
		SuperTile currentTile = findTile(p.getLoc());
		myPieceData.remove(p);
		currentTile.removePieceImage();
	}

	public void removePatch (Patch p) {
		myPatchData.remove(p);
		SuperTile currentTile = findTile(p.getLoc());
		currentTile.removePatchImage();
	}

	/**
	 * Gets Pieces that have been tagged for removal
	 * 
	 * @return
	 */
	public List<Piece> getRemovedPieces () {
		List<Piece> l = new ArrayList<Piece>();
		for (Piece p : myPieceData.getData()) {

			// TODO: FOR TESTING ONLY
			if (p.getStats().getValue("health") <= 0) {
				p.markForRemoval();
			}

			if (p.shouldRemove()) {
				l.add(p);
			}
		}
		return l;
	}

	/**
	 * Returns all pieces that belong to a given player
	 * 
	 * @param playerId
	 *        - ID of player
	 * @return List of pieces belonging to the player
	 */
	public List<Piece> getPlayerPieces (int playerId) {
		List<Piece> l = new ArrayList<Piece>();
		for (Piece p : myPieceData.getData()) {
			if (p.getPlayerID() == playerId) {
				l.add(p);
			}
		}
		return l;
	}

	public void repopulateGrid () {
		this.initGridTiles(this.myShape);
		for (Patch p : myPatchData.getData()) {
			this.addPatchToTile(p, p.getLoc());
		}
		for (Piece p : myPieceData.getData()) {
			this.addPieceToTile(p, p.getLoc());
		}
	}

	@Override
	public void update (Observable o, Object arg) {
		if (o instanceof PieceTypeData) {
			PieceTypeData typeData = (PieceTypeData) o;
			if (arg == null) {
				removePieceType(typeData);
			}
			if (arg instanceof Piece) {
				Piece pieceType = (Piece) arg;
				replacePieceType(pieceType);
			}
		}
		if (o instanceof PatchTypeData) {
			PatchTypeData typeData = (PatchTypeData) o;
			if (arg == null) {
				removePatchType(typeData);
			}
			if (arg instanceof Patch) {
				Patch patchType = (Patch) arg;
				replacePatchType(patchType);
			}
		}
	}

	public void addPieceToTile (Piece pieceType, Point2D loc) {
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPieceImage(pieceType.getImageView());

	}

	public void addPatchToTile (Patch patchType, Point2D loc) {
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPatchImage(patchType.getImageView());
	}

	// TODO: separate the two types of mouse events (drag and click)
	public void paneSetOnMousePressed (EventHandler<MouseEvent> handler) {
		myPane.setOnMousePressed(handler);
	}

	public void paneSetOnMouseDragged(EventHandler<MouseEvent> handler) {
		myPane.setOnMouseDragged(handler);
	}

	/**
	 * Get the whole list of Pieces and Patches in this level. Read by Conditions and
	 * modified by Global Actions (e.g., adding/removing Pieces).
	 * @author MIKE ZHU
	 * @return
	 */
	public List<Piece> getReadOnlyPieceList () {
		return Collections.unmodifiableList(myPieceData.getData());
	}

	public List<Patch> getReadOnlyPatchList () {
		return Collections.unmodifiableList(myPatchData.getData());
	}
}
