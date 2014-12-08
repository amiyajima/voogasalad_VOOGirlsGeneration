package gamedata.wrappers;

import gameengine.movement.Orientator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import authoring_environment.GUIGrid;


/**
 * DEPRICATED
 * Wrapper for MovementData in PathData
 * known as myPath in JSON
 * 
 * @author Rica, Anna
 *
 */
public class MovementData {
    private List<Point2D> myMoves;
    private List<List<Point2D>> myPaths;
    private List<Point2D> myAbsoluteMoves;
    private Orientator myOrientator;
    private double myOrientation;

    public MovementData (List<Point2D> moves,
                     List<Point2D> absoluteMoves,
                     List<List<Point2D>> paths,
                     Orientator orientator,
                     double orientation) {
        myMoves = moves;
        myAbsoluteMoves = absoluteMoves;
        myPaths = paths;
        myOrientator = orientator;
        myOrientation = orientation;
    }

    public List<Point2D> getMyMoves () {
        return myMoves;
    }

    public List<List<Point2D>> getMyPaths () {
        return myPaths;
    }

}
