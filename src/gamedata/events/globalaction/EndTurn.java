package gamedata.events.globalaction;

import java.util.List;

import authoring_environment.GUIGrid;
import gamedata.events.GameStateGlobalAction;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.IChangeGameState;
import gamedata.gamecomponents.IHasStats;


/**
 * Switches to a player with a specified int ID
 *
 */
public class EndTurn extends GameStateGlobalAction {

    public EndTurn (String name, IChangeGameState state) {
        super(name, state);
    }

    @Override
    public void doBehavior(GUIGrid grid) {
    	myState.endTurn();
    }

}
