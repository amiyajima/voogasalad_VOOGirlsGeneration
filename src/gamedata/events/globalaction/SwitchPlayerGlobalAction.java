package gamedata.events.globalaction;

import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;


/**
 * Switches to a player with a specified int ID
 *
 */
public class SwitchPlayerGlobalAction extends GlobalAction {
    public static final String DESCRIPTION = "Jump to player";
    private Game myGame;
    private int myPlayerTurnToJumpTo;

    public SwitchPlayerGlobalAction (Game game, int playerTurnToJumpTo) {
        super(DESCRIPTION);
        myGame = game;
        myPlayerTurnToJumpTo = playerTurnToJumpTo;
    }

    @Override
    public void doBehavior () {
        myGame.changeTurn(myPlayerTurnToJumpTo);
    }

}
