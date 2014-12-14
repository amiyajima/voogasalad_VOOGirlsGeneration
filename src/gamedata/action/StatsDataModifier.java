package gamedata.action;

import gamedata.gamecomponents.IHasStats;
import gamedata.stats.Stats;
import java.awt.geom.Point2D.Double;
import java.util.List;

/**
 * Extracted from Concrete action to be applied in ChangePlayerStat global action
 * @author annamiyajima
 *
 */
public class StatsDataModifier {
   
    private List<StatsTotalLogic> myStatsLogics;
    
    public StatsDataModifier (List<StatsTotalLogic> myStatsLogics2) {
        myStatsLogics = myStatsLogics2;
    }
    /**
     * Takes in two objects that impelment IHasStats(piece,patch,player) and changes receiver's stat
     * based on actor's stat
     * 
     * @param actor
     * @param receiver
     */
    public void modifyStats (IHasStats actor, IHasStats receiver) {
        Stats actorStats = actor.getStats();
        Stats receiverStats = receiver.getStats();
        for (StatsTotalLogic stl : myStatsLogics) {
            List<StatsSingleMultiplier> multiplierLogic = stl.getMultiplierLogic();
            double result = evaluateMultiplierLogic(actorStats, receiverStats,
                                                    multiplierLogic);
            if (stl.checkTarget("actor")) {
                actorStats.setValue(stl.getStatName(), result);
            }
            else if (stl.checkTarget("receiver")) {
                receiverStats.setValue(stl.getStatName(), result);
            }
        }
    }

    
    private double evaluateMultiplierLogic (Stats actorStats,
                                            Stats receiverStats,
                                            List<StatsSingleMultiplier> multiplierLogic) {
        double result = 0;
        for (StatsSingleMultiplier ssm : multiplierLogic) {
            double doubleValue = 0;
            String multiplierValue = ssm.getStatName();
            System.out.println(multiplierValue);
            if (ssm.checkTarget("actor")) {
                doubleValue = actorStats.getValue(multiplierValue);
            }
            else if (ssm.checkTarget("receiver")) {
                doubleValue = receiverStats.getValue(multiplierValue);
            }
            else if (ssm.checkTarget("constant")) {
                doubleValue = Integer.parseInt(multiplierValue);
            }
            result += ssm.getModifier() * doubleValue;
        }
        return result;
    }

}