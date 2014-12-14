package gamedata.events.globalaction;

import gamedata.action.StatsDataModifier;
import gamedata.action.StatsTotalLogic;
import gamedata.gamecomponents.GameState;
import gamedata.gamecomponents.IHasStats;
import gameengine.player.Player;
import java.util.ArrayList;
import java.util.List;
import authoring_environment.GUIGrid;


/**
 * Global Action that modifies a player's stat based on a constant
 * 
 * @author annamiyajima
 *
 */
public class ChangePlayerStats extends GlobalAction {
    private int myPlayerID;
    private String myStatName;
    private int myStatValue;

    public ChangePlayerStats (String statName, int value, int ID ) {
        super("Change player stat");
        myStatValue = value;
        myStatName = statName;
        myPlayerID = ID;
    }
    
    @Override
    public void doBehavior (GUIGrid grid) {
        List<Player> playerList = GameState.playersList;
        for(Player p : playerList){
            if (p.getID()==myPlayerID){
                System.out.println("ChangerPlayerStat: before = " + p.getStats().getValue(myStatName));
                p.changeStat(myStatName, p.getStats().getValue(myStatName)+myStatValue);
                System.out.println("ChangerPlayerStat: before = " + p.getStats().getValue(myStatName));
            }
        }
    }

}
