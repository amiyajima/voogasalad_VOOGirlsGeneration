package gamedata.events.globalaction;

import java.awt.geom.Point2D;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

/**
 * Creates a piece on the grid in a level in a specified location
 * @author Mike Zhu
 *	
 */
public class MakePieceAtLocation extends GlobalAction {

    private Piece myPieceType;
    private Point2D.Double myLoc;
    private int myPlayerID;

    
    /**
     * Create this global action with a piece that stores the location it should be placed
     * in
     * @param game
     * @param pieceToCreate
     */
    public MakePieceAtLocation (Piece pieceToCreate, Point2D.Double loc, int PlayerID) {
    	super(String.format("Make %s at point %s", pieceToCreate, loc));
        myPieceType = pieceToCreate;
        myLoc = loc;
        myPlayerID = PlayerID;
    }
    
    @Override
    public void doBehavior(GUIGrid grid) {
    	grid.addPieceAtLoc(myPieceType, myLoc);
    	grid.getPiece(myLoc).setPlayerID(myPlayerID);
    }

}
