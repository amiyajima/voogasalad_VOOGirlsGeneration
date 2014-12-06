package gamedata.events;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

/**
 * Creates a piece on the grid in a level in a specified location
 * @author Rica
 *
 */
public class CreatePieceGlobalAction implements GlobalAction {
    private Game myGame;
    private Piece myPiece;
    
    /**
     * Create this global action with a piece that stores the location it should be placed
     * in
     * @param game
     * @param pieceToCreate
     */
    public CreatePieceGlobalAction (Game game, Piece pieceToCreate) {
        myGame = game;
        myPiece = pieceToCreate;
    }
    
    @Override
    public void doBehavior () {
        myGame.getCurrentLevel().getGrid().setPiece(myPiece.getLoc(), myPiece);
    }

}
