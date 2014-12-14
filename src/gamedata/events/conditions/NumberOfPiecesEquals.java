package gamedata.events.conditions;

import gamedata.events.Condition;
import gamedata.gamecomponents.IHasStats;

import java.util.List;

/**
 * Condition that fires when the number of a given Piece equals the entered constant.
 * @author Mike Zhu
 *
 */
public class NumberOfPiecesEquals extends Condition{

	protected IHasStats myReference;
	protected Double myConstant;
	
	public NumberOfPiecesEquals(IHasStats ref1, String stat1, String constant){
		super(String.format("IF number of Piece: %s on the grid equals %s", ref1.toString(), constant));
		myReference = ref1;
		myConstant = Double.parseDouble(constant);
	}

	@Override
	public boolean evaluate(List<IHasStats> objects) {
		int count = 0;
		for(IHasStats source: objects){
			if(myReference.getClass().equals(source.getClass())){
				count++;
			}	
		}
		return myConstant==count;
	}
}
