package gamedata.wrappers;

import gamedata.action.StatsSingleMultiplier;
import java.util.List;

/**
 * Wrapper for StatsSingleMultiplerData
 * @author Rica
 *
 */
public class StatsSingleMultiplierData {
    private double myModifier;
    private String myTarget;
    private String myStat;
    
    public StatsSingleMultiplierData(double modifier, String target, String stat) {
        myModifier = modifier;
        myTarget = target;
        myStat = stat;
    }


    public double getMyModifier () {
        return myModifier;
    }


    public String getMyTarget () {
        return myTarget;
    }


    public String getMyStat () {
        return myStat;
    }


    /**
     * Unwrapper
     * @return
     */
    public StatsSingleMultiplier getStatsSingleMultiplierFromData () {
        StatsSingleMultiplier mySSMD = new StatsSingleMultiplier(myModifier, myTarget, myStat);
        return mySSMD;
    }

}
