package gamedata.events.conditions;

import java.util.List;

import gamedata.gamecomponents.IHasStats;

/**
 * A subset of Conditions in which a Stat from an all objects of a given IHasStats ckass 
 * (Piece, Patch, or Player) are compared against a constant Double. 
 * 
 * @author Mike Zhu
 *
 */
public abstract class StatComparison extends Condition{
			
	protected String myReference;
	protected String myStat;
	protected Double myConstant;

	public StatComparison(String description, String ref1, String stat1, String constant){
		super(description);
		myReference = ref1;
		myStat = stat1;
		myConstant = Double.parseDouble(constant);
	}
	
	@Override
	public boolean evaluate(List<IHasStats> objects) {
		for(IHasStats source: objects){
			if(myReference.equals(source.getClass().toString())){
				return compare(source);
			}	
		}
		return false;
	}
	
	protected abstract boolean compare(IHasStats source);
}
