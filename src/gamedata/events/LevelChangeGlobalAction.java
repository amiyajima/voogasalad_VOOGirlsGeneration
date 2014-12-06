package gamedata.events;

import gamedata.gamecomponents.Game;

/**
 * If this action is run, it will make the game jump to the level that this game is set to jump to
 * @author Rica
 *
 */
public class LevelChangeGlobalAction extends GlobalAction {
	public static final String DESCRIPTION = "Change level";
    private Game myGame;
    private String myLevelToJumpTo;

    public LevelChangeGlobalAction(Game game, String levelToJumpTo) {
    	super(DESCRIPTION + " to " + levelToJumpTo);
        myGame = game;
        myLevelToJumpTo = levelToJumpTo;
    }
    
    @Override
    public void doBehavior () {
        // TODO Auto-generated method stub
        myGame.jumpToLevel(myLevelToJumpTo);
    }

}
