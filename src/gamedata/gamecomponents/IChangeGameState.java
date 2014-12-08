package gamedata.gamecomponents;

/**
 * Interface used to classify Game object. Utilizes dependency injection to make visible
 * some methods in Game - namely those that switch Players, Levels, and Turns, while leaving
 * other Game methods hidden. 
 * @author Mike Zhu
 *
 */
public interface IChangeGameState {
	public abstract void winGame();
	
	public abstract void loseGame();
	
	public abstract void endTurn();
	
	public abstract void changeLevel(String name);
}
