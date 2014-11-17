package gameengine.movement;

import gamedata.gamecomponents.Piece;

/**
 * Depreciated for now
 *
 */
public class Turn {
	private double myDegree;

	public Turn(double degree) {
		myDegree = degree;
	}

	public void turn(Piece p) {
		p.getImageView().setRotate(myDegree);
	}

	public double getTurn() {
		return myDegree;
	}

}
