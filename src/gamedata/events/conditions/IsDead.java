package gamedata.events.conditions;

import java.util.ArrayList;
import java.util.List;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;

public class IsDead extends Condition{

	String myHealthName;
	
	public IsDead(String name) {
		super(String.format("SPECIAL CONDITION: Pieces with %s <=0 are dead.", name));
		myHealthName = name;
	}

	@Override
	public boolean evaluate(List<IHasStats> objects) {
		for(IHasStats source : objects){
			if(Piece.class.equals(source.getClass())){
				if(source.getStats().getValue(myHealthName)<=0){
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
