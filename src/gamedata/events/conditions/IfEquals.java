package gamedata.events.conditions;

import gamedata.events.ConditionEquals;
import gamedata.gamecomponents.IHasStats;

public class IfEquals extends ConditionEquals{
	
	public IfEquals(String name, IHasStats ref1, String stat1, IHasStats ref2, String stat2) {
		super(name, ref1, stat1, ref2, stat2);
	}

	@Override
	public boolean evaluate() {
		return myReference1.getStats().getValue(myStat1) == myReference2.getStats().getValue(myStat2);
	}
}
