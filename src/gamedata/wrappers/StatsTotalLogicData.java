package gamedata.wrappers;

import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for StatsTotalLogicData in StatsData in ActionData
 * @author Rica
 *
 */
public class StatsTotalLogicData {
    private List<StatsSingleMultiplierData> myLogic;
    private String myTarget;
    private String myStat;
    
    public StatsTotalLogicData(String target, String stat, List<StatsSingleMultiplierData> logic) {
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

    public List<StatsSingleMultiplierData> getMyLogic () {
        return myLogic;
    }

    /**
     * Data unwrapper
     * @return StatsTotalLogic
     */
    public StatsTotalLogic getStatsTotalLogicFromData () {
        List<StatsSingleMultiplier>  myStatsSingleMultiplerFromData = new ArrayList<StatsSingleMultiplier>();
        for (StatsSingleMultiplierData ssmd : myLogic) {
            myStatsSingleMultiplerFromData.add(ssmd.getStatsSingleMultiplierFromData());
        }
        StatsTotalLogic myStatsTotalLogic = new StatsTotalLogic(myTarget, myStat, myStatsSingleMultiplerFromData);
        return myStatsTotalLogic;
    }

}
