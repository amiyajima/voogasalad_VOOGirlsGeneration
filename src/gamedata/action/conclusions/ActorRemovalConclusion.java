package gamedata.action.conclusions;

import gamedata.action.ActionConclusion;
import gamedata.gamecomponents.Piece;

public class ActorRemovalConclusion implements ActionConclusion {

	@Override
	public void runConclusion(Piece actor, Piece... receivers) {
		actor.markForRemoval();
	}

}
