package gameengine.movement;

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
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
        private List<Point2D> myMoves;
	private Grid myGrid;

	public Movement(Level lvl, List<Point2D>... endPoints) {
		for (List<Point2D> p: paths) {
		    myMoves.add(p);
		}
		myGrid = lvl.getGrid();
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
		for (Point2D a : myPath) {
			p.add(new Point2D(a.getX() + x, a.getY() + y));
		}
		return p;
	}
	
	/**
	 * Checks the collisions in the currently defined path. Checks with piece and patch
	 * collisions.
	 * @return true or false
	 */
        private boolean checkPathCollision() {
            for (Point2D p : myPath) {
                if (myGrid.getPiece(p) != null) {
                    // check if these pieces can collide
                    return false;
                }
                if (myGrid.getPatch(p) != null) {
                    // check if these pieces can collide
                    return false;
                }
            }
            return true;
        }	
}
