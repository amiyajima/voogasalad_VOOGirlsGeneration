package gamedata.wrappers;

import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import java.util.List;


/**
 * Rule wrapper
 * 
 * @author Rica
 *
 */
public class RuleData {
    private List<MoveCountRule> myRules;

    public RuleData (List<MoveCountRule> rules) {
        myRules = rules;
    }

    public List<MoveCountRule> getRules () {
        return myRules;
    }

    public String toString () {
        return "toString called for " + this.getClass() + myRules;
    }
}
