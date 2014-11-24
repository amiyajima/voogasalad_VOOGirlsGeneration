package gamePlayer;

import javafx.scene.Node;
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
        myController.setGridState(this);

        myMouseController = myController.getMouseController();
        
//        myController.getGrid().setOnMouseEntered(event->{myController.changeCursor(myController.CURSOR_ATTACK_TEST);});
//      myController.getGrid().setOnMouseEntered(event->{myMouseController.setCursorImage(myController.getScene(), myController.getGrid(), myController.CURSOR_ATTACK_TEST);;});
//      myMouseController.setOnClick(myController, myController.getGridState(), myController.getGrid());

    }

    @Override
    public void onClick(Piece piece) {
        System.out.println("CLICK during apply state");
        
        
        Piece actor = myController.getActivePiece();
//        System.out.println(actor.toString()+"  "+ piece.getTypeID());
        myController.getActiveAction().doBehavior(actor, piece);
//        System.out.println("dobehavior called");
            myController.setGridState(new SelectState(myController));
          myMouseController.setCursorImage(myController.getScene(), myController.getGrid(), myController.CURSOR_GLOVE_TEST);
    }
}
