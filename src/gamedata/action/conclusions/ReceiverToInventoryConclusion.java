package gamedata.action.conclusions;

import authoring_environment.GUIGrid;
import gamedata.action.ActionConclusion;
import gamedata.gamecomponents.Piece;

public class ReceiverToInventoryConclusion implements ActionConclusion {

	@Override
	public void runConclusion(GUIGrid grid, Piece actor, Piece... receivers) {
		for (Piece receiver : receivers) {
			actor.addToInventory(receiver);
		}
	}

}
