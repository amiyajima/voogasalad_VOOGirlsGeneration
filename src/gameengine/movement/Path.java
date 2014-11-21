package gameengine.movement;

import java.awt.geom.Point2D;
import java.util.ArrayList; 
import java.util.List;


/**
 * Not Currently Implemented.....
 *
 */
public class Path {
	/**
	 * Contains respective positions for possible path.
	 */
	private List<Point2D> myCoords;

	public Path() {

	}

	public Path(List<Point2D> myPath) {
		myCoords = myPath;
	}

	public void addPointsToPath(Point2D... args) {
		for (Point2D p : args) {
			myCoords.add(p);
		}
	}

	public void removePointsFromPath(int index) {
		myCoords.remove(index);
	}

	public List<Point2D> getPath(int x, int y) {
		List<Point2D> p = new ArrayList<Point2D>();
		return p;
	}
}
