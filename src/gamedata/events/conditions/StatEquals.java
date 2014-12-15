// This entire file is part of my masterpiece
// MIKE ZHU

package gamedata.events.conditions;

import gamedata.gamecomponents.IHasStats;

public class StatEquals extends StatComparison{
	
	public StatEquals(String ref1, String stat1, String constant){
		super(String.format("IF %s %s = %s", ref1, stat1, constant), ref1, stat1, constant);
	}

	@Override
	protected boolean compare(IHasStats source) {
		return myConstant.equals(source.getStats().getValue(myStat));
	}

}
