package gamedata.events.globalaction;

import authoring_environment.GUIGrid;
import gamedata.events.GameStateGlobalAction;
import gamedata.gamecomponents.IChangeGameState;

/**
 * Takes in a Level object wrapped in the IChangeGameState interface. This doBehavior 
 * method marks the Level as complete and sets the next level target. 
 * @author Mike Zhu
 *
 */
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
