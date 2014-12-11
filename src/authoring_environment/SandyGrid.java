package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import authoring.data.AuthoringPatchData;
import authoring.data.PieceData;

/**
 * The SandyGrid is a square grid.
 * 
 * @author Jennie Ju
 */
public class SandyGrid extends ShapeGrid {
	
	public SandyGrid(int cols, int rows, int tileSize, PieceData pieceData, AuthoringPatchData patchData,
			String id) {
		super(cols, rows, tileSize, pieceData, patchData, id);
	}
	
	@Override
	protected Point2D getCenter(int row, int col, double height) {
		double xCenter = col*height + 0.5*height;
		double yCenter = row*height + 0.5*height;
		return new Point2D.Double(xCenter, yCenter);
	}

	@Override
	protected Shape makeShape(Point2D center, int row, int col, double height) {
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
	
	@Override
	public SandyTile findTile(MouseEvent event) {
		int x = (int) event.getX() / super.myTileSize;
		int y = (int) event.getY() / super.myTileSize;
		if(x < 0 || x >= myCols || y < 0 || y >= super.myRows){
			return null;
		}
		return super.myGrid.get(y).get(x);
	}
}