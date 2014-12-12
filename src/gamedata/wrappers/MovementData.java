package gamedata.wrappers;

import gameengine.movement.Movement;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for movement
 * @author Rica
 *
 */
public class MovementData {
    private List<Point2D.Double> myMoves;
    private List<List<Point2D.Double>> myPaths;
    
    public MovementData(List<Point2D.Double>... endPoints) {
        boolean first = true;
        myPaths = new ArrayList<List<Point2D.Double>>();
        for (List<Point2D.Double> p : endPoints) {
                if (first) {
                        myMoves = p;
                        first = false;
                } else {
                        myPaths.add(p);
                }
        }
    }
    
    public Movement getMovementFromData () {
        // TODO this may not work, but generic initialization doesn't work either
        List<Point2D.Double>[] myPathsToData = new List<Point2D.Double>[]{myMoves};
        for (int i = 0; i < myPathsToData.length; i++) {
            myPathsToData[i] = new ArrayList<Point2D.Double>();
        }
        Movement myMovementFromData = new Movement(myPathsToData);
        return myMovementFromData;
    }

}
