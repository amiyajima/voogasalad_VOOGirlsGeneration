package gamedata.events.conditions;

import java.util.ArrayList;
import java.util.List;

import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;

/**
 * Condition met when a specified stat is < 0 
 * @author Mike Zhu
 *
 */
public class DefineWhenUnitIsDead extends Condition{

	String myHealthName;
	
	public DefineWhenUnitIsDead(String name) {
		super(String.format("SPECIAL CONDITION: Pieces with %s <=0 are dead", name));
		myHealthName = name;
	}

	@Override
	public boolean evaluate(List<IHasStats> objects) {
		for(IHasStats source : objects){
			if(Piece.class.equals(source.getClass())){
				if(source.getStats().contains(myHealthName) && source.getStats().getValue(myHealthName)<=0){
					((Piece) source).markForRemoval();
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
	    return myDescription;
	}
}
