package gameengine.movement;

import gamedata.gamecomponents.Grid;
import gamedata.rules.Rule;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Defines the movement of a piece. Responsible for maintaining the behavior
 * properties of a piece and executing allowed movements
 * 
 * @author Jesse, Anna, Rica
 *
 */
public class Movement {
	// possible paths for this movement
	private List<List<Point2D>> myMoves;
	private List<Point2D> myPaths;
	private List<Rule> myRules;

	/**
	 * Constructor
	 * @param endPoints Point2Ds representing all possible relative locations of movement
	 */
	public Movement(List<Point2D>... endPoints) {
	    myMoves = new ArrayList<List<Point2D>>();
	    myPaths = new ArrayList<Point2D>();
	    myRules = new ArrayList<Rule>();
		for (List<Point2D> p : endPoints) {
			myMoves.add(p);
		}
	}

	/**
	 * Return absolute possible x,y coordinates of movement based on current
	 * location x,y
	 * 
	 * @param x
	 * @param y
	 * @return List of Point2D corresponding to absolute locations of movement
	 */
	public List<Point2D> getPossibleLocs(int x, int y) {
		List<Point2D> p = new ArrayList<Point2D>();
		for (Point2D a : myPaths) {
			p.add(new Point2D.Double(a.getX() + x, a.getY() + y));
		}
		return p;
	}

	/**
	 * Checks the collisions in the currently defined path. Checks with piece
	 * and patch collisions.
	 * 
	 * @return true or false
	 */
	private boolean checkPathCollision(Grid myGrid) {
		boolean b = true;
		for (Point2D p : myPaths) {
			if (myGrid.getPiece(p) != null) {
				b = false;
			}
			if (myGrid.getPatch(p) != null) {
				// check if these pieces can collide with terrain
				return false;
			}
		}
		return b;
	}
}
