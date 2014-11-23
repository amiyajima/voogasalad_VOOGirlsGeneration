package gamePlayer;

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
        myController.getGrid().getChildren().forEach(node->node.setOnMouseEntered(event->{System.out.println("mouse entered");}));
    }

    @Override
    public void onClick (Piece piece) {
        myController.updateStats(piece);
        myController.setActivePiece(piece);
        myController.updateActions(piece);
    }

}
