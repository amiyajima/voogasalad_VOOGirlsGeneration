package authoring_environment;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class HexagonTile extends SuperTile{

	public HexagonTile(double size, Point2D loc) {
		super(size, loc);
	}

	@Override
	protected Shape makeShape(double size) {
		double radius = size/Math.sqrt(3);
		
		Polygon hexagon = new Polygon(
				0-radius, 0,
				0-0.5*radius, 0+0.5*size,
				0+0.5*radius, 0+0.5*size,
				0+radius, 0,
				0+0.5*radius, 0-0.5*size,
				0-0.5*radius, 0-0.5*size
				);
		return hexagon;
	}

	@Override
	protected Point2D calculateCoord(double size, Point2D loc) {
		double radius = size/Math.sqrt(3);
		double xCoord = loc.getY()*1.5*radius + radius;
		double yCoord = loc.getX()*size + (loc.getY()%2)*0.5*size+size/2;
		return new Point2D.Double(xCoord, yCoord);
		
	}

	@Override
	protected Point2D calculateImageCoord(double size, Point2D loc) {
		Point2D layoutCoord=calculateCoord(size,loc);
		double radius = size/Math.sqrt(3);
		double imageX=layoutCoord.getX()-radius/2;
		double imageY=layoutCoord.getY()+size/4;
		return new Point2D.Double(imageX,imageY);
	}

	@Override
	protected double calculateImageSize(double size) {
		return size/2;
	}

}
