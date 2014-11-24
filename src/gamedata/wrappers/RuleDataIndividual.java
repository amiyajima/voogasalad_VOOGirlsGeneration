package gamedata.wrappers;

/**
 * 
 * @author Rica
 *
 */
public class RuleDataIndividual {
    private String CLASSNAME;
    private MoveCountRuleData INSTANCE;

    public RuleDataIndividual(String className, MoveCountRuleData instance) {
            CLASSNAME = className;
            INSTANCE = instance;
            System.out.println("RuleDataIndividual: Constructor called");
    }

    public String getCLASSNAME () {
        return CLASSNAME;
    }

    public MoveCountRuleData getINSTANCE () {
        return INSTANCE;
    }
    
    
}
