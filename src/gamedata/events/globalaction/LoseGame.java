package gamedata.events.globalaction;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.GameState;
import gamedata.gamecomponents.IChangeGameState;


/**
 * Win the game
 *
 */
public class LoseGame extends GameStateGlobalAction {

    public LoseGame () {
        super("Lose the game");
    }

    @Override
    public void doBehavior(GUIGrid grid) {
    	GameState.loseGame();
    }

}
