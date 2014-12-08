package gamedata.events.globalaction;

import java.awt.geom.Point2D;

import authoring_environment.GUIGrid;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

/**
 * Creates a piece on the grid in a level in a specified location
 * @author Mike Zhu
 *	
 */
public class CreatePiece extends GlobalAction {

    private Piece myPieceType;
    private Point2D myLoc;
    
    /**
     * Create this global action with a piece that stores the location it should be placed
     * in
     * @param game
     * @param pieceToCreate
     */
    public CreatePiece (String name, Piece pieceToCreate, Point2D loc) {
    	super(name);
        myPieceType = pieceToCreate;
        myLoc = loc;
    }
    
    @Override
    public void doBehavior(GUIGrid grid) {
    	grid.addPieceAtLoc(myPieceType, myLoc);
    }

}
