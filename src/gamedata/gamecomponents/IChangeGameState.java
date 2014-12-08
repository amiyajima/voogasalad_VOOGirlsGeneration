package gamedata.gamecomponents;

/**
 * Interface used to classify Game object. Utilizes dependency injection to make visible
 * some methods in Game - namely those that switch Players, Levels, and Turns, while leaving
 * other Game methods hidden. 
 * @author Mike Zhu
 *
 */
public interface IChangeGameState {
	
	/**
	 * End the current player's turn and move on to the next player
	 */
	public abstract void endTurn();
	
	/**
	 * End the game
	 */
	public abstract void endGame(int gameStatus);

	
	/**
	 * Change from the current level to one specified by its String name
	 * @param name
	 */
	public abstract void changeLevel(String name);
}
