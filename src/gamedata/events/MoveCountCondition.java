package gamedata.events;

import gameengine.player.Player;


/**
 * Condition associated with a designated player reaching a designated play count
 * 
 * @author annamiyajima
 *
 */
public class MoveCountCondition extends Condition {
    public static final String DESCRIPTION = "IF Move Count Reached";
    private int myValue;
    private Player myPlayer;

    public MoveCountCondition (Player p, int value) {
    	super(DESCRIPTION);
        myPlayer = p;
        myValue = value;
    }

    @Override
    public boolean evaluate () {
        return myPlayer.getNumMovesPlayed() == myValue;
    }

}
