package authoring.data;

import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.util.List;

/**
 * Stores all the players created in the authoring environment
 * @author Rica
 *
 */
public class PlayerData implements AuthoringData<Player> {
    private List<Player> myPlayers;
    
    public PlayerData(List<Player> players) {
        myPlayers = players;
    }
    
    @Override
    public void add(Player...players) {
        for (Player p: players) {
            myPlayers.add(p);
        }
    }
    
    @Override
    public void remove (Player...players) {
        for (Player p: players) {
            myPlayers.remove(p);
        }
    }

    @Override
    public void clear () {
        myPlayers.clear();
    }

    @Override
    public List<Player> get () {
        return myPlayers;
    }

	@Override
	public void add(Player element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canAdd(Player element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(Player element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(Player origEl, Player newEl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
