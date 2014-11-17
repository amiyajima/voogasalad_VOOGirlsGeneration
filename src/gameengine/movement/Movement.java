package gameengine.movement;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.rules.Rule;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Point2D;

public class Movement {
	// movement should contain relative paths
	// Path is currently unimplemented. Please ignore
	private List<Point2D> myPath;

	/**
	 * Contains respective locations of possible movement
	 */
	private List<Point2D> myMoves;

	public Movement(List<Point2D> move) {
		myMoves = move;
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
		for (Point2D a : myMoves) {
			p.add(new Point2D(a.x + x, a.y + y));
		}
		return p;
	}

	/**
	 * 
	 * @param piece
	 */
	public void movePiece(Piece piece, int x, int y) {

	}

	private void checkPathCollision(List<Point2D> path) {
		for (Point2D p : path) {
			// need to check if your piece can move onto that patch
		}
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
