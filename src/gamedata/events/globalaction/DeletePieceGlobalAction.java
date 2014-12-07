package gamedata.events.globalaction;

import authoring_environment.GUIGrid;
import gamedata.events.GlobalAction;
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
    private String myName;
    
    /**
     * Make sure you construct this referring to the piece that you want to delete rather than 
     * creating a new piece because the grid will try to look for that piece to delete when the
     * method is called
     * @param game
     * @param name of type of piece to delete
     */
    public DeletePieceGlobalAction(String name, Game game) {
        super(name);
        myGame = game;
        myName = name;
    }

    @Override
    public void doBehavior () {
       GUIGrid grid =  myGame.getCurrentLevel().getGrid();
       //currently needs to get all pieces and iterate through them
       for(Piece p : grid.){
           if(p.getName().equals(myName)){
               grid.removePiece(p);
           }
       }
    }
}
