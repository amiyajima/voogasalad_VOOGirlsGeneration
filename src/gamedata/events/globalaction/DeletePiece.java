package gamedata.events.globalaction;

import java.util.List;

import authoring_environment.GUIGrid;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;

/**
 * Deletes all pieces with less than 0 HP
 * @author Rica, Mike Zhu
 *
 */
public class DeletePiece extends GlobalAction {	
	private String myHealthName;
    
    /**
     * Make sure you construct this referring to the piece that you want to delete rather than 
     * creating a new piece because the grid will try to look for that piece to delete when the
     * method is called
     * @param game
     * @param name of type of piece to delete
     */
    public DeletePiece(String name, String healthName) {
        super(name);
        myHealthName = healthName;
    }

    @Override
    public void doBehavior (List<IHasStats> objects) {
       for(IHasStats obj : objects){
    	   Piece p = (Piece) obj;
    	   if(obj.getStats().getValue(myHealthName)<=0){
    		   p.shouldRemove();
    	   }
       }
    }
}
