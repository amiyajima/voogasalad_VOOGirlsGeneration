package authoring_environment;

import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.BiConsumer;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import authoring.data.PatchInstanceData;
import authoring.data.PatchTypeData;
import authoring.data.PieceInstanceData;
import authoring.data.PieceTypeData;

// TODO: REMOVE THE DUPLICATED CODE. SO MUCH.
/**
 * Authoring and player both use this grid
 * 
 * @author Jennie Ju
 *
 */

public class GUIGrid extends SuperGrid implements Observer{

	private PieceInstanceData myPieceData;
	private PatchInstanceData myPatchData;

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
		myPieceData = new PieceInstanceData();
		myPatchData = new PatchInstanceData();
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
			PieceInstanceData pieceData, PatchInstanceData patchData) {
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
	 * @param copyGrid - Grid whose data needs to be copied in
	 */
	public GUIGrid (int cols, int rows, double tileHeight, String shape,
			GUIGrid copyGrid) {
		super(cols, rows, tileHeight, shape);
		myPieceData = copyGrid.myPieceData;
		myPatchData = copyGrid.myPatchData;
		removeRunOffData(cols, rows);
		repopulate();
	}

	public void addPieceAtLoc (Piece pieceType, Point2D.Double loc) {
		Piece clone = new Piece(pieceType, loc);
		if (isPieceOccupied(loc)){
			removePieceAtCoordinate(loc);
		}
		myPieceData.add(clone);
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPieceImage(clone.getImageView());
	}

	public void addPatchAtLoc (Patch patchType, Point2D.Double loc) {
		Patch clone = new Patch(patchType, loc);
		if (isPatchOccupied(loc)){
			removePatchAtCoordinate(loc);
		}
		myPatchData.add(clone);
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPatchImage(clone.getImageView());
	}
	
	public boolean isPieceOccupied (Point2D.Double loc){
		for (Piece p: myPieceData.getData()){
			if (p.getLoc().equals(loc)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isPatchOccupied (Point2D.Double loc){
		for (Patch p: myPatchData.getData()){
			if (p.getLoc().equals(loc)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes a piece at the given coordinates.
	 * NOTE: Point2D.Double coordinates given as
	 * X = column number, Y = row number
	 * 
	 * @param coor - Point2D.Double containing coordinates of
	 *        the piece to remove given as [Col, Row]
	 */
	public void removePieceAtCoordinate (Point2D.Double coor) {
		Piece toRemove = getPiece(coor);
		if(isPieceOccupied(coor)){
			removePiece(toRemove);
		}
	}

	/**
	 * Removes a piece at the given coordinates.
	 * NOTE: Point2D.Double coordinates given as
	 * X = column number, Y = row number
	 * 
	 * @param coor - Point2D.Double containing coordinates of
	 *        the piece to remove given as [Col, Row]
	 */
	public void removePatchAtCoordinate (Point2D.Double coor) {
		Patch toRemove = getPatch(coor);
		//TODO: remove only if there is a patch to be removed
		//to avoid nullerpt..
		if(isPatchOccupied(coor)){
			removePatch(toRemove);
		}
	}

	private void replacePieceType (Piece pieceType) {
		List<Point2D.Double> pointsToReplace = myPieceData.replace(pieceType);
		for (Point2D.Double loc : pointsToReplace) {
			SuperTile tile = super.findTile(loc);
			tile.setPieceImage(pieceType.getImageView());
		}
	}

	private void replacePatchType (Patch patchType) {
		List<Point2D.Double> pointsToReplace = myPatchData.replace(patchType);
		for (Point2D.Double loc : pointsToReplace) {
			SuperTile tile = super.findTile(loc);
			tile.setPatchImage(patchType.getImageView());
		}
	}

	private void removePieceType (PieceTypeData typeData) {
		List<Point2D.Double> pointsToRemove = myPieceData.removeUnknown(typeData.getIdSet());
		for (Point2D.Double loc : pointsToRemove) {
			SuperTile tile = super.findTile(loc);
			tile.removePieceImage();
		}
	}

	private void removePatchType (PatchTypeData typeData) {
		List<Point2D.Double> pointsToRemove = myPatchData.removeUnknown(typeData.getIdSet());
		for (Point2D.Double loc : pointsToRemove) {
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
	public Piece getPiece (Point2D.Double loc) {
		for (Piece p : myPieceData.getData()) {
			if ((p.getLoc().equals(loc))) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Returns the patch at loc
	 * 
	 * @param loc
	 * @return
	 */
	public Patch getPatch (Point2D.Double loc) {
		for (Patch p : myPatchData.getData()) {
			if ((p.getLoc().equals(loc))) {
				return p;
			}
		}
		return null;
	}

	public void removePiece (Piece p) {
		myPieceData.remove(p);
		SuperTile currentTile = findTile(p.getLoc());
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
	
	//CHANGE THESE
	public void repopulate() {
		for (Patch p : myPatchData.getData()) {
			this.addPatchToTile(p, p.getLoc());
		}
		for (Piece p : myPieceData.getData()) {
			this.addPieceToTile(p, p.getLoc());
		}
	}
	
	public void removeRunOffData(int numCols, int numRows) {
		List<Piece> piecesToRemove = new ArrayList<Piece>();
		for (Piece piece : myPieceData.getData()) {
			if (piece.getLoc().getX() > numCols - 1 
					|| piece.getLoc().getY() > numRows - 1) {
				piecesToRemove.add(piece);
			}
		}
		
		myPieceData.getData().removeAll(piecesToRemove);
		
		List<Patch> patchesToRemove = new ArrayList<Patch>();
		for (Patch patch : myPatchData.getData()) {
			if ((patch.getLoc().getX() > numCols - 1 )
					|| (patch.getLoc().getY() > numRows - 1)) {
				patchesToRemove.add(patch);
			}
		}
		myPatchData.getData().removeAll(patchesToRemove);
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

	private void addPieceToTile (Piece pieceType, Point2D.Double loc) {
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPieceImage(pieceType.getImageView());

	}

	private void addPatchToTile (Patch patchType, Point2D.Double loc) {
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.setPatchImage(patchType.getImageView());
	}

	// TODO: separate the two types of mouse events (drag and click)
	public void paneSetOnMousePressed (EventHandler<MouseEvent> handler) {
		myPane.setOnMousePressed(handler);
		myPane.setOnMousePressed(null);
	}
	
	public void paneSetOnMouseDragged (EventHandler<MouseEvent> handler) {
		myPane.setOnMouseDragged(handler);
	}
	
	public void runEvent(BiConsumer<List<IHasStats>, GUIGrid> eventFunc){
		List<IHasStats> allObjects = new ArrayList<>();
		allObjects.addAll(myPieceData.getData());
		allObjects.addAll(myPatchData.getData());
		
		eventFunc.accept(allObjects, this);
	}
	
	public PatchInstanceData getPatchData() {
	    return myPatchData;
	}
	
	public String toString() {
	    List<Patch> myPatches = myPatchData.getData();
	    List<Piece> myPieces = myPieceData.getData();
	    String myString = "\nGrid\n";
	    int size = myPatches.size();
	    if (myPieces.size() < myPatches.size()) {
	        size = myPieces.size();
	    }
	    for (int i = 0; i < size; i++) {
	        myString += myPatches.get(i).toString() + "\n";
	        myString += myPieces.get(i).toString() + "\n";
	    }
	    return myString;
	}
}
