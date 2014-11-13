package gamedata.goals;

import gamedata.gamecomponents.Level;

public abstract class Goal {

	public Goal() {

	}

	public abstract int checkGameState(Level l);
}
