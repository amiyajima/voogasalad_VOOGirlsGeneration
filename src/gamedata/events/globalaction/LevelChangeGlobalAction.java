package gamedata.events.globalaction;

import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level;


public class LevelChangeGlobalAction extends GlobalAction {
    public static final String DESCRIPTION = "Change level";
    private Game myGame;
    private Level myLevelToJumpTo;

    public LevelChangeGlobalAction (Game game, Level l) {
        super(DESCRIPTION + " to " + l);
        myGame = game;
        myLevelToJumpTo = l;
    }

    @Override
    public void doBehavior () {
        // TODO Auto-generated method stub
        myGame.setCurrentLevel(myLevelToJumpTo);
    }

}
