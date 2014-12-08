package gamedata.events.globalaction;

import java.util.List;

import authoring_environment.GUIGrid;
import gamedata.events.GameStateGlobalAction;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.IChangeGameState;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Level;


public class LevelChange extends GameStateGlobalAction {
    public static final String DESCRIPTION = "Change level";
    private String myNextLevelID;
    
    public LevelChange (IChangeGameState state, String levelID) {
        super(String.format("Change level to %s", levelID), state);
        myNextLevelID = levelID;
    }

    @Override
    public void doBehavior(GUIGrid grid) {
    	myState.changeLevel(myNextLevelID);
    }
}
