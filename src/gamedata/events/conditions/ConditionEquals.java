package gamedata.events.conditions;

import java.util.List;

import gamedata.events.Condition;
import gamedata.gamecomponents.IHasStats;

public class ConditionEquals extends Condition{
	
	protected IHasStats myReference;
	protected String myStat;
	protected Double myConstant;

	//TODO: There might be an error with passing myDescription up the pipeline
	public ConditionEquals(String description, IHasStats ref1, String stat1, Double constant){
		super(description);
		myReference = ref1;
		myStat = stat1;
		myConstant = constant;
	}

	@Override
	public boolean evaluate(List<IHasStats> objects) {
		for(IHasStats source: objects){
			if(myReference.getClass().equals(source.getClass())){
				return myConstant.equals(source.getStats().getValue(myStat));
			}	
		}
		return false;
	}
	
	/**
	 * For testing purposes
	 */
	public String printOut(){
		String print = "IF " + myReference + ": " + myStat + " EQUALS " + myConstant;
		return print;
	}

}
