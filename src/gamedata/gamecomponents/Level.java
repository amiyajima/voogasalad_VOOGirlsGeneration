package gamedata.gamecomponents;

import java.util.List;
import gamedata.goals.*;
import gamedata.rules.*;
import gameengine.player.Player;


public class Level {

    private Grid myGrid;
    private List<Goal> myGoals;
    private Rule myEndTurnRule;

    /**
     * Information for grid and goals comes from the JSON
     */
    public Level (Grid grid, List<Goal> goals) {
        myGrid = grid;
        myGoals = goals;
    }

    public void playLevel (Player p) {
        // as long as there are turns left
        while (!myEndTurnRule.isTriggered()) {
            
            //need to increment something that would lead to endturnrule returning true
        }
    }

    /**
     * Checks to see if the level goals have been met.
     * 
     * @return
     */
    public boolean isLevelWon () {
        boolean b = false;
        for (Goal g : myGoals) {
            b = (boolean) g.checkGameState(this);
        }
        return b;
    }

}
