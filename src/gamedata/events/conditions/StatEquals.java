package gamedata.events.conditions;

import gamedata.events.StatComparison;
import gamedata.gamecomponents.IHasStats;

public class StatEquals extends StatComparison{
	
	public StatEquals(IHasStats ref1, String stat1, String constant){
		super(String.format("IF %s %s = %s", ref1, stat1, constant), ref1, stat1, constant);
	}

	/**
	 * For testing purposes
	 */
	public String printOut(){
		String print = "IF " + myReference + ": " + myStat + " EQUALS " + myConstant;
		return print;
	}

	@Override
	protected boolean compare(IHasStats source) {
		return myConstant.equals(source.getStats().getValue(myStat));
	}

}
