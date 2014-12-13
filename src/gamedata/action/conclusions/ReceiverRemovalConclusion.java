package gamedata.action.conclusions;

import authoring_environment.GUIGrid;
import gamedata.action.ActionConclusion;
import gamedata.gamecomponents.Piece;

public class ReceiverRemovalConclusion implements ActionConclusion {

	@Override
	public void runConclusion(GUIGrid grid, Piece actor, Piece... receivers) {
		for (Piece receiver : receivers) {
			receiver.markForRemoval();
		}
	}
	
	@Override
	public String toString() {
		return "Receiver Removal";
	}

}
