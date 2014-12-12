package gamedata.events.conditions;

import gamedata.events.StatComparison;
import gamedata.gamecomponents.IHasStats;

public class StatLessThan extends StatComparison{

	public StatLessThan(IHasStats ref1, String stat1, String constant){
		super(String.format("IF %s %s < %s", ref1, stat1, constant), ref1, stat1, constant);
	}

	@Override
	protected boolean compare(IHasStats source) {
		return myConstant > (source.getStats().getValue(myStat));
	}
	
	/**
	 * For testing purposes
	 */
	public String printOut(){
		String print = "IF " + myReference + ": " + myStat + " LESS THAN " + myConstant;
		return print;
	}

}
