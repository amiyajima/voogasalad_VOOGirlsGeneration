package gamedata.action;

import gamedata.gamecomponents.Piece;

public class ActorRemovalConclusion implements ActionConclusion {

	@Override
	public void runConclusion(Piece actor, Piece... receivers) {
		actor.markForRemoval();
	}

}
