package gamedata.wrappers;

import gamedata.gamecomponents.IHasStats;
import java.awt.geom.Point2D;

//TODO: test by adding this condition to the bob tester
public class LocationEqualsConditionData {
    private IHasStats myReference;
    private Point2D.Double myConstant;
    private String myDescription;
    
    public LocationEqualsConditionData(IHasStats ref, Point2D constant, String descript){
        myReference = ref;
        myConstant = (java.awt.geom.Point2D.Double) constant;
        myDescription = descript;
    }
    
    public String toString(){
        return "LocationEqualsConditionData: ref = " + myReference + " myconst = " +  myConstant;
    }
}
