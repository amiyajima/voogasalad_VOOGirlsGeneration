package gamedata.events;

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
    
    /**
     * Create this global action with a piece that stores the location it should be placed
     * in
     * @param game
     * @param pieceToCreate
     */
    public CreatePieceGlobalAction (Game game, Piece pieceToCreate) {
    	super(ACTION_TYPE + pieceToCreate.getName() + " at " + pieceToCreate.getLoc().toString());
        myGame = game;
        myPiece = pieceToCreate;

    }
    
    @Override
    public void doBehavior () {
        myGame.getCurrentLevel().getGrid().setPiece(myPiece.getLoc(), myPiece);
    }

}
