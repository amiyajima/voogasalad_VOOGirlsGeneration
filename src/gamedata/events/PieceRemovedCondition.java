package gamedata.events;

import gamedata.events.conditions.Condition;
import gamedata.gamecomponents.Piece;

import java.util.List;


/**
 * Condition that checks if specified pieces no longer are in play
 * 
 * @author annamiyajima
 *
 *         TODO: guarantee that previously removed pieces are no longer in the list
 */
public class PieceRemovedCondition extends Condition {
    public static final String DESCRIPTION = "IF Piece with specified name removed";
    private List<Piece> myPieces;

    public PieceRemovedCondition (List<Piece> pieces) {
        super(DESCRIPTION);
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
