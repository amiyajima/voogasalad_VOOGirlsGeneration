package gamedata.events.globalactions;

import gamedata.gamecomponents.Game;
import gameengine.player.Player;
import java.util.function.Consumer;


/**
 * Action that switches current player of a level to a player defined by the authoring environment
 * 
 * @author annamiyajima
 *
 */
public class SwitchPlayerGlobalAction implements GlobalAction {

    private Player myPlayer;

    public SwitchPlayerGlobalAction (Player p) {
        myPlayer = p;
    }

    @Override
    public void performAction () {
        // lambda called by level when conditions are met
        Consumer<Game> c = (Game g) -> {
            g.setCurrentPlayer(myPlayer);
            ;
        };

    }

}
