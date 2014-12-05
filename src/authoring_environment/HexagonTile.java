package authoring_environment;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class HexagonTile extends SuperTile{

	public HexagonTile(int size, Point2D loc) {
		super(size, loc);
	}

	@Override
	protected Shape makeShape(int size) {
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
	protected Point2D calculateCoord(int size, Point2D loc) {
		double radius = size/Math.sqrt(3);
		double xCoord = loc.getY()*1.5*radius + radius/2;
		double yCoord = loc.getX()*size + (loc.getY()%2)*0.5*size;
		return new Point2D.Double(xCoord, yCoord);
		
	}

	@Override
	protected Point2D calculateImageCoord(int size, Point2D loc) {
		Point2D layoutCoord=calculateCoord(size,loc);
		double radius = size/Math.sqrt(3);
		double imageX=layoutCoord.getX()-radius/2;
		double imageY=layoutCoord.getY()+size/4;
		return new Point2D.Double(imageX,imageY);
	}

	@Override
	protected int calculateImageSize(int size) {
		return size/2;
	}

}
