package gamedata;

import gamedata.stats.Stats;
import java.util.List;

public class StatsData {
    private List<Stats> myStats;
    
    public StatsData(List<Stats> stats){
        myStats = stats;
    }
    
    public List<Stats> getStats() {
        return myStats;
    }
}
