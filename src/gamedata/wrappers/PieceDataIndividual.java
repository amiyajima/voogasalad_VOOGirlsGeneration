package gamedata.wrappers;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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

    public int getMyPlayerID () {
        return myPlayerID;
    }

    public boolean isMyShouldRemove () {
        return myShouldRemove;
    }

    public String toString () {
        return "PieceDataIndividual: ID = " + myID + "myLoc = " + myLoc + " stats = " + myStats;
    }

    public String getMyTypeID () {
        return myID;
    }

}
