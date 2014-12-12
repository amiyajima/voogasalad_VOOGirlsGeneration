package gamedata.wrappers;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ConcreteActionData {
    private String myID;
    private List<Point2D.Double> myAttackRange;
    private List<Point2D.Double> myEffectRange;
    private List<StatsTotalLogicData> myStatsLogics;
    private ActionConclusion myConclusion;
    
    public ConcreteActionData(String name, List<Point2D.Double> attackRange, 
                                List<Point2D.Double> effectRange, List<StatsTotalLogicData> statsLogic, 
                                ActionConclusion conclusion) {
        myID = name;
        myAttackRange = attackRange;
        myEffectRange = effectRange;
        myStatsLogics = statsLogic;
        myConclusion = conclusion;
    }
    
    public String getName() {
        return myID;
    }
    
    public List<Point2D.Double> getAttackRange() {
        return myAttackRange;
    }
    
    public List<Point2D.Double> getEffectRange() {
        return myEffectRange;
    }
    
    public List<StatsTotalLogicData> getStatsLogic() {
        return myStatsLogics;
    }
    
    public ActionConclusion getConclusion() {
        return myConclusion;
    }
    
    /**
     * Will use reflection to figure out what type of action it is and create
     * an instance
     * @return
     */
    public Action getActionFromData() {
        // TODO currently these variables assume ConcreteAction and constructs
        // it as such
        List<StatsTotalLogic> myStatsLogicFromData = new ArrayList<StatsTotalLogic>();
        // TODO fix this when StatsModifier inheritance hierarchy is fixed
        /*
        for (StatsTotalLogicData stld : myStatsLogics) {
            myStatsLogicFromData.add(stld.getStatsTotalLogicFromData());
        }
        */
        StatsSingleMultiplier ssm1 = new StatsSingleMultiplier(0, "actor",
                "health");
        List<StatsSingleMultiplier> ssmList = new ArrayList<StatsSingleMultiplier>();
        ssmList.add(ssm1);

        List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
        StatsTotalLogic s1 = new StatsTotalLogic("actor", "health", ssmList);
        stlList.add(s1);
        
        Action myAction = new ConcreteAction(myID, myAttackRange, myEffectRange, 
                                             stlList, myConclusion);
        return myAction;
    }
}
