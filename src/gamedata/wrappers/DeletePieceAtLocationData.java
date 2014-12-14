package gamedata.wrappers;

import java.awt.geom.Point2D;


public class DeletePieceAtLocationData {
    private Point2D.Double myLoc;
    private String myDescription;

    public DeletePieceAtLocationData (Point2D loc, String descript) {
        myLoc = (java.awt.geom.Point2D.Double) loc;
        myDescription = descript;
    }

    public String toString () {
        return "DeletePieceAtLocationData: loc = " + myLoc + " myDescription = " + myDescription;
    }
}
