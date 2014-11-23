package gamedata.wrappers;

import java.util.List;
import gamedata.gamecomponents.Grid;
import gamedata.goals.Goal;
import gamedata.rules.Rule;

/**
 * Individual level data wrapper
 * @author Rica
 *
 */
public class LevelDataIndividual {
    private GridData myGrid;
    private List<GoalData> myGoals;
    private List<RuleData> myRules;
    
    public LevelDataIndividual(GridData grid, List<GoalData> goals, List<RuleData> rules) {
        myGrid = grid;
        myGoals = goals;
        myRules = rules;
    }
    
    public GridData getGrid() { 
        return myGrid;
    }
    
    public List<GoalData> getGoals() {
        return myGoals;
    }
    
    public List<RuleData> getRules() {
        return myRules;
    }

}
