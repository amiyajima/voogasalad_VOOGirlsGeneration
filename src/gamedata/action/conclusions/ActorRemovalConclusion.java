package gamedata.action.conclusions;

import authoring_environment.GUIGrid;
import gamedata.action.ActionConclusion;
import gamedata.gamecomponents.Piece;

public class ActorRemovalConclusion implements ActionConclusion {

	@Override
	public void runConclusion(GUIGrid grid, Piece actor, Piece... receivers) {
		actor.markForRemoval();
	}
	
	@Override
	public String toString() {
		return "Actor Removal";
	}
	
}
