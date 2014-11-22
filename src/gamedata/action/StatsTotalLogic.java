package gamedata.action;

import java.util.List;

/**
 * Stores the overall logic for one stats modifying equation.
 * Note, a StatsModifyingAction contains a list of StatsTotalLogics.
 * 
 * @author jujs100
 *
 */
public class StatsTotalLogic extends StatsModifier{
	private List<StatsSingleMultiplier> myLogic;
	
	/**
	 * Constructor for StatsTotalLogic
	 * @param target - One of 2 string choices indicating whether the stat to be
	 * affected is that from the actor or the receiver. String choices: [actor, receiver]
	 * @param stat - String name of stat to be modified
	 * @param logic - List of StatsSingleMultipliers to edit the stat
	 */
	public StatsTotalLogic(String target, String stat, List<StatsSingleMultiplier> logic) {
		super(target,stat);
		myLogic = logic;
	}

	/**
	 * Returns the list of multiplier logics 
	 */
	protected List<StatsSingleMultiplier> getMultiplierLogic() {
		return myLogic;
	}
}
