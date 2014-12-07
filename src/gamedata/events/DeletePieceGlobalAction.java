package gamedata.events;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

/**
 * Deletes a specified piece off the grid
 * @author Rica, Mike Zhu
 *
 */
public class DeletePieceGlobalAction extends GlobalAction {
	public static final String DESCRIPTION = "Delete ";
    Game myGame;
    Piece myPiece;
    
    /**
     * Make sure you construct this referring to the piece that you want to delete rather than 
     * creating a new piece because the grid will try to look for that piece to delete when the
     * method is called
     * @param game
     * @param pieceToDelete
     */
    public DeletePieceGlobalAction(Game game, Piece pieceToDelete) {
    	super(DESCRIPTION + pieceToDelete.getName());
        myGame = game;
        myPiece = pieceToDelete;
    }

    @Override
    public void doBehavior () {
        myGame.getCurrentLevel().getGrid().removePiece(myPiece);
    }
    

}
