// This entire file is part of my masterpiece.
// Jennie Ju

package gamedata.action;

import java.util.List;
import java.util.function.Consumer;
import java.awt.geom.Point2D;

import authoring.concretefeatures.RangeEditor;
import authoring_environment.GUIGrid;
import gamedata.gamecomponents.Piece;

/**
 * Interface of a game component that performs
 * an action on another component in the game.
 * Pieces will contain a list of actions.
 * 
 * This class was written as an outline to the Action API
 * 
 * @author Jennie Ju
 */
public interface Action {
	
	/**
	 * Returns the name of the Action for display
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
	 * Gives back a list of Point2D of absolute
	 * locations for the effect range
	 * @return list of absolute locations in Point2D
	 */
	public List<Point2D.Double> getAbsoluteEffectRange(Point2D pieceLocation);
	
	/**
	 * Displays the relative action range on a RangeEditor for editing in
	 * the ActionEditor
	 * 
	 * @param consumer - Consumer lambda stating how data will be manipulated in
	 * the RangeEditor
	 * @param gridShape - String stating desired grid shape
	 * @return RangeEditor populated with highlighed tiles indicating the action range
	 */
	public RangeEditor getActionRangeEditor(Consumer<List<Point2D.Double>> consumer,
			String gridShape);
	
	/**
	 * Displays the relative effect range on a RangeEditor for editing in
	 * the ActionEditor
	 * 
	 * @param consumer - Consumer lambda stating how data will be manipulated in
	 * the RangeEditor
	 * @param gridShape - String stating desired grid shape
	 * @return RangeEditor populated with highlighed tiles indicating myRange
	 */
	public RangeEditor getEffectRangeEditor(Consumer<List<Point2D.Double>> consumer,
			String gridShape);
	
	/**
	 * Executes an action on a component of
	 * the game (i.e. a piece, patch, or other module)
	 */
	public void doBehavior(GUIGrid grid, Piece actor, Piece... receivers);
}
