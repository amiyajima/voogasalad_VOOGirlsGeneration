package gamedata.wrappers;

import java.util.Map;

public class StatsData {
    private Map<String, Double> myStats;
    
    public StatsData(Map<String, Double> stats){
        myStats = stats;
    }
    
    public Map<String, Double> getStats() {
        return myStats;
    }
}
