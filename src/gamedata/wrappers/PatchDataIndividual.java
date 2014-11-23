package gamedata.wrappers;

import java.awt.geom.Point2D;

public class PatchDataIndividual {
    private int myTypeID;
    private Point2D myLoc;
    private String myImageLocation;
    
    public PatchDataIndividual(int typeID, String imageLocation, Point2D p) {
        myTypeID = typeID;
        myImageLocation = imageLocation;
        myLoc = p;
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
