package gamedata.action;

import java.util.List;
import java.awt.geom.Point2D;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.Piece;

/**
 * Interface of a game component that performs
 * an action on another component in the game.
 * Pieces will contain a list of actions.
 * 
 * @author Jennie Ju
 */
public interface Action {
	
	/**
	 * Returns the information about the Action for display
	 * @return name of Action
	 */
	public String toString();
	
	/**
	 * Returns the name of the Action for display
	 * @return name of Action
	 */
	public String getName();
	
	/**
	 * Gives back a list of Point2D of absolute
	 * locations for the action range
	 * @return list of absolute locations in Point2D
	 */
	public List<Point2D.Double> getAbsoluteActionRange(Point2D pieceLocation);
	
	/**
	 * Gives back a list of Point2D of relative
	 * locations for the action range
	 * @return list of absolute locations in Point2D
	 */
	public List<Point2D.Double> getActionRange();
        
	/**
	 * Gives back a list of Point2D of relative locations
	 * for the effect range of the action (splashzone)
	 * @return list of relative locations in Point2D
	 */
	public List<Point2D.Double> getEffectRange();
	
	/**
	 * Executes an action on a component of
	 * the game (i.e. a piece, patch, or other module)
	 */
	public void doBehavior(GUIGrid grid, Piece actor, Piece... receivers);
}
