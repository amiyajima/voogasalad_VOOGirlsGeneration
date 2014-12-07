package gamedata.events.conditions;

import gamedata.events.Condition;
import gamedata.gamecomponents.IHasStats;

public class ConditionEquals extends Condition{
	
	protected IHasStats myReference1;
	protected IHasStats myReference2;
	protected String myStat1;
	protected String myStat2;
	
	protected double myVal1;
	protected double myVal2;
	
	//TODO: There might be an error with passing myDescription up the pipeline
	public ConditionEquals(String description, IHasStats ref1, String stat1, IHasStats ref2, String stat2){
		super(description);
		myReference1 = ref1;
		myReference2 = ref2;
		
		myStat1 = stat1;
		myStat2 = stat2;
	}

	@Override
	public boolean evaluate() {
		return myReference1.getStats().getValue(myStat1) == myReference2.getStats().getValue(myStat2);
	}
	
	/**
	 * For testing purposes
	 */
	public String printOut(){
		String print = "IF " + myReference1 + ": " + myStat1 + " EQUALS " + myReference2 + ": " + myStat2;
		return print;
	}

}
