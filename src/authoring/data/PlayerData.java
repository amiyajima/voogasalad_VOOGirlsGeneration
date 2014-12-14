package authoring.data;

import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores all the players created in the authoring environment
 * 
 * @author Rica, annamiyajima
 *
 */
public class PlayerData {
    private int myNumPlayers;
    private List<Player> myPlayers;

    public PlayerData (int numPlayers) {
        myPlayers = new ArrayList<>(numPlayers);
    }
    
    public int getNumPlayers(){
        return myNumPlayers;
    }

}
