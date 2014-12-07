package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import authoring.data.PatchData;
import authoring.data.PatchTypeData;
import authoring.data.PieceData;
import authoring.data.PieceTypeData;

/**
 * Authoring, engine, and player may all use this grid!!
 * 
 * @author Jennie Ju
 *
 */

public class GUIGrid extends SuperGrid implements Observer{


    private PieceData myPieceData;
    private PatchData myPatchData;

    public GUIGrid () {
        super();
    }

    public GUIGrid (int cols, int rows, double tileSize, String shape) {
        super(cols, rows, tileSize, shape);
        myPieceData = new PieceData();
        myPatchData = new PatchData();
    }

    public GUIGrid (int cols, int rows, double tileSize, String shape,
    		PieceData pieceData, PatchData patchData) {
    	super(cols, rows, tileSize, shape);
        myPieceData = pieceData;
        myPatchData = patchData;
    }
    
    /**
     * Returns number of rows
     * 
     * @return int number of rows
     */
    public int getNumRows () {
        return super.myHeight;
    }

    /**
     * Returns number of columns
     * 
     * @return int number of columns
     */
    public int getNumCols () {
        return super.myWidth;
    }

    // TODO: set image within tile at this location
    public void addPiece (Piece pieceType, Point2D loc) {
        Piece clone = new Piece(pieceType, loc);
        myPieceData.add(clone); 
        SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
        myTile.addPieceImage(clone.getImageView());
        
    }

    // TODO: set image within tile at this location
	public void addPatch (Patch patchType, Point2D loc) {
		System.out.println("addpatch");
		Patch clone = new Patch(patchType, loc);
		myPatchData.add(clone);
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.addPatchImage(clone.getImageView());
	}
	
	private void replacePiece(Piece pieceType) {
		List<Point2D> pointsToReplace = myPieceData.replace(pieceType);
		for (Point2D loc : pointsToReplace) {
			SuperTile tile = super.findClickedTile(loc);
			tile.addPatchImage(pieceType.getImageView());
		}
	}
	
	private void replacePatch(Patch patchType) {
		List<Point2D> pointsToReplace = myPatchData.replace(patchType);
		System.out.println(pointsToReplace.toString());
		for (Point2D loc : pointsToReplace) {
			SuperTile tile = super.findClickedTile(loc);
			tile.addPatchImage(patchType.getImageView());
		}
	}


	/**
	 * Returns the piece at loc
	 * @param loc
	 * @return
	 */
	public Piece getPiece (Point2D loc) {
		for (Piece p: myPieceData.getData()){
			if ((p.getLoc().getX()==loc.getX()) & (p.getLoc().getY()==loc.getY())){
				return p;
			}
		}
		return null;
	}

	/**
	 * Returns the patch at loc
	 * @param loc
	 * @return
	 */
	public Patch getPatch (Point2D loc) {
		for (Patch p: myPatchData.getData()){
			if ((p.getLoc().getX()==loc.getX()) & (p.getLoc().getY()==loc.getY())){
				return p;
			}
		}
		return null;
	}

	public void removePiece(Piece p){
		myPieceData.remove(p);
		
	}
//	public PieceData getPieces () {
//		return myPieceData;
//	}
//
//	public PatchData getPatches () {
//		return myPatchData;
//	}

	/**
	 * Gets Pieces that have been tagged for removal 
	 * @return
	 */
	public List<Piece> getRemovedPieces(){
		List<Piece> l = new ArrayList<Piece>();
		for(Piece p:myPieceData.getData()){
			
			//TODO: FOR TESTING ONLY
			if(p.getStats().getValue("health")<=0){
				p.markForRemoval();
			}
			
			if(p.shouldRemove()){
				l.add(p);
			}
		}
		return l;
	}
	
	/**
	 * Returns all pieces that belong to a given player
	 * @param id
	 * @return
	 */
	public List<Piece> getPlayerPieces(int id){
		List<Piece> l = new ArrayList<Piece>();
		for(Piece p:myPieceData.getData()){
			if(p.getPlayerID()==id){
				l.add(p);
			}
		}
		return l;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof PieceTypeData) {
			PieceTypeData typeData = (PieceTypeData) o;
			if (arg == null) {
				myPieceData.removeUnknown(typeData.getIdSet());
			}
			if (arg instanceof Piece) {
				Piece pieceType = (Piece) arg;
				replacePiece(pieceType);
			}
		}
		if (o instanceof PatchTypeData) {
			PatchTypeData typeData = (PatchTypeData) o;
			if (arg == null) {
				myPatchData.removeUnknown(typeData.getIdSet());
			}
			if (arg instanceof Patch) {
				System.out.println("I'M HERE");
				Patch patchType = (Patch) arg;
				replacePatch(patchType);
			}
		}
	}
	
	public <T> void paneSetOnMouseClicked(EventHandler<MouseEvent> handler) {
		myPane.setOnMouseClicked(handler);
	}
}
