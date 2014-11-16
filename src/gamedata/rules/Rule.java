package gamedata.rules;

/**
 * A rule that defines when a user's turn is over.
 *
 *ie. player has no more turns left
 *ie. player has no available moves left
 */
public abstract class Rule {
    /**
     * Returns true if end turn conditions are met.
     * Otherwise returns false.
     * @return
     */
    public abstract boolean conditionsMet (int x);

}
