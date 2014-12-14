package gamedata.wrappers;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;


/**
 * Wrapper for individual pieces in PieceData in GridData
 *
 */
public class PieceDataIndividual {
    // private ActionData myActions;
    private List<ActionDataIndividual> myActions;
    private StatsData myStats;
    private int myPlayerID;
    private boolean myShouldRemove;

    // from GridComponent
    private String myID;
    private String myName;
    private Point2D.Double myLoc;
    private String myImageLocation;

    public PieceDataIndividual (List<ActionDataIndividual> actions, StatsData stats,
                                int playerID,
                                boolean shouldRemove,
                                String ID,
                                String name,
                                String imageLocation,
                                Point2D p) {
        myActions = actions;
        myStats = stats;
        myPlayerID = playerID;
        myShouldRemove = shouldRemove;
        myID = ID;
        myName = name;
        myImageLocation = imageLocation;
        myLoc = (Double) p;
    }

    public List<ActionDataIndividual> getMyActions () {
        return myActions;
    }

    public StatsData getMyStats () {
        return myStats;
    }

    public int getMyPlayerID () {
        return myPlayerID;
    }

    public boolean isMyShouldRemove () {
        return myShouldRemove;
    }

    public String getMyID () {
        return myID;
    }

    public String getMyName () {
        return myName;
    }

    public Point2D.Double getMyLoc () {
        return myLoc;
    }

    public String getMyImageLocation () {
        return myImageLocation;
    }
    
    public Piece getPieceFromData() {
        List<Action> myActionsFromData = new ArrayList<Action>();
        for (ActionDataIndividual adi : myActions) {
            myActionsFromData.add(adi.getActionFromData());
        }
        Stats myStatsFromData = new Stats(myStats.getStats());
        // TODO Inventory is not implemented yet, so making an empty one
        Piece myPiece = new Piece(myID, myName, myImageLocation, null, myActionsFromData,
                                  myStatsFromData, myLoc, myPlayerID, false, false);
        return myPiece;
    }

    public String toString () {
        return "PieceDataIndividual: ID = " + myID + "myLoc = " + myLoc + " stats = " + myStats;
    }
}
