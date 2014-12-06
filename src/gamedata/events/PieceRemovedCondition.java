package gamedata.events;

import gamedata.gamecomponents.Piece;
import java.util.List;


/**
 * Condition that checks if specified pieces no longer are in play
 * 
 * @author annamiyajima
 *
 *         TODO: guarantee that previously removed pieces are no longer in the list
 */
public class PieceRemovedCondition implements Condition {
    public static final String description = "IF Piece with specified name";
    private List<Piece> myPieces;

    PieceRemovedCondition (List<Piece> pieces) {
        myPieces = pieces;
    }

    @Override
    public boolean evaluate () {
        for (Piece p : myPieces) {
            if (p.shouldRemove())
                return true;
        }
        return false;
    }

}
