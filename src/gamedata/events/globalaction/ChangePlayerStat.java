package gamedata.events.globalaction;

import gamedata.action.StatsDataModifier;
import gamedata.action.StatsTotalLogic;
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
public class ChangePlayerStat extends GlobalAction {
    private List<StatsTotalLogic> myStatsLogics;
    private StatsDataModifier myStatsModifier;
    private IHasStats myActor;
    private Player myReceiver;

    public ChangePlayerStat (List<StatsTotalLogic> statsLogics, IHasStats actor, Player receiver) {
        super("Change player stat");
        myStatsLogics = statsLogics;
        myStatsModifier = new StatsDataModifier(myStatsLogics);
        myActor = actor;
        myReceiver = receiver;
    }

    @Override
    public void doBehavior (GUIGrid grid) {
        myStatsModifier.modifyStats(myActor, myReceiver);
    }

}
