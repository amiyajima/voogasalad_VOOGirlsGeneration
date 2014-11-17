package gameengine.movement;

import javafx.geometry.Point2D;
import gamedata.gamecomponents.Piece;

/**
 * At every point on the piece's movement path, this calculates which direction
 * the piece should be facing. Also if you want to turn the piece to face an
 * enemy or something like that, you simply enter the location of the piece and
 * the location of the enemy.
 *
 */
public class Orientator {
	double myTurn;

	public void calculateTurn(double currentFacing, Point2D from, Point2D to) {
		if (from.getX() != to.getX() && from.getX() > to.getX()) {
			myTurn = 90 - currentFacing;
		} else if (from.getX() != to.getX() && from.getX() < to.getX()) {
			myTurn = 270 - currentFacing;
		} else if (from.getY() != to.getY() && from.getY() < to.getY()) {
			myTurn = 0 - currentFacing;
		} else if (from.getY() != to.getY() && from.getY() > to.getY()) {
			myTurn = 180 - currentFacing;
		} else {
			myTurn = 0;
		}
	}

	public void turn(Piece p) {
		p.getImageView().setRotate(myTurn);
	}

	public double getTurn() {
		return myTurn;
	}

}
