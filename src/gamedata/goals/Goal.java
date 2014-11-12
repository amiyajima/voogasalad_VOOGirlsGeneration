package gamedata.goals;

import gamedata.grid.Level;

public abstract class Goal {

	public Goal() {

	}

	public abstract int checkGameState(Level l);
}
