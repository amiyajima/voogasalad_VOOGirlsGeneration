package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import authoring.data.PatchData;
import authoring.data.PieceData;

/**
 * The SandyGrid is a square grid.
 * 
 * @author Jennie Ju
 */
public class SandyGrid extends Pane {
	private int myRows;
	private int myCols;
	private int myTileSize;
	
	private PieceData myPieceData;
	private PatchData myPatchData;
	private List<List<SandyTile>> myGrid;
	
	public SandyGrid(int cols, int rows, int tileSize, PieceData pieceData, PatchData patchData) {
		myRows = rows;
		myCols = cols;
		myTileSize = tileSize;
		
		myPieceData = pieceData;
		myPatchData = patchData;
		
		initGridTiles();
	}
	
	private void initGridTiles(){
		myGrid = new LinkedList<List<SandyTile>>();
		for (int r = 0; r < myRows; r++) {
			List<SandyTile> tileCol = new LinkedList<SandyTile>();
			for (int c = 0; c < myCols; c++) {
				Point2D centerLoc = getCenter(r, c, myTileSize);
				Shape bgShape = makeShape(centerLoc, r, c, myTileSize);
				Point2D coor = new Point2D.Double(c,r);
				SandyTile tile = new SandyTile(bgShape, myTileSize, coor, centerLoc);
				tile.setStyle("-fx-cursor: hand");
				tileCol.add(tile);
				super.getChildren().add(tile);
			}
			myGrid.add(tileCol);
		}
	}
	
	private Point2D getCenter(int row, int col, double height) {
		double xCenter = col*height + 0.5*height;
		double yCenter = row*height + 0.5*height;
		return new Point2D.Double(xCenter, yCenter);
	}

	private Shape makeShape(Point2D center, int row, int col, double height) {
		double xCenter = center.getX();
		double yCenter = center.getY();
		Polygon p = new Polygon (
				xCenter-0.5*height, yCenter-0.5*height,
				xCenter-0.5*height, yCenter+0.5*height,
				xCenter+0.5*height, yCenter+0.5*height,
				xCenter+0.5*height, yCenter-0.5*height
				);
		setCheckeredColor(row, col, p);
		return p;
	}
	
	private void setCheckeredColor(int row, int col, Shape shape) {
		if (((row % 2 == 0) && (col % 2 == 0)) || ((row % 2 == 1) && (col % 2 == 1))) {
            shape.setFill(Color.WHITE);
		}
        else {
            shape.setFill(Color.WHITESMOKE);
        }
		shape.setStroke(Color.GRAY);
		shape.setStrokeWidth(0.75);
	}
	
	public void handleEvent(MouseEvent event, Piece currentUnit, Patch currentTerrain,
			boolean doNothing, boolean unitSelected, boolean reset) {
		int x = (int) event.getX() / myTileSize;
		int y = (int) event.getY() / myTileSize;
		SandyTile tile = myGrid.get(y).get(x);
		setContents(tile, currentUnit, currentTerrain, doNothing, unitSelected, reset);
	}

	protected void setContents(SandyTile tile, Piece currentUnit, Patch currentTerrain,
			boolean doNothing, boolean unitSelected, boolean reset) {
		if(doNothing){
			return;
		}
		if(reset) {
			if (unitSelected) {
				removeUnit(tile);
			}
			else {
				removeTerrain(tile);
			}
		}
		else {
			if(unitSelected) {
				if(tile.myUnit != null){
					myPieceData.remove(tile.myUnit);
				}
				tile.myUnit = currentUnit;
				tile.myUnit.setLoc(new Point2D.Double(tile.getYLocation(), tile.getXLocation()));
				tile.myPieceImage.setImage(tile.myUnit.getImageView().getImage());
				tile.myPieceImage.setVisible(true);
				myPieceData.add(tile.myUnit);
			}
			else {
				if(tile.myTerrain != null){
					myPatchData.remove(tile.myTerrain);
				}
				tile.myTerrain = currentTerrain;
				tile.myTerrain.setLoc(new Point2D.Double(tile.getYLocation(), tile.getXLocation()));
				tile.myPatchImage.setImage(tile.myTerrain.getImageView().getImage());
				tile.myPatchImage.setVisible(true);
				myPatchData.add(tile.myTerrain);
			}
		}
	}

	private void removeUnit(SandyTile tile) {
		myPieceData.remove(tile.myUnit);
		tile.myUnit = null;
		tile.myPieceImage.setVisible(false);
	}
	
	private void removeTerrain(SandyTile tile) {
		myPatchData.remove(tile.myTerrain);
		tile.myTerrain = null;
		tile.myPatchImage.setVisible(false);
	}

	public void removePieces(Piece unit) {
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myCols; c++) {
				SandyTile tile = myGrid.get(r).get(c);
				if(tile.myUnit != null && tile.myUnit.getTypeID() == unit.getTypeID()){
					removeUnit(tile);
				}
			}
		}
	}
	
	public void removePatches(Patch terrain) {
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myCols; c++) {
				SandyTile tile = myGrid.get(r).get(c);
				if(tile.myTerrain != null && tile.myTerrain.getTypeID() == terrain.getTypeID()){
					removeTerrain(tile);
				}
			}
		}
	}
}