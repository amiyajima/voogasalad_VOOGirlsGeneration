package gamedata.events.globalactions;

import java.util.function.Consumer;
import gamedata.gamecomponents.Piece;
import authoring_environment.GUIGrid;


public class DeletePieceGlobalAction implements GlobalAction {

    private Piece myDeletePiece;

    public DeletePieceGlobalAction (Piece p) {
        myDeletePiece = p;
    }

    @Override
    public void performAction () {
        // lambda called by level when conditions are met
        Consumer<GUIGrid> c = (GUIGrid g) -> {
            g.removePiece(myDeletePiece);
        };
    }

}
