package gameengine.player;

import gamePlayer.GameGrid;
import java.util.Set;

/**
 * Defines a simple AI for playing turn-based strategy games
 * 
 * @author Jesse
 *
 */
public class SimpleAIPlayer extends Player {

	public SimpleAIPlayer(int id) {
		super(id);
	}

	@Override
	public void startTurn() {
		this.resetMovesPlayed();
		// TODO: IMPLEMENT AI BEHAVIOR HERE :D

	}

}
