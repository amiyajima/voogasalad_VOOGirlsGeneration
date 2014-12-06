package gamedata.events;

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

/**
 * Condition that determines if a piece is at the same location as a patch
 * @author annamiyajima
 *
 */
public class PieceOnPatchCondition extends Condition {
    public static final String description = "IF Piece Is On Particular Patch";
    private Piece myPiece;
    private Patch myPatch;

    /**
     * Constructor
     * 
     * @param pie
     *        Target Piece
     * @param pat
     *        Target Patch
     */
    public PieceOnPatchCondition (Piece pie, Patch pat) {
    	super(description);
        myPiece = pie;
        myPatch = pat;
    }

    @Override
    public boolean evaluate () {
       return(myPiece.getLoc().equals(myPatch.getLoc()));
    }

}
