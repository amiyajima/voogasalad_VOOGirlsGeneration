package gamedata.events;

import gamedata.gamecomponents.Piece;
import gameengine.player.Player;


/**
 * Condition that is triggered if a player has no available moves left
 * 
 * // TODO determine way to mark a piece if it has no possible moves
 * 
 * @author annamiyajima
 *
 */
public class NoAvailableMoveCondition implements Condition {

    public static final String description = "IF No Available Moves Remain";
    private Player myPlayer;

    NoAvailableMoveCondition (Player p) {
        myPlayer = p;
    }

    @Override
    public boolean evaluate () {
        for(Piece p: myPlayer.getPieces()){
            if(p.!canMove()){
                return true;
            }           
        }
        return false;
    }
}
