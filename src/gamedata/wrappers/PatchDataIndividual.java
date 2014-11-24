package gamedata.wrappers;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * Wrapper for an individual patch in gamedata
 * @author annamiyajima
 *
 */
public class PatchDataIndividual {
    private int myTypeID;
    private Point2D.Double myLoc;
    private String myImageLocation;
    
    public PatchDataIndividual(int typeID, String imageLocation, Point2D p) {
        myTypeID = typeID;
        myImageLocation = imageLocation;
        myLoc = (Double) p;
    }

    public int getMyTypeID () {
        return myTypeID;
    }

    public Point2D getMyLoc () {
        return myLoc;
    }

    public String getMyImageLocation () {
        return myImageLocation;
    }

}
