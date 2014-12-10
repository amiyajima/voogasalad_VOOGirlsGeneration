package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Hexagon tile of the grid. In each row, the upper middle hexagon has its lower 
 * left and right sides overlap with adjacent lower hexagons' upper right/left side.
 * 
 * @author Mengen Huang, Jennie Ju
 *
 */
public class HexagonTile extends SuperTile{

	/**
	 * 
	 * @param size: The height of the hexagon.
	 * @param loc: The grid location of the tile.
	 */
	public HexagonTile(double size, Point2D loc) {
		super(size, loc);
	}

	/**
	 * @param size: The height of the hexagon.
	 * @param centerCoord: The pixel location of the hexagon center.
	 * @return The hexagon with its size and hexagon vertexes position defined.
	 */
	@Override
	protected Shape makeShape(double size,Point2D centerCoord) {
		double radius = size/Math.sqrt(3);
		double centerX=centerCoord.getX();
		double centerY=centerCoord.getY();
		Polygon hexagon = new Polygon(
				centerX-radius, centerY,
				centerX-0.5*radius, centerY+0.5*size,
				centerX+0.5*radius, centerY+0.5*size,
				centerX+radius, centerY,
				centerX+0.5*radius, centerY-0.5*size,
				centerX-0.5*radius, centerY-0.5*size
				);
		return hexagon;
	}

	/**
	 * @param size: The height of the hexagon.
	 * @param loc: The grid location of the tile.
	 * @return The pixel coordinate of the leftmost vertex of the hexagon.
	 */
	@Override
	protected Point2D calculatePixelLocation(double size, Point2D loc) {
		double radius = size/Math.sqrt(3);
		double xCoord = loc.getY()*1.5*radius + radius;
		double yCoord = loc.getX()*size + (loc.getY()%2)*0.5*size+size/2;
		return new Point2D.Double(xCoord, yCoord);
		
	}

	/**
	 * @param size: The height of the hexagon.
	 * @param loc: The grid location of the tile.
	 * @return The pixel location of the image.
	 */
	@Override
	protected Point2D calculateImageLocation(double size, Point2D loc) {
		Point2D layoutCoord = calculatePixelLocation(size,loc);
		double radius = size/Math.sqrt(3);
		double imageX = layoutCoord.getX()-radius/2;
		double imageY = layoutCoord.getY()-size/4;
		return new Point2D.Double(imageX,imageY);
	}

	/**
	 * @param size: The height of the hexagon.
	 * @return The side length of the biggest square inside the hexagon tile.
	 */
	@Override
	protected double calculateImageSize(double size) {
		return size/2;
	}
}
