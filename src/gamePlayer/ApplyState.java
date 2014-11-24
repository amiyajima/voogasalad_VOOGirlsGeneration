package gamePlayer;

import gamedata.gamecomponents.Piece;

/**
 * Class representing the state of the grid when a particular action 
 * has been selected and is ready to be applied on the grid
 *
 */
public class ApplyState implements IGridState{

    private ViewController myController;
    private MouseController myMouseController;
    
    public ApplyState(ViewController controller){
        System.out.println("new ApplyState");
        myController = controller;
        myMouseController = myController.getMouseController();
        
//        myController.getGrid().setOnMouseEntered(event->{myController.changeCursor(myController.CURSOR_ATTACK_TEST);});
      myController.getGrid().setOnMouseEntered(event->{myMouseController.setCursorImage(myController.getScene(), myController.getGrid(), myController.CURSOR_ATTACK_TEST);;});
    }

    @Override
    public void onClick(Piece piece) {
        Piece actor = myController.getActivePiece();
        System.out.println(actor.toString()+"  "+ piece.getTypeID());
        myController.getActiveAction().doBehavior(actor, piece);
        System.out.println("dobehavior called");
            myController.setGridState(new SelectState(myController));
//     myController.changeCursor(myController.CURSOR_GLOVE_TEST);
          myMouseController.setCursorImage(myController.getScene(), myController.getGrid(), myController.CURSOR_GLOVE_TEST);
    }
}
