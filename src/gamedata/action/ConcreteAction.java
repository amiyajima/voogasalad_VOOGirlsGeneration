package gamedata.action;

import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * A concrete instance of an Action.
 * All Actions defined by the user will be an
 * instance of this class.
 * 
 * See Action interface for descriptions of public methods
 * 
 * @author Jennie Ju
 *
 */
public class ConcreteAction implements Action {
	private String myName;
	private List<Point2D> myAttackRange;
	private List<Point2D> myEffectRange;
	private List<StatsTotalLogic> myStatsLogics;
	private ActionConclusion myConclusion;

	/**
	 * ConcreteAction constructor
	 * Called when a new Action is made and
	 * its behavior is already defined
	 */
	public ConcreteAction(String name, List<Point2D> attackRange, 
			List<Point2D> effectRange, List<StatsTotalLogic> statsLogics,
			ActionConclusion conclusion) {
		myName = name;
		myAttackRange = attackRange;
		myEffectRange = effectRange;
		myStatsLogics = statsLogics;
		myConclusion  = conclusion;
	}
	
	@Override
	public String toString() {
		return myName;
	}

	@Override
	public List<Point2D> getActionRange(Point2D pieceLoc) {
		List<Point2D> absoluteRange = new ArrayList<Point2D>();
		for (Point2D relativeLoc : myAttackRange) {
			double absX = pieceLoc.getX() + relativeLoc.getX();
			double absY = pieceLoc.getY() + relativeLoc.getY();
			absoluteRange.add(new Point2D(absX, absY));
		}
		return absoluteRange;
	}

	@Override
	public List<Point2D> getEffectRange() {
		return myEffectRange;
	}

	/**
	 * Executes behavior of the action.
	 * Modifies the necessary stats and runs
	 * the conclusion
	 */
	@Override
	public void doBehavior(Piece actor, Piece... receivers) {
		modifyStats(actor, receivers);
		runConclusion(actor, receivers);
	}

	private void modifyStats(Piece actor, Piece[] receivers) {
		if (myStatsLogics != null) {
			for (Piece receiver : receivers) {
				modifyStats(actor, receiver);
			}
		}
	}

	private void runConclusion(Piece actor, Piece[] receivers) {
		if (myConclusion != null) {
			myConclusion.runConclusion(actor, receivers);
		}
	}

	private void modifyStats(Piece actor, Piece receiver) {
		Stats actorStats = actor.getStats();
		Stats receiverStats = receiver.getStats();
		for (StatsTotalLogic stl : myStatsLogics) {
			List<StatsSingleMultiplier> multiplierLogic = stl.getMultiplierLogic();
			double result = evaluateMultiplierLogic(actorStats, receiverStats,
					multiplierLogic);
			if (stl.checkTarget("actor")) {
				actorStats.setValue(stl.getValue(), result);
			} else if (stl.checkTarget("receiver")) {
				receiverStats.setValue(stl.getValue(), result);
			} 
		}
	}

	private double evaluateMultiplierLogic(Stats actorStats, 
			Stats receiverStats, List<StatsSingleMultiplier> multiplierLogic) {
		double result = 0;
		for (StatsSingleMultiplier ssm : multiplierLogic) {
			double doubleValue = 0;
			String multiplierValue = ssm.getValue();
			if (ssm.checkTarget("actor")) {
				doubleValue = actorStats.getValue(multiplierValue);
			} else if (ssm.checkTarget("receiver")) {
				doubleValue = receiverStats.getValue(multiplierValue);
			} else if (ssm.checkTarget("constant")) {
				doubleValue = Integer.parseInt(multiplierValue);
			}
			result += ssm.getModifier()*doubleValue;
		}
		return result;
	}
}
