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
    private Point2D.Double myLoc;
    private String myImageLocation;
    
    public PatchDataIndividual(String typeID, String name, Point2D.Double p, String imageLocation) {
        System.out.println(typeID);
        myID = typeID;
        System.out.println(name);
        myName = name;
        System.out.println(p.getX() + " " + p.getY());
        myLoc = (Double) p;
        System.out.println(imageLocation);
        myImageLocation = imageLocation;
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
