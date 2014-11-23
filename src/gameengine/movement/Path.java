package gameengine.movement;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a path(An arrangement of Points that must be traveresed during a
 * movement) for movement.
 * 
 * DEPRECIATED
 * 
 * @author Jesse, Rica
 *
 */
public class Path {
	/**
	 * Contains respective positions for possible path.
	 */
	private List<Point2D> myCoords;

	/**
	 * Default Constructor
	 */
	public Path() {

	}

	/**
	 * Constructor
	 * 
	 * @param myPath
	 *            List of Point2D containing ordered relative points of movement
	 */
	public Path(List<Point2D> myPath) {
		myCoords = myPath;
	}

	/**
	 * Adds Point2Ds to path
	 * 
	 * @param args
	 *            Point2Ds to add to Path
	 */
	public void addPointsToPath(Point2D... args) {
		for (Point2D p : args) {
			myCoords.add(p);
		}
	}

	/**
	 * Removes a point at a given index from the Path
	 * 
	 * @param index
	 *            int index corresponding to Point to be removed from path
	 */
	public void removePointsFromPath(int index) {
		myCoords.remove(index);
	}

	/**
	 * Returns a List of Point2Ds corresponding to the Path
	 * 
	 * @return List of Point2Ds corresponding to the Path
	 */
	public List<Point2D> getPath() {
		return myCoords;
	}
}
