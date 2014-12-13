package gameengine.movement;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;
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
	private List<Point2D.Double> myMoves;
	
	/**
	 * Point2Ds referring to currently calculated absolute positions of movement
	 */
	private List<Point2D.Double> myAbsoluteMoves;
	
	/**
	 * List of Point2Ds referring to relative paths of movement
	 */
	private List<List<Point2D.Double>> myPaths;
	
	/**
	 * Grid the Piece is on and movement will be execute on
	 */
	private transient GUIGrid myGrid;

	/**
	 * Orientator resonsible for calculating orientations
	 */
	private transient Orientator myOrientator;
	
	/**
	 * Orientation of the piece (depending on last movement made)
	 */
	private double myOrientation;

	/**
	 * Constructor
	 * 
	 * @param g
	 *            The GUIGrid that the piece containing this movement exists on
	 * @param endPoints
	 *            Point2Ds representing all possible relative locations of
	 *            movement
	 */
	public Movement(List<Point2D.Double>... endPoints) {
		myOrientation = 0;
		myOrientator = new Orientator();
		boolean first = true;
		myPaths = new ArrayList<List<Point2D.Double>>();
		for (List<Point2D.Double> p : endPoints) {
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
	 * @param x Current x coordinate
	 * @param y Current y coordinate
	 * @return List of Point2D corresponding to absolute locations of movement
	 */
	public List<Point2D.Double> getPossibleLocs(int x, int y) {
		myAbsoluteMoves = new ArrayList<Point2D.Double>();
		for (Point2D.Double a : myMoves) {
			myAbsoluteMoves.add(new Point2D.Double(a.getX() + x, a.getY() + y));
		}
		return myAbsoluteMoves;
	}

	/**
	 * Checks to see if an absolute location (x,y) is a valid location for
	 * movement and that the destination is empty (no pieces overlapping)
	 * 
	 * @param x Absolute coordinate x
	 * @param y Absolute coordinate y
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
	 * @return true or false depending on whether a collision is detected on a path
	 */
	private boolean checkPathCollision(GUIGrid myGrid, Point2D endPoint) {
		List<Point2D.Double> path;
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
	public List<Point2D.Double> getSpecificActionRange(Point2D pieceLocation) {
		return this.getPossibleLocs((int) pieceLocation.getX(),
				(int) pieceLocation.getY());
	}

	@Override
	public List<Point2D.Double> getEffectRange() {
		return new ArrayList<Point2D.Double>();
	}

	/**
	 * Contains the logic to execute the behavior of moving the piece
	 */
	@Override
	public void doBehavior(GUIGrid grid, Piece actor, Piece... receivers) {
		Piece p = receivers[0];
		Point2D.Double point = p.getLoc();
		if (isValidLocation((int) point.getX(), (int) point.getY())) {
			// TODO: Implement Orientation Calculation Here
			actor.setLoc(point);
		}
	}

	@Override
	public String toString() {
	    String str = "MOVEMENT: moves - ";
	    for (Point2D.Double pt : myMoves) {
	        str += "(" + pt.getX() + ","  + pt.getY() + ")";
	    }
	    str += " paths - ";
	    for (List<Point2D.Double> ptlst : myPaths) {
	        str += "[";
	        for (Point2D.Double pt : ptlst) {
	            str += "(" + pt.getX() + ","  + pt.getY() + ")";
	        }
	        str += "]";
	    }
	    return str;
	}

	@Override
	public List<Point2D.Double> getActionRange() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Getter method for the list of relative moves. This method
	 * is needed by the PieceTypeEditor, so do not delete it!
	 * 
	 * @return : List of locations a piece can move to relative
	 * 				to its current location.
	 */
	public List<Point2D.Double> getRelativeMoves() {
		return myMoves;
	}
}
