package gamedata.action;

import java.util.LinkedList;
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
	
	public StatsTotalLogic() {
		super ("","");
		myLogic = new LinkedList<StatsSingleMultiplier>();
	}
	
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
	 * 
	 * made public for GlobalStatLogicBox
	 */
	public List<StatsSingleMultiplier> getMultiplierLogic() {
		return myLogic;
	}
	
	@Override
	public String toString() {
	    String myString = "StatsTotalLogic - My Logics: StatRef: " + myStatRef + " Stat: " + myStat + "\n";
	    for (StatsSingleMultiplier ssm : myLogic) {
	        myString += ssm.toString() + " // ";
	    }
	    return myString;
	}
}
