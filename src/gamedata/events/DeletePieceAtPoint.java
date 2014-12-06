package gamedata.events;

import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;


/**
 * GlobalAction that deletes pieces on a designated Patch
 * 
 * @author annamiyajima
 *
 */
public class DeletePieceAtPoint implements GlobalAction {

    private Point2D myCoords;

    DeletePieceAtPoint (Point2D coords) {
        myCoords = coords;
    }

    @Override
    public void doBehavior () {
        // TODO Auto-generated method stub

    }

}
