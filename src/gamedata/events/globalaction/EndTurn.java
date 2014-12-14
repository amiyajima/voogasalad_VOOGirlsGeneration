package gamedata.events.globalaction;

import java.util.List;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.GameState;
import gamedata.gamecomponents.IChangeGameState;
import gamedata.gamecomponents.IHasStats;


/**
 * Switches to a player with a specified int ID
 *
 */
public class EndTurn extends GameStateGlobalAction {

    public EndTurn () {
        super("End current turn");
    }

    @Override
    public void doBehavior(GUIGrid grid) {
    	GameState.endTurn();
    }

}
