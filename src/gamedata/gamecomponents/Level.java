package gamedata.gamecomponents;

import java.util.List;
import gamedata.goals.*;
import gamedata.rules.*;

public class Level {

	private Game myGame;
	private Grid myGrid;
	private List<Goal> myGoals;
	private List<Rule> myRules;

	public Level(Game ga, Grid gr) {
		myGame = ga;
		myGrid = gr;
	}

	/**
	 * Checks to see if the level goals have been met.
	 * 
	 * @return
	 */
	public void checkLevelStatus() {
		for (Goal g : myGoals) {
			if (g.checkGameState(this) == 1) {
				myGame.nextLevel();
			} else if (g.checkGameState(this) == -1) {
				myGame.restartLevel();
			}
		}
	}

	public void checkTurn() {
		for (Rule r : myRules) {
			if (r.isTriggered()) {
				myGame.nextPlayer();
			}
		}
	}

	public Grid getGrid() {
		return myGrid;
	}
}
