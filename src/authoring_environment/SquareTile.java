package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Square tile of the grid.
 * @author Mengen Huang
 *
 */
public class SquareTile extends SuperTile{

	/**
	 * 
	 * @param size: The side length of the square.
	 * @param loc: The grid coordinate of the tile.
	 */
	public SquareTile(double size, Point2D loc) {
		super(size, loc);
	}

	/**
	 * @param size: The side length of the square. 
	 * @param coord: The pixel location of the top left vertex.
	 */
	@Override
	protected Shape makeShape(double size, Point2D coord) {
		return new Rectangle(coord.getX(),coord.getY(),size, size);
	}
	
	/**
	 * @param size: The side length of the square.
	 * @param loc: The grid location of the tile.
	 * @return The pixel location of the top left vertex.
	 */
	@Override
	protected Point2D calculatePixelLocation(double size, Point2D loc) {
		Point2D coord = new Point2D.Double(loc.getX()*size, loc.getY()*size);
		return coord;
	}
	
	/**
	 * @param size: The side length of the square.
	 * @param loc: The grid location of the tile.
	 * @return The pixel location of the image.
	 */
	@Override
	protected Point2D calculateImageLocation(double size, Point2D loc) {
		return calculatePixelLocation(size, loc);
	}
	
	/**
	 * @param size: The side length of the square.
	 * @return The side length of the image which is the same as that
	 * of the square tile.
	 */
	@Override
	protected double calculateImageSize(double size) {
		return size;
	}
}
