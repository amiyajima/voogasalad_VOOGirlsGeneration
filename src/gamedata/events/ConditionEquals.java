package gamedata.events;

import gamedata.gamecomponents.IHasStats;

public abstract class ConditionEquals extends Condition{
	
	protected IHasStats myReference1;
	protected IHasStats myReference2;
	protected String myStat1;
	protected String myStat2;
	
	protected double myValue;
	
	//TODO: There might be an error with passing myDescription up the pipeline
	public ConditionEquals(String description, IHasStats ref1, String stat1, IHasStats ref2, String stat2){
		super(description);
		myReference1 = ref1;
		myReference2 = ref2;
		
		myStat1 = stat1;
		myStat2 = stat2;
	}
	
	public ConditionEquals(String description, IHasStats ref1, String stat1, double val){
		super(description);
		myReference1 = ref1;
		myStat1 = stat1;
		myValue = val;
	}
	
}
