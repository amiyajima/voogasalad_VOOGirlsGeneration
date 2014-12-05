package gamedata.events;

import java.awt.geom.Point2D;

import gamedata.gamecomponents.Piece;

/**
 * GlobalAction that creates a new Piece at a given Patch. 
 * @author Mike Zhu
 *
 */

//TODO: This class likely needs to access the given Grid to place the Piece

public class CreatePieceAtPoint implements GlobalAction{

	private Point2D myCoords;
	private Piece myPiece;
	
	public CreatePieceAtPoint(Point2D coords, Piece piece){
		myCoords = coords;
		myPiece = piece;
	}

	@Override
	public void doBehavior() {
		// TODO Auto-generated method stub
	}
}
