package gamedata.action.conclusions;

import gamedata.action.ActionConclusion;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import authoring_environment.GUIGrid;

public class ReceiverToInventoryConclusion implements ActionConclusion {

	@Override
	public void runConclusion(GUIGrid grid, Piece actor, Piece... receivers) {
		for (Piece receiver : receivers) {
			actor.addToInventory(new Piece(receiver,new Point2D.Double(0,0)));
			receiver.markForRemoval();
		}
	}
	
	@Override
	public String toString() {
		return "Receiver to Inventory";
	}

}
