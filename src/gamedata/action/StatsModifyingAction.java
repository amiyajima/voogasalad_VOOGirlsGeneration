package gamedata.action;

import java.util.List;
import java.util.Map;

import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;

public class StatsModifyingAction implements Action {
	private List<StatsTotalLogic> myStatsLogics;

	public StatsModifyingAction(List<StatsTotalLogic> statslogics) {
		myStatsLogics = statslogics;
	}
	
	/**
	 * Executes an action that modifies the stats of 2 pieces 
	 * based on the mathematical logic stored in myStatsTotalLogic.
	 * 
	 */
	@Override
	public void doBehavior(Piece actor, Piece receiver) {
		// need method from pieces to get stats
		Stats actorStats = actor.getStats();
		Stats receiverStats = receiver.getStats();

		for (StatsTotalLogic stl : myStatsLogics) {
			List<StatsSingleMultiplier> multiplierLogic = stl.getMultiplierLogic();
			double result = 0;
			for (StatsSingleMultiplier ssm : multiplierLogic) {
				double doubleValue = 0;
				String multiplierValue = ssm.getValue();
				if (ssm.checkTarget("actor")) {
					doubleValue = actorStats.getStatValue(multiplierValue);
				} else if (ssm.checkTarget("receiver")) {
					doubleValue = receiverStats.getStatValue(multiplierValue);
				} else if (ssm.checkTarget("constant")) {
					doubleValue = Integer.parseInt(multiplierValue);
				}
				result += ssm.getModifier()*doubleValue;
			}
			
			if (stl.checkTarget("actor")) {
				actorStats.setStatValue(stl.getValue(), result);
			} else if (stl.checkTarget("receiver")) {
				actorStats.setStatValue(stl.getValue(), result);
			} 
		}
	}
}
