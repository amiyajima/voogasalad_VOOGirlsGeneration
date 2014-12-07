package gamedata.events;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

/**
 * Deletes a specified piece off the grid
 * @author Rica, Mike Zhu
 *
 */
public class DeletePieceGlobalAction extends GlobalAction {
	public static final String DESCRIPTION = "Delete ";
    private Game myGame;
    private String myID;
    
    /**
     * Make sure you construct this referring to the piece that you want to delete rather than 
     * creating a new piece because the grid will try to look for that piece to delete when the
     * method is called
     * @param game
     * @param pieceToDelete
     */
    public DeletePieceGlobalAction(String name, Game game, String ID) {
        super(name);
        myGame = game;
        myID=ID;
    }

    @Override
    public void doBehavior () {
       GUIGrid grid =  myGame.getCurrentLevel().getGrid();
       for(Piece p : grid.getPieces().getData()){
           if(p.getID().equals(myID)){
               grid.removePiece(p);
           }
       }
    }
}
