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
	
	public SandyTile findTile(MouseEvent event) {
		int x = (int) event.getX() / myTileSize;
		int y = (int) event.getY() / myTileSize;
		if(x < 0 || x >= myCols || y < 0 || y >= myRows){
			return null;
		}
		return myGrid.get(y).get(x);
	}

	protected void addUnit(SandyTile tile, Piece unit){
		myPieceData.removePiece(tile.getLocation());
		unit.setLoc(tile.getLocation());
		tile.myPieceImage.setImage(unit.getImageView().getImage());
		tile.myPieceImage.setVisible(true);
		myPieceData.add(unit);
	}
	
	protected void addTerrain(SandyTile tile, Patch terrain){
		myPatchData.removePatch(tile.getLocation());
		terrain.setLoc(tile.getLocation());
		tile.myPatchImage.setImage(terrain.getImageView().getImage());
		tile.myPatchImage.setVisible(true);
		myPatchData.add(terrain);
	}
	
	protected void removeUnit(SandyTile tile, Piece unit) {
		tile.myPieceImage.setVisible(false);
		myPieceData.remove(unit);
	}
	
	protected void removeTerrain(SandyTile tile, Patch terrain) {
		tile.myPatchImage.setVisible(false);
		myPatchData.remove(terrain);
	}
	
	protected void editUnit(SandyTile tile, Piece unit) {
		// TO DO: Open individual unit editor.
	}
	
	protected void editTerrain(SandyTile tile, Patch terrain) {
		// TO DO: Open individual terrain editor.
	}
	
	public void removePieces(Piece unit) {
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myCols; c++) {
				if(myPieceData.unitAtLoc(unit, c, r)){
					SandyTile tile = myGrid.get(r).get(c);
					tile.myPieceImage.setVisible(false);
				}
			}
		}
	}
	
	public void removePatches(Patch terrain) {
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myCols; c++) {
				if(myPatchData.terrainAtLoc(terrain, c, r)){
					SandyTile tile = myGrid.get(r).get(c);
					tile.myPatchImage.setVisible(false);
				}
			}
		}
	}
}