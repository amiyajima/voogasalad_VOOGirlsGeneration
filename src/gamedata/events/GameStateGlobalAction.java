package gamedata.events;

import gamedata.gamecomponents.IChangeGameState;

/**
 * GameStateGlobalActions modify the state of a game - whether the game is won/lost,
 * level changed, turn ends, etc. - rather than modifying an individual grid. In the 
 * constructor, they take in a Level, wrapped in an IChangeGameState interface which
 * prevents access to Level methods that are unneeded (e.g., those that modify Events, 
 * Pieces, etc. - all stuff these GlobalActions don't need to effect).
 * @author Mike Zhu
 *
 */
public abstract class GameStateGlobalAction extends GlobalAction{

	protected IChangeGameState myState;
	
	protected GameStateGlobalAction(String s, IChangeGameState state) {
		super(s);
		myState = state;
	}
	
	public void reinject(IChangeGameState target){
		myState = target;
	}

}
