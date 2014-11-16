package gamedata.goals;

import gamedata.gamecomponents.Level;
import gameengine.player.Player;

public class PlayerPiecesRemovedGoal extends Goal {

	private int myID;

	public PlayerPiecesRemovedGoal(Player p) {
		//myID = p.getID();
	}

	@Override
	public int checkGameState(Level l) {
		// Loop through and check to see if all pieces of myID have been removed
		return 0;
	}

}
