package gamePlayer;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import gamedata.action.Action;
import gamedata.gamecomponents.Piece;

/**
 * The class representing the state of the grid before any piece on
 * the grid is selected
 *
 */
public class SelectState implements IGridState {
    private ViewController myController;

    public SelectState (ViewController controller) {
        myController = controller;

        myController.getGrid().setOnMouseEntered(event->{myController.changeCursor(myController.CURSOR_GLOVE_TEST);});

        
    }
    

    @Override
    public void onClick (Piece piece) {

        myController.updateStats(piece);
        myController.setActivePiece(piece);
        myController.updateActions(piece);
    }
}
