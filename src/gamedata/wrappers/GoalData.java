package gamedata.wrappers;

import gamedata.goals.Goal;
import gamedata.goals.PlayerPiecesRemovedGoal;
import java.util.List;


/**
 * Wrapper for goals in GridData
 * 
 * @author Rica
 *
 */
// TODO generalize from PlayerPiecesRemovedGoal back to superclass Goal
public class GoalData {
    private List<PlayerPiecesRemovedGoalData> myGoals;

    public GoalData (List<PlayerPiecesRemovedGoalData> goals) {
        myGoals = goals;
    }

    public List<PlayerPiecesRemovedGoalData> getGoals () {
        return myGoals;
    }

    public String toString () {
        return "GoalData: tostring called. contains " + myGoals;
    }
}
