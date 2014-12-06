package gamedata.events.conditions;

import gamedata.gamecomponents.IHasStats;

public class IfStatEqualsStat extends ConditionEquals{
	
	public IfStatEqualsStat(IHasStats ref1, String stat1, IHasStats ref2, String stat2) {
		super(IF + ref1.getName() + " " + stat1 + EQUALS + ref2.getName() + " " + stat2, 
				ref1, stat1, ref2, stat2);
	}

	@Override
	public boolean evaluate() {
		return myReference1.getStats().getValue(myStat1) == myReference2.getStats().getValue(myStat2);
	}
}
