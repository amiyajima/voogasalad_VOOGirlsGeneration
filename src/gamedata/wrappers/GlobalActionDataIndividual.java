package gamedata.wrappers;

import java.awt.geom.Point2D;
import gamedata.events.GlobalAction;
import gamedata.events.globalaction.DeletePieceAtLocation;

/**
 * Wrapper for each global action
 * @author Rica
 *
 */
public class GlobalActionDataIndividual {
    private String myDescription;
    
    public GlobalActionDataIndividual(String description) {
        myDescription = description;
    }

    public String getMyDescription () {
        return myDescription;
    }

    /**
     * Unwrapper for global action, uses reflection to choose the appropriate one
     * @return
     */
    public GlobalAction getGlobalActionFromData () {
        // TODO Assumes this is DeletePieceAtLocation for now
        Point2D myPoint = new Point2D.Double(0,0);
        GlobalAction myGlobalActionFromData = new DeletePieceAtLocation(myPoint);
        return myGlobalActionFromData;
    }

}
