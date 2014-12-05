package authoring_environment;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleTile extends SuperTile{

	public CircleTile(int size, Point2D loc) {
		super(size, loc);
	}

	@Override
	protected Shape makeShape(int size) {
		return new Circle(size/2);
	}

	@Override
	protected Point2D calculateCoord(int size, Point2D loc) {
		double xCenter=loc.getY()*size+size/2;
		double yCenter=loc.getX()*size+size/2;
		return new Point2D.Double(xCenter, yCenter);
	}

	@Override
	protected Point2D calculateImageCoord(int size, Point2D loc) {
		Point2D layoutCenter=calculateCoord(size,loc);
		double imageX=layoutCenter.getX()-size/Math.sqrt(2);
		double imageY=layoutCenter.getY()-size/Math.sqrt(2);
		return new Point2D.Double(imageX,imageY);
	}

	@Override
	protected int calculateImageSize(int size) {
		return (int)Math.round(2*size/Math.sqrt(2));
	}

}
