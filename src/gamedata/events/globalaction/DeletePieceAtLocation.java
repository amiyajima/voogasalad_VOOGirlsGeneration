package gamedata.events.globalaction;

import java.awt.geom.Point2D;
import java.util.List;

import authoring_environment.GUIGrid;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;

/**
 * Deletes a piece at a specified point
 * @author Mike Zhu
 *
 */
public class DeletePieceAtLocation extends GlobalAction {	
    
	Point2D myLoc;
	
    /**
     * Make sure you construct this referring to the piece that you want to delete rather than 
     * creating a new piece because the grid will try to look for that piece to delete when the
     * method is called
     * @param game
     * @param name of type of piece to delete
     */
    public DeletePieceAtLocation(Point2D point) {
        super(String.format("Delete Piece at position %s", point));
        myLoc = point;
    }

    @Override
    public void doBehavior(GUIGrid myGrid) {
    	myGrid.removePieceAtCoordinate(myLoc);
    }
}
