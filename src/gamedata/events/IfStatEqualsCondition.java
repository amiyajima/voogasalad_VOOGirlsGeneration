package gamedata.events;

import gamedata.gamecomponents.Piece;

public class IfStatEqualsCondition extends Condition{
	public static final String description = "IF Piece Stat EQUALS Value";
	private Piece myReference;
	private String myStat;
	private double myValue;
	
	public IfStatEqualsCondition(Piece p, String s, double val){
		super(description);
		myReference = p;
		myStat = s;
		myValue = val;
	}

	@Override
	public boolean evaluate() {
		return myReference.getStats().getValue(myStat) == myValue;
	}	
}