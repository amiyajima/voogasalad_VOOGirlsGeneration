package gamedata.events.globalaction;

import authoring_environment.GUIGrid;
import gamedata.events.GameStateGlobalAction;
import gamedata.gamecomponents.IChangeGameState;


/**
 * Win the game
 *
 */
public class WinGame extends GameStateGlobalAction {

    public WinGame (IChangeGameState state) {
        super("Win the game", state);
    }

    @Override
    public void doBehavior(GUIGrid grid) {
    	myState.winGame();
    }

}
