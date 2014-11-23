package gamedata.wrappers;

import gamedata.action.StatsSingleMultiplier;
import java.util.List;

/**
 * Wrapper for StatsSingleMultiplerData
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
