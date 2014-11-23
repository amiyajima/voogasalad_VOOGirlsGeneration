package gamedata.wrappers;

import gameengine.player.Player;
import java.util.List;

/**
 * Wrapper for Player data in Players for JSON parsing
 * @author Rica
 *
 */
public class PlayerData {
    private List<Player> myPlayers;
    
    public PlayerData(List<Player> players) {
        myPlayers = players;
    }
    
    public List<Player> getPlayers() {
        return myPlayers;
    }

}