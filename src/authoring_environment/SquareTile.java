package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareTile extends SuperTile{

	public SquareTile(int size, Point2D loc) {
		super(size, loc);
	}

	@Override
	protected Shape makeShape(int size) {
		return new Rectangle(size, size);
	}
	
	@Override
	protected Point2D calculateCoord(int size, Point2D loc) {
		Point2D coord=new Point2D.Double(loc.getX()*size, loc.getY()*size);
		return coord;
	}

	@Override
	protected Point2D calculateImageCoord(int size, Point2D loc) {
		return calculateCoord(size, loc);
	}

	@Override
	protected int calculateImageSize(int size) {
		return size;
	}

}
