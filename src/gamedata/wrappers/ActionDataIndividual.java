package gamedata.wrappers;

import gamedata.action.ActionConclusion;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Wrapper for individual actions in ActionData
 * @author Rica
 *
 */
public class ActionDataIndividual {
    private String myName;
    private List<Point2D> myAttackRange;
    private List<Point2D> myEffectRange;
    private List<StatsTotalLogicData> myStatsLogic;
    private ActionConclusion myConclusion;
    
    public ActionDataIndividual(String name, List<Point2D> attackRange, 
                                List<Point2D> effectRange, List<StatsTotalLogicData> statsLogic, 
                                ActionConclusion conclusion) {
        myName = name;
        myAttackRange = attackRange;
        myEffectRange = effectRange;
        myStatsLogic = statsLogic;
        myConclusion = conclusion;
    }
    
    public String getName() {
        return myName;
    }
    
    public List<Point2D> getAttackRange() {
        return myAttackRange;
    }
    
    public List<Point2D> getEffectRange() {
        return myEffectRange;
    }
    
    public List<StatsTotalLogicData> getActions() {
        return myStatsLogic;
    }
    
    public ActionConclusion getConclusion() {
        return myConclusion;
    }
}
