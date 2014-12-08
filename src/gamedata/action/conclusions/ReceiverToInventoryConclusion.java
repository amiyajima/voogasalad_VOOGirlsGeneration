package gamedata.action.conclusions;

import gamedata.action.ActionConclusion;
import gamedata.gamecomponents.Piece;

public class ReceiverToInventoryConclusion implements ActionConclusion {

	@Override
	public void runConclusion(Piece actor, Piece... receivers) {
		for (Piece receiver : receivers) {
			actor.addToInventory(receiver);
		}
	}

}
