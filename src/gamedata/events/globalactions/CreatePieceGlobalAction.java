package gamedata.events.globalactions;

import java.awt.geom.Point2D;
import java.util.function.Consumer;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;
import authoring_environment.GUIGrid;


/**
 * Adds a piece of a specified type at a specified location
 * 
 * @author annamiyajima
 *
 */
public class CreatePieceGlobalAction implements GlobalAction {

    private Piece myTemplate;
    private Point2D myLoc;

    public CreatePieceGlobalAction (Piece template, Point2D loc) {
        myTemplate = template;
    }

    @Override
    public void performAction () {
        // lambda called by level when conditions are met
        Consumer<GUIGrid> c = (GUIGrid g) -> {
            g.addPiece(myTemplate, myLoc);
            ;
        };
    }

}
