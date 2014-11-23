package gamedata.wrappers;

import gamedata.action.StatsSingleMultiplier;
import java.util.List;

/**
 * Wrapper for StatsTotalLogicData in StatsData in ActionData
 * @author Rica
 *
 */
public class StatsTotalLogicData {
    private String myTarget;
    private String myStat;
    private List<StatsSingleMultiplier> myLogic;
    
    public StatsTotalLogicData(String target, String stat, List<StatsSingleMultiplier> logic) {
        myTarget = target;
        myStat = stat;
        myLogic = logic;
    }

    public String getMyTarget () {
        return myTarget;
    }

    public String getMyStat () {
        return myStat;
    }

    public List<StatsSingleMultiplier> getMyLogic () {
        return myLogic;
    }

}
