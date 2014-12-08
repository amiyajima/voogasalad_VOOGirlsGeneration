package gamedata.events.globalaction;

import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.IChangeGameState;


/**
 * Switches to a player with a specified int ID
 *
 */
public class EndTurn extends GlobalAction {
	private IChangeGameState myState;

    public EndTurn (String name, IChangeGameState state) {
        super(name);
        myState = state;
    }

    @Override
    public void doBehavior () {
    	myState.endTurn();
    }
}
