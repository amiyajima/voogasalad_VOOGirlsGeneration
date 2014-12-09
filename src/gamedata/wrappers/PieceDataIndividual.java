package gamedata.wrappers;

import gamedata.action.Action;
import gamedata.gamecomponents.Inventory;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.List;


/**
 * Wrapper for individual pieces in PieceData in GridData
 *
 */
public class PieceDataIndividual {
    private ActionData myActions;
    //private Movement myMove;
    private int myPlayerID;
    private boolean myShouldRemove;
    
    //from GridComponent
    private String myID;
    private String myName;
    private Point2D.Double myLoc;
    private String myImageLocation;

    public PieceDataIndividual (ActionData actions, int playerID, boolean shouldRemove,String ID, String name, String imageLocation, Point2D p) {
        myActions = actions;
        // myMove = move;
        myPlayerID = playerID;
        myShouldRemove = shouldRemove;
        myID=ID;
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
    
    public String toString(){
        return "PieceDataIndividual: ID = " + myID + "myLoc = " + myLoc;
    }

}
