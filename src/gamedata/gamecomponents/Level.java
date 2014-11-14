package gamedata.gamecomponents;

import java.util.List;
import gamedata.goals.*;
import gamedata.rules.*;

public class Level {

	// rules for the level and any global events (ie. fire kills half of the
	// scren)
	// goals AKA win/lose conditions for the level
	private Grid myGrid;
	private List<Goal> myGoals;
	private List<Rule> myRules;

	public Level() {

	}

	/**
	 * Checks to see if the level goals have been met.
	 * 
	 * @return
	 */
	public boolean isLevelWon() {
		boolean b = false;
		for (Goal g : myGoals) {
			b = g.checkGameState(this);
		}
		return b;
	}

}
