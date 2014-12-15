// This entire file is part of my masterpiece.
// Jennie Ju

package gamedata.action;

import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.Consumer;

import authoring.concretefeatures.RangeEditor;
import authoring_environment.GUIGrid;

/**
 * A concrete instance of an Action.
 * All Actions defined by the user will be an
 * instance of this class.
 * 
 * See Action interface for descriptions of public methods.
 * 
 * @author Jennie Ju, annamiyajima, Sandy Lee
 */
public class ConcreteAction implements Action {
	private String myName;
	private RelativeRange myRelativeActionRange;
	private RelativeRange myRelativeEffectRange;
	private List<StatsTotalLogic> myStatsLogics;
	private StatsDataModifier myDataModifier;
	private ActionConclusion myConclusion;

	/**
	 * ConcreteAction constructor
	 * Called when a new Action is made and
	 * its behavior is already defined
	 */
	public ConcreteAction (String name, List<Point2D.Double> attackRange,
			List<Point2D.Double> effectRange, List<StatsTotalLogic> statsLogics,
			ActionConclusion conclusion) {
		myName = name;
		myRelativeActionRange = new RelativeRange(attackRange);
		myRelativeEffectRange = new RelativeRange(effectRange);
		myStatsLogics = statsLogics;
		myDataModifier = new StatsDataModifier(statsLogics);
		myConclusion = conclusion;
	}

	@Override
	public String toString () {
		return getName();
	}

	@Override
	public String getName () {
		return myName;
	}

	@Override
	public List<Point2D.Double> getAbsoluteActionRange (Point2D currLoc) {
		return myRelativeActionRange.calculateAbsoluteRange(currLoc);
	}

	@Override
	public List<Point2D.Double> getAbsoluteEffectRange (Point2D currLoc) {
		return myRelativeEffectRange.calculateAbsoluteRange(currLoc);
	}

	@Override
	public RangeEditor getActionRangeEditor(Consumer<List<Point2D.Double>> consumer,
			String gridShape) {
		return myRelativeActionRange.displayOnRangeEditor(consumer, gridShape);
	}

	@Override
	public RangeEditor getEffectRangeEditor(Consumer<List<Point2D.Double>> consumer,
			String gridShape) {
		return myRelativeEffectRange.displayOnRangeEditor(consumer, gridShape);
	}

	/**
	 * Executes the behavior of the action.
	 * Modifies the necessary stats and runs the conclusion
	 */
	@Override
	public void doBehavior (GUIGrid grid, Piece actor, Piece ... receivers) {
		modifyStats(actor, receivers);
		myConclusion.runConclusion(grid, actor, receivers);
	}
	
	private void modifyStats (Piece actor, Piece[] receivers) {
		for (Piece receiver : receivers) {
			myDataModifier.modifyStats(actor, receiver);
		}
	}

	/**
	 * Returns the logic contained in the ConcreteAction for
	 * editing in the Authoring Environment
	 * 
	 * @return
	 */
	public ActionStatsEditor initializeStatsEditor() {
		return new ActionStatsEditor(myStatsLogics);
	}

	/**
	 * Returns the name of the type of ActionConclusion
	 * used in this instance of ConcreteAction for 
	 * display purposes in the Authoring Environment
	 * 
	 * @return String name identifier of the Conclusion
	 */
	public String getConclusionName () {
		return myConclusion.toString();
	}
}
