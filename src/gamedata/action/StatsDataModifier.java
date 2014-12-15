package gamedata.action;

import gamedata.gamecomponents.IHasStats;
import gamedata.stats.Stats;
import java.util.List;


// THIS IS A PART OF MY CODE MASTERPIECE
// ANNA MIYAJIMA (am437)

/**
 * Performs stats calculations to modify stats of an actor or a receiver
 * Extracted from Concrete action to be applied in ChangePlayerStat global action
 * 
 * @author annamiyajima
 *
 */
public class StatsDataModifier {

    private static final String ACTOR = "actor";
    private static final String RECEIVER = "receiver";
    private static final String CONSTANT = "constant";
    
    private StatsTotalLogic myStatsLogic;

    public StatsDataModifier (StatsTotalLogic statsLogic) {
        myStatsLogic = statsLogic;
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

        List<StatsSingleMultiplier> multiplierLogic = myStatsLogic.getMultiplierLogic();
        double result = evaluateMultiplierLogic(actorStats, receiverStats,
                                                multiplierLogic);
        if (myStatsLogic.checkTarget(ACTOR)) {
            actorStats.setValue(myStatsLogic.getStatName(), result);
        }
        else if (myStatsLogic.checkTarget(RECEIVER)) {
            receiverStats.setValue(myStatsLogic.getStatName(), result);
        }

    }

    /**
     * Given the logic that must happen, changes the stats of the actor and receiver
     * 
     * @param actorStats
     * @param receiverStats
     * @param multiplierLogic
     * @return
     */
    private double evaluateMultiplierLogic (Stats actorStats,
                                            Stats receiverStats,
                                            List<StatsSingleMultiplier> multiplierLogic) {
        double result = 0;
        for (StatsSingleMultiplier ssm : multiplierLogic) {
            double doubleValue = 0;
            String multiplierValue = ssm.getStatName();
            if (ssm.checkTarget(ACTOR)) {
                doubleValue = actorStats.getValue(multiplierValue);
            }
            else if (ssm.checkTarget(RECEIVER)) {
                doubleValue = receiverStats.getValue(multiplierValue);
            }
            else if (ssm.checkTarget(CONSTANT)) {
                doubleValue = Integer.parseInt(multiplierValue);
            }
            result += ssm.getModifier() * doubleValue;
        }
        return result;
    }

}
