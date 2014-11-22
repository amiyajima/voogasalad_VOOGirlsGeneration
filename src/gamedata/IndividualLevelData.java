package gamedata;

import java.util.List;
import gamedata.gamecomponents.Grid;
import gamedata.goals.Goal;
import gamedata.rules.Rule;

public class IndividualLevelData {
    private Grid myGrid;
    private List<Goal> myGoals;
    private List<Rule> myRules;
    
    public IndividualLevelData(Grid grid, List<Goal> goals, List<Rule> rules) {
        myGrid = grid;
        myGoals = goals;
        myRules = rules;
    }
    
    public Grid getGrid() { 
        return myGrid;
    }
    
    public List<Goal> getGoals() {
        return myGoals;
    }
    
    public List<Rule> getRules() {
        return myRules;
    }

}
