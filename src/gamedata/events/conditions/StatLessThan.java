package gamedata.events.conditions;

import java.util.List;

import gamedata.events.Condition;
import gamedata.events.StatComparison;
import gamedata.gamecomponents.IHasStats;

public class StatLessThan extends StatComparison{
	
	public static final String description = "LESS THAN";

	public StatLessThan(String description, IHasStats ref1, String stat1, String constant){
		super(description, ref1, stat1, constant);
	
	}

	@Override
	protected boolean comparison(IHasStats source) {
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
