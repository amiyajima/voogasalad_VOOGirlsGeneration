package gamedata.wrappers;

import gameengine.player.Player;
import java.util.List;

/**
 * Wrapper for Player data in Players for JSON parsing
 * @author Rica
 *
 */
public class PlayerData {
    private List<PlayerDataIndividual> myPlayers;
    
    public PlayerData(List<PlayerDataIndividual> players) {
        myPlayers = players;
    }
    
    public List<PlayerDataIndividual> getPlayers() {
        return myPlayers;
    }
    
    public String toString(){
        return myPlayers.toString();
    }

}