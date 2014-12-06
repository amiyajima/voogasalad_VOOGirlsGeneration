package gamedata.events;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

public class CreatePieceGlobalAction implements GlobalAction {
    private Game myGame;
    private Piece myPiece;
    
    public CreatePieceGlobalAction (Game game, Piece pieceToCreate) {
        myGame = game;
        myPiece = pieceToCreate;
    }

    @Override
    public void doBehavior () {
        // TODO change the way piece is set
        myGame.getCurrentLevel().getGrid().setPiece(myPiece.getLoc(), myPiece);
        
    }

}
