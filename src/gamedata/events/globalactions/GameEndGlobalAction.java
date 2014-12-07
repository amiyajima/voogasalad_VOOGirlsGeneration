package gamedata.events.globalactions;

import gamedata.gamecomponents.Game;
import java.util.function.Consumer;

/**
 * Set the 
 * @author annamiyajima
 *
 */
public class GameEndGlobalAction implements GlobalAction {

    private int myStatus;

    public GameEndGlobalAction (int status) {
        myStatus = status;
    }

    @Override
    public void performAction () {
        // lambda called by level when conditions are met
        Consumer<Game> c = (Game g) -> {
            g.endGame(myStatus);
            ;
        };

    }

}
