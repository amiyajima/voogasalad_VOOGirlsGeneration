package authoring.data;

import gamedata.stats.Stats;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatData {
	private Stats myStats;
	private String myScoreStat;
	
	public PlayerStatData() {
		myStats = new Stats();
		myScoreStat = "";
	}
	
	public Stats getStats() {
		return myStats;
	}
	
	public String getScoreStat() {
		return myScoreStat;
	}
	
	public void setScoreStat(String stat) {
		myScoreStat = stat;
	}
	
	public List<Player> makePlayers(int numPlayers) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			Stats statsCopy = new Stats(myStats);
			players.add(new HumanPlayer(i,statsCopy,myScoreStat));
		}
		return players;
	}
}
