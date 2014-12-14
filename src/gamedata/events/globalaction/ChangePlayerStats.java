package gamedata.events.globalaction;

import gamedata.action.StatsDataModifier;
import gamedata.action.StatsTotalLogic;
import gamedata.events.GameStateGlobalAction;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.IHasStats;
import gameengine.player.Player;
import java.util.ArrayList;
import java.util.List;
import authoring_environment.GUIGrid;


/**
 * Global Action that modifies a player's stat based on another stat or constant
 * 
 * @author annamiyajima
 *
 */
public class ChangePlayerStats extends GlobalAction {
    private int myPlayerID;
    private String myStatName;
    private int myConstant;

    public ChangePlayerStats (int ID, String statName, int constant) {
        super("Change player stat");
        myPlayerID = ID;
        myStatName = statName;
        myConstant = constant;
    }

    @Override
    public void doBehavior (GUIGrid grid) {
        
    }

}
