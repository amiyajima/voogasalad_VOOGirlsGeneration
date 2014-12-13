package gamedata.events;

import gamedata.events.globalaction.GlobalAction;
import gamedata.gamecomponents.IChangeGameState;

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
