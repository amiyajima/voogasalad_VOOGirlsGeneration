package gamedata;

import gameengine.player.Player;
import java.util.List;

public class PlayerData {
    private List<Player> myPlayers;
    
    public PlayerData(List<Player> players) {
        myPlayers = players;
    }
    
    public List<Player> getPlayers() {
        return myPlayers;
    }

}