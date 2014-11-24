package gamedata.wrappers;

import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import java.util.List;


/**
 * Rule wrapper for Rules in GridData
 * 
 * @author Rica
 *
 */
//TODO change movecountruledata to generic individual rule data  
public class RuleData {
    private List<MoveCountRuleData> myRules;
    
    //private List<RuleDataIndividual> myRules;
    
    public RuleData(List<MoveCountRuleData> rules) {
        myRules = rules;
        System.out.println("ruleData: Constructor called");
    }
    
    public List<MoveCountRuleData> getRules() {
        return myRules;
    }

    public String toString () {
        return "toString called for " + this.getClass() + myRules;
    }
}
