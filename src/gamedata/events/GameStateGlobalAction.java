package gamedata.events;

import gamedata.gamecomponents.IChangeGameState;

public abstract class GameStateGlobalAction extends GlobalAction{

	protected IChangeGameState myState;
	
	protected GameStateGlobalAction(String s, IChangeGameState state) {
		super(s);
		myState = state;
	}

	public abstract void doBehavior();

}
