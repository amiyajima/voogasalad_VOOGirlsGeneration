package gamedata;

import gamedata.action.StatsSingleMultiplier;
import java.util.List;

/**
 * 
 * @author Rica
 *
 */
public class StatsSingleMultiplierData {
    List<StatsSingleMultiplier> myStats;
    
    public StatsSingleMultiplierData(List<StatsSingleMultiplier> stats) {
        myStats = stats;
    }
    
    public List<StatsSingleMultiplier> getStats() {
        return myStats;
    }

}
