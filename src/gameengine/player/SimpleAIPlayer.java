package gameengine.player;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;

import java.util.List;
import java.util.Set;

/**
 * Defines a simple AI for playing turn-based strategy games
 * 
 * @author Jesse
 *
 */
public class SimpleAIPlayer extends Player {

	private Level myCurrentLevel;

	public SimpleAIPlayer(int id) {
		super(id);
	}

	@Override
	public void startTurn(Level l) {
		myCurrentLevel = l;
		this.play();
	}

	private void play() {
		List<Piece> myPieces = myCurrentLevel.getGrid().getPlayerPieces(
				this.myID);
		
	}

}
