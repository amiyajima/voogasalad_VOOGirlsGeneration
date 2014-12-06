package gamedata.events;

import gamedata.gamecomponents.Game;

/**
 * Ends the turn by changing to a new specified turn
 * @author Rica
 *
 */
public class EndTurnGlobalAction extends GlobalAction {
	public static final String DESCRIPTION = "End current player's turn";
    private Game myGame;
    private int myPlayerTurnToJumpTo;
    
    public EndTurnGlobalAction (Game game, int playerTurnToJumpTo) {
    	super(DESCRIPTION);
        myGame = game;
        myPlayerTurnToJumpTo = playerTurnToJumpTo;
    }

    @Override
    public void doBehavior () {
        myGame.changeTurn(myPlayerTurnToJumpTo);
    }

}
