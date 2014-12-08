package gameengine.movement;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gamedata.rules.Rule;

import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import authoring_environment.GUIGrid;

/**
 * Defines the movement of a piece. Responsible for maintaining the behavior
 * properties of a piece and executing allowed movements
 * 
 * @author Jesse, Rica
 *
 */
public class Movement implements Action {
	/**
	 * Point2Ds referring to relative positions of movement
	 */
	private List<Point2D> myMoves;
	/**
	 * Point2Ds referring to currently calculated absolute positions of movement
	 */
	private List<Point2D> myAbsoluteMoves;
	/**
	 * List of Point2Ds referring to relative paths of movement
	 */
	private List<List<Point2D>> myPaths;
	/**
	 * Grid the Piece is on and movement will be execute on
	 */
	private transient GUIGrid myGrid;

	/**
	 * Orientator resonsible for calculating orientations
	 */
	private Orientator myOrientator;
	/**
	 * Orientation of the piece (depending on last movement made)
	 */
	private double myOrientation;

	/**
	 * Constructor
	 * 
	 * @param endPoints
	 *            Point2Ds representing all possible relative locations of
	 *            movement
	 */
	@SafeVarargs
	public Movement(GUIGrid g, List<Point2D>... endPoints) {
		myOrientation = 0;
		myOrientator = new Orientator();
		myGrid = g;
		boolean first = true;
		myPaths = new ArrayList<List<Point2D>>();
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
	 * Checks to see if an absolute location (x,y) is a valid location for
	 * movement and that the destination is empty (no pieces overlapping)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValidLocation(int x, int y) {
		for (Point2D p : myAbsoluteMoves) {
			if ((p.getX() == x && p.getY() == y)
					&& (myGrid.getPiece(new Point2D.Double(x, y)) == null)) {
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
	private boolean checkPathCollision(GUIGrid myGrid, Point2D endPoint) {
		List<Point2D> path;
		boolean b = true;
		// TODO: Implement Path Collision Once Constraints are Implemented
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
	public List<Point2D> getSpecificActionRange(Point2D pieceLocation) {
		return this.getPossibleLocs((int) pieceLocation.getX(),
				(int) pieceLocation.getY());
	}

	@Override
	public List<Point2D> getEffectRange() {
		return new ArrayList<Point2D>();
	}

	/**
	 * Contains the logic to execute the behavior of moving the piece
	 */
	@Override
	public void doBehavior(Piece actor, Piece... receivers) {
		Piece p = receivers[0];
		Point2D point = p.getLoc();
		if (isValidLocation((int) point.getX(), (int) point.getY())) {
			// TODO: Implement Orientation Calculation Here
			actor.setLoc(point);
		}
	}

	@Override
	public String toString() {
		return "Movement";
	}

	@Override
	public List<Point2D> getActionRange() {
		// TODO Auto-generated method stub
		return null;
	}
}
