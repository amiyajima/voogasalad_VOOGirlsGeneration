package gamedata.events;

import gamedata.gamecomponents.Piece;

public class IfStatEquals extends Condition{
	public static final String description = "IF Piece Stat EQUALS Value";
	private Piece reference;
	private String stat;
	private double value;
	
	public IfStatEquals(Piece p, String s, double val){
		reference = p;
		stat = s;
		value = val;
	}

	@Override
	public boolean evaluate() {
		if(reference.getStat(stat) == value){
			return true;
		}
		return false;
	}
	
	
}
