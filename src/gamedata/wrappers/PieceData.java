package gamedata.wrappers;

import gamedata.gamecomponents.Piece;
import java.util.List;

/**
 * Piece wrapper
 * @author Rica
 *
 */
public class PieceData {
    private List<Piece> myPieces;
    
    public PieceData(List<Piece> pieces) {
        myPieces = pieces;
    }
    
    public List<Piece> getPieces() {
        return myPieces;
    }
}
