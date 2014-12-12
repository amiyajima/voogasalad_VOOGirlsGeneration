package gamedata.wrappers;

import java.util.Map;

/**
 * Wrapper for StatsData in ActionData
 * @author Rica
 *
 */
public class StatsData {
    private Map<String, Double> myStats;
    
    public StatsData(Map<String, Double> stats){
        myStats = stats;
    }
    
    public Map<String, Double> getStats() {
        return myStats;
    }
    
    public String toString(){
        return myStats.toString();
    }
}
