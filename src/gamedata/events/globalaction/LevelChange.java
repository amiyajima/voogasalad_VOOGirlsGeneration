package gamedata.events.globalaction;

import gamedata.events.GameStateGlobalAction;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.IChangeGameState;
import gamedata.gamecomponents.Level;


public class LevelChange extends GameStateGlobalAction {
    public static final String DESCRIPTION = "Change level";
    private String myNextLevelID;
    
    public LevelChange (String name, IChangeGameState state, String levelID) {
        super(name, state);
        myNextLevelID = levelID;
    }

    @Override
    public void doBehavior () {
    	myState.changeLevel(myNextLevelID);
    }

}