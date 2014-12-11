package gamedata.wrappers;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * Wrapper for an individual patch in gamedata
 * @author annamiyajima
 *
 */
public class PatchDataIndividual {
    private String myID; 
    private String myName;
    private Point2D myLoc;
    private String myImageLocation;
    
    public PatchDataIndividual(String typeID, String name,String imageLocation, Point2D p) {
        myID = typeID;
        myName = name;
        myImageLocation = imageLocation;
        myLoc = (Double) p;
    }
    
    public String getName() {
        return myName;
    }

    public Point2D getMyLoc () {
        return myLoc;
    }

    public String getMyImageLocation () {
        return myImageLocation;
    }

    public String getMyTypeID () {
        return myID;
    }
    
    public String toString() {
        return "Patch - ID: " + myID + "Name: " + myName + " Location: " + myLoc.getX() + ", "
                + myLoc.getY() + " myImage: " + myImageLocation;
    }

    /**
     * Patch unwrapper
     * @return
     */
    public Patch getPatchFromData () {
        Patch myPatchFromData = new Patch(myID, myName, myImageLocation, myLoc);
        return myPatchFromData;
    }

}
