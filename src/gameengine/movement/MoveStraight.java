package gameengine.movement;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import gamedata.gamecomponents.Piece;

/**
 * Depreciated for now
 *
 */
public class MoveStraight {
	private double myXIncrement;
	private double myYIncrement;

	public MoveStraight(double x, double y) {
		myXIncrement = x;
		myYIncrement = y;
	}

	public List<Point2D> getPath(Piece p) {
		List<Point2D> myPath = new ArrayList<Point2D>();
		double xLoc = p.getImageView().getX();
		double yLoc = p.getImageView().getY();
		while (xLoc < p.getImageView().getX() + myXIncrement
				&& yLoc < p.getImageView().getY() + myYIncrement) {
			myPath.add(new Point2D(xLoc++, yLoc++));
		}
		return myPath;
	}

	public void move(Piece p) {
		p.getImageView().setX(p.getImageView().getX() + myXIncrement);
		p.getImageView().setX(p.getImageView().getY() + myYIncrement);
	}

	public double getXMove() {
		return myXIncrement;
	}

	public double getYMove() {
		return myYIncrement;
	}
}
