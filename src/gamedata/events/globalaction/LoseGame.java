package gamedata.events.globalaction;

import authoring_environment.GUIGrid;
import gamedata.events.GameStateGlobalAction;
import gamedata.gamecomponents.IChangeGameState;


/**
 * Win the game
 *
 */
public class LoseGame extends GameStateGlobalAction {

    public LoseGame (IChangeGameState state) {
        super("Lose the game", state);
    }

    @Override
    public void doBehavior(GUIGrid grid) {
    	myState.loseGame();
    }

}
