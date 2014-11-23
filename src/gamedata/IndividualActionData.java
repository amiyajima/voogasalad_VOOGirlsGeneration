package gamedata;

import java.awt.geom.Point2D;
import java.util.List;

public class IndividualActionData {
    private String myName;
    private List<Point2D> myAttackRange;
    private List<Point2D> myEffectRange;
    private List<StatsSingleMultiplierData> myStatsLogic;
    
    public IndividualActionData(String name, List<Point2D> attackRange, 
                                List<Point2D> effectRange, List<StatsSingleMultiplierData> statsLogic) {
        myName = name;
        myAttackRange = attackRange;
        myEffectRange = effectRange;
        myStatsLogic = statsLogic;
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
    
    public List<StatsSingleMultiplierData> getActions() {
        return myStatsLogic;
    }
}
