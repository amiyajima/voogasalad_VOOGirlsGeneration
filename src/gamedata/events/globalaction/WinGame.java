package gamedata.events.globalaction;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.GameState;
import gamedata.gamecomponents.IChangeGameState;


/**
 * Win the game
 *
 */
public class WinGame extends GameStateGlobalAction {

    public WinGame () {
        super("Win the game");
    }

    @Override
    public void doBehavior(GUIGrid grid) {
    	GameState.winGame();
    }

}
