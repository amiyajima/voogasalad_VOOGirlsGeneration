package gamedata.wrappers;

import gamedata.rules.Rule;
import java.util.List;

/**
 * Rule wrapper for Rules in GridData
 * @author Rica
 *
 */
public class RuleData {
    //private List<RuleDataIndividual> myRules;
    private List<Rule> myRules;
    
    public RuleData(List<Rule> rules) {
        myRules = rules;
    }
    
    public List<Rule> getRules() {
        return myRules;
    }
}
