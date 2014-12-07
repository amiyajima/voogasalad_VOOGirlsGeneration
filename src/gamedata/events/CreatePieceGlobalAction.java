package gamedata.events;

import java.awt.geom.Point2D;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

/**
 * Creates a piece on the grid in a level in a specified location
 * @author Rica, Mike Zhu
 *	
 */
public class CreatePieceGlobalAction extends GlobalAction {
	//Space in the description needed when we concatenate Strings to make dynamic title
	public static final String ACTION_TYPE = "Create ";
    private Game myGame;
    private Piece myPiece;
    private Point2D myLoc;
    
    /**
     * Create this global action with a piece that stores the location it should be placed
     * in
     * @param game
     * @param pieceToCreate
     */
    public CreatePieceGlobalAction (Game game, Piece pieceToCreate, Point2D loc) {
    	super(ACTION_TYPE + pieceToCreate.getName() + " at " + pieceToCreate.getLoc().toString());
        myGame = game;
        myPiece = pieceToCreate;
        myLoc = loc;
    }
    
    @Override
    public void doBehavior () {
        myGame.getCurrentLevel().getGrid().addPiece(myPiece, myLoc);;
    }

}
