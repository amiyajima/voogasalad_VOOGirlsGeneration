package gameengine.movement;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Point2D;

/**
 * 
 * @author Jesse, Anna , Rica
 *
 */
public class Movement {
	// movement should contain relative paths
	private List<Patch> myPath;

	/**
	 * Contains respective locations of possible movement
	 */
	private List<Point2D> myMoves;

	public Movement(List<Point2D> move, List<Patch> path) {
		myMoves = move;
		myPath = path;
	}

	/**
	 * Return possible x,y coordinates of movement based on current location x,
	 * y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Point2D> getPossibleLocs(int x, int y) {
		List<Point2D> p = new ArrayList<Point2D>();
		for (Point2D a : myMoves) {
			p.add(new Point2D(a.x + x, a.y + y));
		}
		return p;
	}

	public void showPath() {
		// show path to user in GUI
	}

	/**
	 * 
	 * @param piece
	 */
	public void movePiece(Piece piece, int x, int y) {
		if (checkPathClear()) {

		}
	}

	/**
	 * check if there are collisions in your path. if the whole path is clear,
	 * return true. else return false.
	 */
	private boolean checkPathClear() {
		for (Patch p : myPath) {
			// need to check if your piece can move onto that patch
		}
		return true;
	}

	/**
	 * 
	 * 
	 * @param x
	 * @param y
	 */
	private void moveToPosition(int x, int y) {

	}

}
