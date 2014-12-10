package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Circle tile that each circle is the biggest circle in the square tile.
 * 
 * @author Mengen Huang
 *
 */
public class CircleTile extends SuperTile{

	/**
	 * 
	 * @param size: The diameter of the circle.
	 * @param loc: The grid coordinate of the circle center.
	 * 
	 */
	public CircleTile(double size, Point2D loc) {
		super(size, loc);
	}

	/**
	 * 
	 * @param centerCoord: The pixel coordinate of the circle center.
	 * @return The circle with its center position and diameter defined.
	 */
	@Override
	protected Shape makeShape(double size, Point2D centerCoord) {
		double centerX = centerCoord.getX();
		double centerY = centerCoord.getY();
		return new Circle(centerX,centerY,size/2);
	}

	/**
	 *@param size: The diameter of the circle.
	 *@param loc: The grid location of the tile.
	 * @return The pixel location of center of the circle tile.
	 */
	@Override
	protected Point2D calculatePixelLocation(double size, Point2D loc) {
		double xCenter = loc.getY()*size+size/2;
		double yCenter = loc.getX()*size+size/2;
		return new Point2D.Double(xCenter, yCenter);
	}

	/**
	 * @param size: The diameter of the circle.
	 * @param loc: The grid location with the top left tile as (0,0).
	 * @return The pixel coordinate of the top-left vertex of the 
	 * 			biggest square in the circle.
	 * 
	 */
	@Override
	protected Point2D calculateImageLocation(double size, Point2D loc) {
		Point2D layoutCenter = calculatePixelLocation(size,loc);
		double imageX = layoutCenter.getX()-size/Math.sqrt(2);
		double imageY = layoutCenter.getY()-size/Math.sqrt(2);
		return new Point2D.Double(imageX,imageY);
	}

	/**
	 * @param size: The diameter of the circle.
	 * @return The side length of the biggest square inside the circle.
	 */
	@Override
	protected double calculateImageSize(double size) {
		return (int)Math.round(2*size/Math.sqrt(2));
	}

}
