package gamedata.events.conditions;

import gamedata.gamecomponents.GameState;
import gamedata.gamecomponents.IHasStats;
import java.util.List;

public class MovesReachedEquals extends Condition{

	protected Double myConstant;
	
	public MovesReachedEquals(String ref1, String stat1, String constant){
		super(String.format("IF Player Moves in one turn equals %s", constant));
		myConstant = Double.parseDouble(constant);
	}

	@Override
	public boolean evaluate(List<IHasStats> objects) {
		if(GameState.getMoves()==myConstant){
			GameState.resetMoves();
		}
		return GameState.getMoves()==myConstant;
	}

}
