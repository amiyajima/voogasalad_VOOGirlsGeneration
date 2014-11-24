package gamePlayer;

import gamedata.gamecomponents.Piece;

/**
 * The class representing the state of the grid before any piece on
 * the grid is selected
 *
 */
public class SelectState implements IGridState {
    private ViewController myController;
   // private MouseController myMouseController;

    public SelectState (ViewController controller) {
        myController = controller;

       // myMouseController = myController.getMouseController();
        myController.getGrid().setOnMouseEntered(event->{myController.changeCursor(myController.CURSOR_GLOVE_TEST);});
//        myController.getGrid().setOnMouseEntered(event->{myMouseController.setCursorImage(myController.getScene(), myController.getGrid(), myController.CURSOR_GLOVE_TEST);;});    
//        
//        myMouseController.setOnClick(myController, myController.getGridState(), myController.getGrid());
//        
    }
    

    @Override
    public void onClick (Piece piece) {

        myController.updateStats(piece);
        myController.setActivePiece(piece);
        myController.updateActions(piece);
    }

}
