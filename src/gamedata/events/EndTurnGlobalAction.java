package gamedata.events;

import gamedata.gamecomponents.Game;

public class EndTurnGlobalAction implements GlobalAction {
    private Game myGame;
    private int myPlayerTurnToJumpTo;
    
    public EndTurnGlobalAction (Game game, int playerTurnToJumpTo) {
        myGame = game;
        myPlayerTurnToJumpTo = playerTurnToJumpTo;
    }

    @Override
    public void doBehavior () {
        myGame.changeTurn(myPlayerTurnToJumpTo);
    }

}
