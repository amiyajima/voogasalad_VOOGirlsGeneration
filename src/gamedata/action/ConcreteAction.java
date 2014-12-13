package gamedata.action;

import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import authoring_environment.GUIGrid;

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
	private List<Point2D.Double> myAttackRange;
	private List<Point2D.Double> myEffectRange;
	private List<StatsTotalLogic> myStatsLogics;
	private ActionConclusion myConclusion;

	/**
	 * ConcreteAction constructor
	 * Called when a new Action is made and
	 * its behavior is already defined
	 */
	public ConcreteAction(String id, List<Point2D.Double> attackRange, 
			List<Point2D.Double> effectRange, List<StatsTotalLogic> statsLogics,
			ActionConclusion conclusion) {
		myName = id;
		myAttackRange = attackRange;
		myEffectRange = effectRange;
		myStatsLogics = statsLogics;
		myConclusion  = conclusion;
	}

	@Override
	public List<Point2D.Double> getAbsoluteActionRange(Point2D pieceLoc) {
		List<Point2D.Double> absoluteRange = new ArrayList<Point2D.Double>();
		for (Point2D relativeLoc : myAttackRange) {
			double absX = pieceLoc.getX() + relativeLoc.getX();
			double absY = pieceLoc.getY() + relativeLoc.getY();
			absoluteRange.add(new Point2D.Double(absX, absY));
		}
		return absoluteRange;
	}

	@Override
        public List<Point2D.Double> getActionRange() {
                return myAttackRange;
        }
	
	@Override
	public List<Point2D.Double> getEffectRange() {
		return myEffectRange;
	}

	/**
	 * Executes behavior of the action.
	 * Modifies the necessary stats and runs
	 * the conclusion
	 */
	@Override
	public void doBehavior(GUIGrid grid, Piece actor, Piece... receivers) {
		modifyStats(actor, receivers);
		runConclusion(grid, actor, receivers);
	}
	
	/**
	 * Returns the logic contained in the ConcreteAction for 
	 * editing in the Authoring Environment
	 * @return
	 */
	public List<StatsTotalLogic> getStatsLogics() {
		return myStatsLogics;
	}

	private void modifyStats(Piece actor, Piece[] receivers) {
		if (myStatsLogics != null) {
			for (Piece receiver : receivers) {
				modifyStats(actor, receiver);
			}
		}
	}

	private void runConclusion(GUIGrid grid, Piece actor, Piece[] receivers) {
		if (myConclusion != null) {
			myConclusion.runConclusion(grid, actor, receivers);
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
				actorStats.setValue(stl.getStatName(), result);
			} else if (stl.checkTarget("receiver")) {
				receiverStats.setValue(stl.getStatName(), result);
			} 
		}
	}

	private double evaluateMultiplierLogic(Stats actorStats, 
			Stats receiverStats, List<StatsSingleMultiplier> multiplierLogic) {
		double result = 0;
		for (StatsSingleMultiplier ssm : multiplierLogic) {
			double doubleValue = 0;
			String multiplierValue = ssm.getStatName();
			System.out.println(multiplierValue);
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
	
	@Override
	public String toString() {
	    String myString = "actionid:" + myName;
	    myString += " attack: ";
	    for (Point2D.Double pt : myAttackRange) {
	        myString += "(" + pt.getX() + " " + pt.getY() + ") ";
	    }
	    myString += " effect: ";
            for (Point2D.Double pt : myEffectRange) {
                myString += "(" + pt.getX() + " " + pt.getY() + ") ";
            }
            myString += " statslogic: ";
            for (StatsTotalLogic stat : myStatsLogics) {
                myString += "(" + stat.toString() + ") ";
            }
            myString += " effect: ";
            for (Point2D.Double pt : myEffectRange) {
                myString += "(" + pt.getX() + " " + pt.getY() + ") ";
            }
            myString += " conclusion: " + myConclusion.toString();
            return myString;
	}
	
	@Override
	public String getName() {
		return myName;
	}
}
