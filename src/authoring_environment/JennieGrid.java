package authoring_environment;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import authoring.data.PatchData;
import authoring.data.PieceData;

/**
 * The JennieGrid is a hexagonal grid.
 * 
 * @author Jennie Ju
 *
 */
public class JennieGrid extends Pane {
	private int myRows;
	private int myCols;
	private int myTileSize;
	
	private PieceData myPieceData;
	private PatchData myPatchData;
	private List<List<SandyTile>> myGrid;
	
	public JennieGrid(int cols, int rows, int tileSize,
			PieceData pieceDat, PatchData patchDat) {
		myRows = rows;
		myCols = cols;
		myTileSize = tileSize;
		
		myPieceData = pieceDat;
		myPatchData = patchDat;
		
		initGridTiles(myGrid);
	}
	
	private void initGridTiles(List<List<SandyTile>> grid){
		grid = new LinkedList<List<SandyTile>>();
		for (int r = 0; r < myRows; r++) {
			List<SandyTile> tileCol = new LinkedList<SandyTile>();
			for (int c = 0; c < myCols; c++) {
				Shape bgShape = makeShape(r,c,myTileSize);
				Point2D loc = new Point2D.Double(c,r);
				SandyTile jt = new SandyTile(bgShape, myTileSize, loc);
				tileCol.add(jt);
				super.getChildren().add(jt);
			}
		grid.add(tileCol);
		}
	}
	
	private Shape makeShape(int row, int col, double h) {
		double r = h/Math.sqrt(3);
		double xCenter = col*1.5*r + r;
		double yCenter = row*h + (col%2)*0.5*h + 0.5*h;

		Polygon p = new Polygon(
				xCenter-r, yCenter,
				xCenter-0.5*r, yCenter+0.5*h,
				xCenter+0.5*r, yCenter+0.5*h,
				xCenter+r, yCenter,
				xCenter+0.5*r, yCenter-0.5*h,
				xCenter-0.5*r, yCenter-0.5*h
				);
		
		setCheckeredColor(row, col, p);
		return p;
	}
	
	private void setCheckeredColor(int row, int col, Shape shape) {
		if (((row % 2 == 0) && (col % 2 == 0)) || 
				((row % 2 == 1) && (col % 2 == 1))) {
            shape.setFill(Color.WHITE);
        }
        else {
            shape.setFill(Color.WHITESMOKE);
        }
		
		shape.setStroke(Color.GRAY);
		shape.setStrokeWidth(0.75);
	}
	
}
