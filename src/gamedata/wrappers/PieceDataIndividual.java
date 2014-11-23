package gamedata.wrappers;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Wrapper for individual pieces in PieceData in GridData
 * @author Rica
 *
 */
public class PieceDataIndividual {
    private int myTypeID;
    private int myUniqueID;
    private int myPlayerID;
    private String myImageLocation;

    private Point2D myLocation;
    private StatsData myStats;
    private List<ActionData> myActions;
    private List<MovementData> myPath;
    private boolean myShouldRemove;
    private InventoryData myInventory;
    
    public PieceDataIndividual (String imageLocation, List<MovementData> m, 
                                List<ActionData> a, StatsData stats, Point2D location, 
                                int tid, int uid, int pid, InventoryData inventory) {
       myImageLocation = imageLocation;
       myPath = m;
       myActions = a;
       myStats = stats;
       myLocation = location;
       myTypeID = tid;
       myUniqueID = uid;
       myPlayerID = pid;
       myShouldRemove = false;
       myInventory = inventory;
   }

    public int getMyTypeID () {
        return myTypeID;
    }

    public int getMyUniqueID () {
        return myUniqueID;
    }

    public int getMyPlayerID () {
        return myPlayerID;
    }

    public String getMyImageLocation () {
        return myImageLocation;
    }

    public Point2D getMyLocation () {
        return myLocation;
    }

    public StatsData getMyStats () {
        return myStats;
    }

    public List<ActionData> getMyActions () {
        return myActions;
    }

    public List<MovementData> getMyPath () {
        return myPath;
    }

    public boolean isMyShouldRemove () {
        return myShouldRemove;
    }

    public InventoryData getMyInventory () {
        return myInventory;
    }

}
