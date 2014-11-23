package authoring_environment;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javafx.event.EventHandler;
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
		
		initGridTiles(myGrid);
	}
	
	private void initGridTiles(List<List<SandyTile>> grid){
		grid = new LinkedList<List<SandyTile>>();
		for (int r = 0; r < myRows; r++) {
			List<SandyTile> tileCol = new LinkedList<SandyTile>();
			for (int c = 0; c < myCols; c++) {
				Shape bgShape = makeShape(r,c,myTileSize);
				Point2D loc = new Point2D.Double(c,r);
				SandyTile tile = new SandyTile(bgShape, myTileSize, loc);
				setClickEvent(tile);
				tileCol.add(tile);
				super.getChildren().add(tile);
			}
		grid.add(tileCol);
		}
	}
	
	private Shape makeShape(int row, int col, double h) {
		double xCenter = col*h + 0.5*h;
		double yCenter = row*h + 0.5*h;
		Polygon p = new Polygon (
				xCenter-0.5*h, yCenter-0.5*h,
				xCenter-0.5*h, yCenter+0.5*h,
				xCenter+0.5*h, yCenter+0.5*h,
				xCenter+0.5*h, yCenter-0.5*h
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
	
	private void setClickEvent(SandyTile tile) {
		tile.setStyle("-fx-cursor: hand");
		tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				System.out.println(tile.getXLocation() + " " + tile.getYLocation());
			}
		});
	}
}