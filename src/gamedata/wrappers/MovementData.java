package gamedata.wrappers;

import gameengine.movement.Movement;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Wrapper for movement
 * @author Rica
 *
 */
public class MovementData {
    private List<Point2D.Double> myMoves;
    private List<List<Point2D.Double>> myPaths;
    
    public MovementData(List<Point2D.Double> moves, List<List<Point2D.Double>> paths) {
        myMoves = moves;
        myPaths = paths;
    }
    
    public List<Point2D.Double> getMyMoves () {
        return myMoves;
    }

    public List<List<Point2D.Double>> getMyPaths () {
        return myPaths;
    }

    public Movement getMovementFromData () {
        Movement myMovementFromData = new Movement(myMoves, myPaths);
        return myMovementFromData;
    }

}
