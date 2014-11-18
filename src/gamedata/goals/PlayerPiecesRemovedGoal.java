package gamedata.goals;

import java.util.List;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;

public class PlayerPiecesRemovedGoal extends Goal {

	private int myID;

	public PlayerPiecesRemovedGoal(Player p) {
		myID = p.getID();
	}

	@Override
	public int checkGameState(Level l) {
		List<Piece> pieces = l.getGrid().getAllPieces();
		boolean b = true;
		for (Piece p : pieces) {
			if (p.getPlayerID() == myID) {
				b = false;
			}
		}
		if (b) {
			return 1;
		}
		return 0;
	}

}
