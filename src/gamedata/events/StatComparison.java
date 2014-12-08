package gamedata.events;

import java.util.List;

import gamedata.gamecomponents.IHasStats;

public abstract class StatComparison extends Condition{
			
	protected IHasStats myReference;
	protected String myStat;
	protected Double myConstant;

	public StatComparison(String description, IHasStats ref1, String stat1, String constant){
		super(description);
		myReference = ref1;
		myStat = stat1;
		myConstant = Double.parseDouble(constant);
	}
	
	@Override
	public boolean evaluate(List<IHasStats> objects) {
		for(IHasStats source: objects){
			if(myReference.getClass().equals(source.getClass())){
				return compare(source);
			}	
		}
		return false;
	}
	
	protected abstract boolean compare(IHasStats source);
}
