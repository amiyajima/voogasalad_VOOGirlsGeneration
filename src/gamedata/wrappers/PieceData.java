package gamedata.wrappers;

import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Wrapper for PieceData in GridData
 * @author Rica
 *
 */
public class PieceData {
    private Map<Point2D, Piece> myPieces;

    public PieceData (Map<Point2D, Piece> pieces) {
        myPieces = pieces;
    }

    public Map<Point2D, Piece> getPieces () {
        return myPieces;
    }

}
