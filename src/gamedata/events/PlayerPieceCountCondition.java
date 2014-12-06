package gamedata.events;

import gameengine.player.Player;


/**
 * Condition that checks if a player has less than a certain number of pieces left
 * 
 * @author annamiyajima
 *
 */
public class PlayerPieceCountCondition extends Condition {
    public static final String description = "IF Num Pieces Remaining Reached";
    private Player myPlayer;
    private int myValue;

    public PlayerPieceCountCondition (Player p, int value) {
    	super(description);
        myPlayer = p;
        myValue = value;
    }

    @Override
    public boolean evaluate () {
        return myPlayer.getPieces().size() <= myValue;
    }

}
