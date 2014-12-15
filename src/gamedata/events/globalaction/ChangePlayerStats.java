package gamedata.events.globalaction;

import gamedata.action.StatsDataModifier;
import gamedata.action.StatsTotalLogic;
import gamedata.gamecomponents.IHasStats;
import gameengine.player.Player;

//THIS IS A PART OF MY CODE MASTERPIECE
//ANNA MIYAJIMA (am437)

/**
 * Global Action that modifies a player's stat. Delegates stats operations to a StatsDataModifier
 * 
 * @author annamiyajima
 *
 */
public class ChangePlayerStats extends GlobalAction<Player> {
    private StatsDataModifier myDataModifier;

    public ChangePlayerStats (StatsTotalLogic statsLogic) {
        super("Change player stat");
        myDataModifier = new StatsDataModifier(statsLogic);
    }

    @Override
    public void doBehavior (Player p) {
        this.doBehavior(p);
    }

    public void doBehavior (Player p, IHasStats actor) {
        myDataModifier.modifyStats(actor, p);
    }

}
