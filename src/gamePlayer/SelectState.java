package gamePlayer;

import gamedata.gamecomponents.Piece;

/**
 * The class representing the state of the grid before any piece on
 * the grid is selected
 *
 */
public class SelectState implements IGridState {
    private ViewController myController;
    //private MouseController myMouseController;

    public SelectState (ViewController controller) {
        System.out.println("At Select State");
        myController = controller;
        myController.getGridPane().setOnMouseEntered(event->{myController.changeCursor(myController.CURSOR_GLOVE_TEST);;});    
    
    }
    

    @Override
    public void onClick (Piece piece) {
        myController.updateStats(piece);
        myController.setActivePiece(piece);
        myController.updateActions(piece);
    }
}
