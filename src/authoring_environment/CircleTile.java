package authoring_environment;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleTile extends SuperTile{

	public CircleTile(double size, Point2D loc) {
		super(size, loc);
	}

	@Override
	protected Shape makeShape(double size, Point2D centerCoord) {
		double centerX=centerCoord.getX();
		double centerY=centerCoord.getY();
		return new Circle(centerX,centerY,size/2);
	}

	@Override
	protected Point2D calculateCoord(double size, Point2D loc) {
		double xCenter=loc.getY()*size+size/2;
		double yCenter=loc.getX()*size+size/2;
		return new Point2D.Double(xCenter, yCenter);
	}

	@Override
	protected Point2D calculateImageCoord(double size, Point2D loc) {
		Point2D layoutCenter=calculateCoord(size,loc);
		double imageX=layoutCenter.getX()-size/Math.sqrt(2);
		double imageY=layoutCenter.getY()-size/Math.sqrt(2);
		return new Point2D.Double(imageX,imageY);
	}

	@Override
	protected double calculateImageSize(double size) {
		return (int)Math.round(2*size/Math.sqrt(2));
	}

}
