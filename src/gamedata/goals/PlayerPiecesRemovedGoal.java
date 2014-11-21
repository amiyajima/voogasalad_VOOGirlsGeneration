package gamedata.goals;

import java.util.List;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;

/**
 * Defines a win condition where a specific Players pieces have been removed
 * from the board
 * 
 * @author Jesse
 *
 */
public class PlayerPiecesRemovedGoal extends Goal {

	private int myID;

	/**
	 * Constructor
	 * 
	 * @param p
	 *            Target Player
	 */
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
