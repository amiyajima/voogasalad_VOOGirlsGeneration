package gameengine.movement;

import gamedata.action.Action;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Piece;
import gamedata.rules.Rule;

import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Defines the movement of a piece. Responsible for maintaining the behavior
 * properties of a piece and executing allowed movements
 * 
 * @author Jesse, Rica
 *
 */
public class Movement implements Action {
	// possible paths for this movement
	private List<Point2D> myMoves;
	private List<List<Point2D>> myPaths;
	private List<Rule> myRules;
	private List<Point2D> myAbsoluteMoves;

	/**
	 * Constructor
	 * 
	 * @param endPoints
	 *            Point2Ds representing all possible relative locations of
	 *            movement
	 */
	@SafeVarargs
	public Movement(List<Rule> rules, List<Point2D>... endPoints) {
		boolean first = true;
		// myRules = rules;
		myPaths = new ArrayList<List<Point2D>>();
		myRules = new ArrayList<Rule>();
		for (List<Point2D> p : endPoints) {
			if (first) {
				myMoves = p;
				first = false;
			} else {
				myPaths.add(p);
			}
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
		myAbsoluteMoves = new ArrayList<Point2D>();
		for (Point2D a : myMoves) {
			myAbsoluteMoves.add(new Point2D.Double(a.getX() + x, a.getY() + y));
		}
		return myAbsoluteMoves;
	}

	/**
	 * Checks to see if an absolute location (x,y) is valid
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValidLocation(int x, int y) {
		for (Point2D p : myAbsoluteMoves) {
			if (p.getX() == x && p.getY() == y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks the collisions in the currently defined path. Checks with piece
	 * and patch collisions.
	 * 
	 * @return true or false
	 */
	private boolean checkPathCollision(Grid myGrid) {
		List<Point2D> path;
		boolean b = true;
		// Needs to find path with correct endpoint. Then check collision at
		// each point2D of the path

		/*
		 * for (Point2D p : myPaths) { if (myGrid.getPiece(p) != null) { b =
		 * false; } if (myGrid.getPatch(p) != null) { // check if these pieces
		 * can collide with terrain return false; } }
		 */
		return b;
	}

	@Override
	public List<Point2D> getActionRange(Point2D pieceLocation) {
		return this.getPossibleLocs((int)pieceLocation.getX(), (int)pieceLocation.getY());
	}

	@Override
	public List<Point2D> getEffectRange() {
		return null;
	}

	@Override
	public void doBehavior(Piece actor, Piece... receivers) {
		
	}
}
