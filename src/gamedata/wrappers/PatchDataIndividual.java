package gamedata.wrappers;

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
    
    public PatchDataIndividual(String typeID, String name,String imageLocation, Point2D p) {
        myID = typeID;
        myName = name;
        myImageLocation = imageLocation;
        myLoc = (Double) p;
    }

    public Point2D getMyLoc () {
        return myLoc;
    }

    public String getMyImageLocation () {
        return myImageLocation;
    }

}
