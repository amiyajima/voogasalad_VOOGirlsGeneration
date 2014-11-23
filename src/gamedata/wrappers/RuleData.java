package gamedata.wrappers;

import gamedata.rules.Rule;
import java.util.List;

/**
 * Rule wrapper for Rules in GridData
 * @author Rica
 *
 */
public class RuleData {
    private List<RuleDataIndividual> myRules;
    
    public RuleData(List<RuleDataIndividual> rules) {
        myRules = rules;
    }
    
    public List<RuleDataIndividual> getRules() {
        return myRules;
    }
}
