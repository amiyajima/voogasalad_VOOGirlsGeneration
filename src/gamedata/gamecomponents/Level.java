package gamedata.gamecomponents;

import java.util.List;
import gamedata.goals.*;
import gamedata.rules.*;


public class Level {

    // goals AKA win/lose conditions for the level
    private Grid myGrid;
    private List<Goal> myGoals;

    public Level () {

    }

    /**
     * Checks to see if the level goals have been met.
     * 
     * @return
     */
    public boolean isLevelWon () {
        boolean b = false;
        for (Goal g : myGoals) {
            b = g.checkGameState(this);
        }
        return b;
    }

}
