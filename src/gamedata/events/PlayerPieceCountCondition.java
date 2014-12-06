package gamedata.events;

import gamedata.events.conditions.Condition;
import gameengine.player.Player;


/**
 * Condition that checks if a player has less than a certain number of pieces left
 * 
 * @author annamiyajima
 *
 */
public class PlayerPieceCountCondition extends Condition {
    public static final String DESCRIPTION = "IF Num Pieces Remaining Reached";
    private Player myPlayer;
    private int myValue;

    public PlayerPieceCountCondition (Player p, int value) {
    	super(IF + "Player " + p.getID() + " has " + value + " Pieces remaining");
        myPlayer = p;
        myValue = value;
    }

    @Override
    public boolean evaluate () {
        return myPlayer.getPieces().size() <= myValue;
    }

}
