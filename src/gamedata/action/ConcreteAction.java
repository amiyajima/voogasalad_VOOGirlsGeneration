package gamedata.action;

import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
 * @author Jennie Ju, annamiyajima
 */
public class ConcreteAction implements Action {
    private String myName;
    private List<Point2D.Double> myAttackRange;
    private List<Point2D.Double> myEffectRange;
    private List<StatsTotalLogic> myStatsLogics;
    private ActionConclusion myConclusion;
    private StatsDataModifier myDataModifier;
    
    /**
     * ConcreteAction constructor
     * Called when a new Action is made and
     * its behavior is already defined
     */
    public ConcreteAction (String id, List<Point2D.Double> attackRange,
                           List<Point2D.Double> effectRange, List<StatsTotalLogic> statsLogics,
                           ActionConclusion conclusion) {
        myName = id;
        myAttackRange = attackRange;
        myEffectRange = effectRange;
        myStatsLogics = statsLogics;
        myConclusion = conclusion;
        myDataModifier = new StatsDataModifier(myStatsLogics);
    }

    @Override
    public List<Point2D.Double> getAbsoluteActionRange (Point2D pieceLoc) {
    	return getAbsoluteRange(pieceLoc, myAttackRange);
    }
    
	@Override
	public List<Point2D.Double> getAbsoluteEffectRange(Point2D pieceLoc) {
		return getAbsoluteRange(pieceLoc, myEffectRange);
	}
	
	private List<Point2D.Double> getAbsoluteRange(Point2D pieceLoc, 
			List<Point2D.Double> range) {
		List<Point2D.Double> absoluteRange = new ArrayList<Point2D.Double>();
        for (Point2D relativeLoc : range) {
            double absX = pieceLoc.getX() + relativeLoc.getX();
            double absY = pieceLoc.getY() + relativeLoc.getY();
            absoluteRange.add(new Point2D.Double(absX, absY));
        }
        return absoluteRange;
	}

    @Override
    public List<Point2D.Double> getActionRange () {
        return myAttackRange;
    }

    @Override
    public List<Point2D.Double> getEffectRange () {
        return myEffectRange;
    }

    /**
     * Executes behavior of the action.
     * Modifies the necessary stats and runs
     * the conclusion
     */
    @Override
    public void doBehavior (GUIGrid grid, Piece actor, Piece ... receivers) {
        modifyStats(actor, receivers);
        runConclusion(grid, actor, receivers);
    }

    /**
     * Returns the logic contained in the ConcreteAction for
     * editing in the Authoring Environment
     * 
     * @return
     */
    public List<StatsTotalLogic> getStatsLogics () {
        return myStatsLogics;
    }

    private void modifyStats (Piece actor, Piece[] receivers) {
        if (myStatsLogics != null) {
            for (Piece receiver : receivers) {
                myDataModifier.modifyStats(actor, receiver);
            }
        }
    }

    private void runConclusion (GUIGrid grid, Piece actor, Piece[] receivers) {
        if (myConclusion != null) {
            myConclusion.runConclusion(grid, actor, receivers);
        }
    }

   
    @Override
    public String toString () {
        return myName;
    }

    @Override
    public String getName () {
        return myName;
    }

    public ActionConclusion getConclusion () {
        return myConclusion;
    }

}
