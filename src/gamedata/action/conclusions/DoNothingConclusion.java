package gamedata.action.conclusions;

import authoring_environment.GUIGrid;
import gamedata.action.ActionConclusion;
import gamedata.gamecomponents.Piece;

public class DoNothingConclusion implements ActionConclusion {

	@Override
	public void runConclusion(GUIGrid grid, Piece actor, Piece... receivers) {
		// do nothing
	}
	
	@Override
	public String toString() {
		return "Do Nothing";
	}
	
}

