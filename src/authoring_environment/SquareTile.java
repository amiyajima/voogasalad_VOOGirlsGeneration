package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareTile extends SuperTile{

	public SquareTile(double size, Point2D loc) {
		super(size, loc);
	}

	@Override
	protected Shape makeShape(double size, Point2D coord) {
		return new Rectangle(coord.getX(),coord.getY(),size, size);
	}
	
	@Override
	protected Point2D calculateCoord(double size, Point2D loc) {
		Point2D coord=new Point2D.Double(loc.getX()*size, loc.getY()*size);
		return coord;
	}

	@Override
	protected Point2D calculateImageCoord(double size, Point2D loc) {
		return calculateCoord(size, loc);
	}

	@Override
	protected double calculateImageSize(double size) {
		return size;
	}

}
