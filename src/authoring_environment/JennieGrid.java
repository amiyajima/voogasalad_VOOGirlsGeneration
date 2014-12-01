package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.input.MouseEvent;
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
public class JennieGrid extends ShapeGrid {
	
	public JennieGrid(int cols, int rows, int tileSize, PieceData pieceData, PatchData patchData) {
		super(cols, rows, tileSize, pieceData, patchData);
	}
	
	@Override
	protected Point2D getCenter(int row, int col, double height) {
		double r = height/Math.sqrt(3);
		double xCenter = col*1.5*r + r;
		double yCenter = row*height + (col%2)*0.5*height + height/2;
		return new Point2D.Double(xCenter, yCenter);
	}
	
	@Override
	protected Shape makeShape(Point2D center, int row, int col, double h) {
		double r = h/Math.sqrt(3);
		double xCenter = center.getX();
		double yCenter = center.getY();

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
	
	@Override
	public SandyTile findTile(MouseEvent event) {
		int x = (int) (event.getX() / myTileSize);
		int y = (int) (event.getY() / (double)(2*myTileSize/Math.sqrt(3)));
		if(x < 0 || x >= myCols || y < 0 || y >= myRows){
			return null;
		}
		return myGrid.get(y).get(x);
	}
}
