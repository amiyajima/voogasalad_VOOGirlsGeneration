package gamedata.wrappers;

import gamedata.goals.Goal;
import java.util.List;

/**
 * Wrapper for goals
 * @author Rica
 *
 */
public class GoalData {
    private List<Goal> myGoals;
    
    public GoalData(List<Goal> goals) {
        myGoals = goals;
    }
    
    public List<Goal> getGoals() {
        return myGoals;
    }

}
