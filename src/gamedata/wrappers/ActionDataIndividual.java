package gamedata.wrappers;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsTotalLogic;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for individual actions in ActionData
 * @author Rica
 *
 */
public class ActionDataIndividual {
    private String myID;
    private List<Point2D> myAttackRange;
    private List<Point2D> myEffectRange;
    private List<StatsTotalLogicData> myStatsLogic;
    private ActionConclusion myConclusion;
    
    public ActionDataIndividual(String name, List<Point2D> attackRange, 
                                List<Point2D> effectRange, List<StatsTotalLogicData> statsLogic, 
                                ActionConclusion conclusion) {
        myID = name;
        myAttackRange = attackRange;
        myEffectRange = effectRange;
        myStatsLogic = statsLogic;
        myConclusion = conclusion;
    }
    
    public String getName() {
        return myID;
    }
    
    public List<Point2D> getAttackRange() {
        return myAttackRange;
    }
    
    public List<Point2D> getEffectRange() {
        return myEffectRange;
    }
    
    public List<StatsTotalLogicData> getStatsLogic() {
        return myStatsLogic;
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
        for (StatsTotalLogicData stld : myStatsLogic) {
            myStatsLogicFromData.add(stld.getStatsTotalLogicFromData());
        }
        Action myAction = new ConcreteAction(myID, myAttackRange, myEffectRange, 
                                     myStatsLogicFromData, myConclusion);
        return myAction;
    }
}
