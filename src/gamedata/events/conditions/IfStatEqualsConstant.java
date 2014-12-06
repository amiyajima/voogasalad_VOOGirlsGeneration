package gamedata.events.conditions;

import gamedata.gamecomponents.IHasStats;

public class IfStatEqualsConstant extends ConditionEquals{
	
	public IfStatEqualsConstant(IHasStats ref, String stat, double val){
		super(IF + ref + " " + stat + EQUALS + " " + 2,
				ref, stat, val);
	}

	@Override
	public boolean evaluate() {
		return myReference1.getStats().getValue(myStat1) == myValue;
	}	
}