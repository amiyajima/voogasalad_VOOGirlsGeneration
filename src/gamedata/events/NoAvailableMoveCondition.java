package gamedata.events;

import gamedata.gamecomponents.Piece;
import gameengine.player.Player;

public class NoAvailableMoveCondition implements Condition{

    public static final String description = "IF No Available Moves Remain";
    private Player myPlayer;
    
    NoAvailableMoveCondition(Player p){
        myPlayer = p;
    }
    
    //TODO determine way to mark a piece if it has no possible moves
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
