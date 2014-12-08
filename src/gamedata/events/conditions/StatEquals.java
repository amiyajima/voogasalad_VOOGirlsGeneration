package gamedata.events.conditions;

import gamedata.events.StatComparison;
import gamedata.gamecomponents.IHasStats;

public class StatEquals extends StatComparison{

	public static final String description = "EQUALS";
	
	public StatEquals(String description, IHasStats ref1, String stat1, String constant){
		super(description, ref1, stat1, constant);
	}

	/**
	 * For testing purposes
	 */
	public String printOut(){
		String print = "IF " + myReference + ": " + myStat + " EQUALS " + myConstant;
		return print;
	}

	@Override
	protected boolean comparison(IHasStats source) {
		return myConstant.equals(source.getStats().getValue(myStat));
	}

}
