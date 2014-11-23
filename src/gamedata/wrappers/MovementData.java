package gamedata.wrappers;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for MovementData in PathData
 * known as myPath in JSON
 * @author Rica
 *
 */
public class MovementData {
    private List<Point2D> myMoves;
    private List<List<Point2D>> myPaths;
    private List<RuleData> myRules;

    public MovementData(List<Point2D>... endPoints) {
        boolean first = true;
        myPaths = new ArrayList<List<Point2D>>();
        myRules = new ArrayList<RuleData>();
        for (List<Point2D> p : endPoints) {
            if(first){
                myMoves = p;
                first = false;
            }
            else {
                myPaths.add(p);
            }
        }
    }

    public List<Point2D> getMyMoves () {
        return myMoves;
    }

    public List<List<Point2D>> getMyPaths () {
        return myPaths;
    }

    public List<RuleData> getMyRules () {
        return myRules;
    }

}
