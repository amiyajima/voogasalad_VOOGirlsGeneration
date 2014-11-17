package gameengine.movement;

import javafx.geometry.Point2D;
import gamedata.gamecomponents.Piece;

/**
 * Depreciated for now
 *
 */
public class Orientator {
    double myTurn;

	public void calculateTurn(double currentFacing, Point2D from, Point2D to) {
		if (from.getX() != to.getX() && from.getX() > to.getX()) {
		    double myTurn = 0 - currentFacing;
		}
		else if (from.getX() != to.getX() && from.getX() < to.getX()) {
		    double myTurn = 180 - currentFacing;
		}
		else if (from.getY() != to.getY() && from.getY() < to.getY()) {
		    double myTurn = 90 - currentFacing;
		}
		else if (from.getY() != to.getY() && from.getY() > to.getY()) {
		    double myTurn = 270 - currentFacing;
		}
		else {
		    myTurn = 0;
		}
	}

	public double getTurn() {
		return myTurn;
	}

}
