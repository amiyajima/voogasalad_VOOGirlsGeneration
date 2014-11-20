package gameengine.movement;

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
import gamedata.rules.Rule;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * 
 * @author Jesse, Anna , Rica
 *
 */
public class Movement {
    // possible paths for this movement
    private List<List<Point2D>> myMoves;
	private List<Point2D> myPaths;
	private List<Rule> myRules;

	public Movement(List<Point2D>... endPoints) {
		for (List<Point2D> p: endPoints) {
		    myMoves.add(p);
		}
	}

	/**
	 * Return absolute possible x,y coordinates of movement based on current
	 * location x,y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Point2D> getPossibleLocs(int x, int y) {
		List<Point2D> p = new ArrayList<Point2D>();
		for (Point2D a : myPaths) {
			p.add(new Point2D(a.getX() + x, a.getY() + y));
		}
		return p;
	}	
	/**
	 * Checks the collisions in the currently defined path. Checks with piece and patch
	 * collisions.
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
