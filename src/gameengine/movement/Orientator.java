package gameengine.movement;

import javafx.geometry.Point2D;
import gamedata.gamecomponents.Piece;

/**
 * At every point on the piece's movement path, this calculates which direction the piece
 * should be facing. Also if you want to turn the piece to face an enemy or something like 
 * that, you simply enter the location of the piece and the location of the enemy.
 * @author Rica
 *
 */
public class Orientator {
    double myTurn;
    
    /**
     * Calculates the amount that the piece needs to turn between each unit of movement
     * so that it is facing the proper direction
     * @param currentFacing - the direction the piece is currently facing
     * @param from - the piece's current location
     * @param to - the location the piece is moving to
     */
	public void calculateTurn(double currentFacing, Point2D from, Point2D to) {
		if (from.getX() != to.getX() && from.getX() > to.getX()) {
		    myTurn = 90 - currentFacing;
		}
		else if (from.getX() != to.getX() && from.getX() < to.getX()) {
		    myTurn = 270 - currentFacing;
		}
		else if (from.getY() != to.getY() && from.getY() < to.getY()) {
		    myTurn = 0 - currentFacing;
		}
		else if (from.getY() != to.getY() && from.getY() > to.getY()) {
		    myTurn = 180 - currentFacing;
		}
		else {
		    myTurn = 0;
		}
	}
	
	/**
	 * Turns the piece
	 * @param the piece
	 */
	public void turn(Piece p) {
	    p.getImageView().setRotate(myTurn);
	}

	/**
	 * Get the turn
	 * @return a double representing how much the turn is
	 */
	public double getTurn() {
		return myTurn;
	}

}
