package gamedata.action;

import gamedata.gamecomponents.Piece;

public class ReceiverRemovalConclusion implements ActionConclusion {

	@Override
	public void runConclusion(Piece actor, Piece... receivers) {
		for (Piece receiver : receivers) {
			receiver.markForRemoval();
		}
	}

}