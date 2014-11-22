package gamedata.rules;

/**
 * A rule that checks if a player has more than the minimum requirement
 * 
 */
public class AvailableMovesRule extends Rule {
    
    private int myMinNumUnit;

    /**
     * Constructor
     * @param numUnits The minimum number of Units that each player 
     */
    public AvailableMovesRule (int numUnits) {
        myMinNumUnit = numUnits;
    }

    @Override
    public boolean conditionsMet (int currNumUnits) {
        return (currNumUnits > myMinNumUnit);
    }

}
