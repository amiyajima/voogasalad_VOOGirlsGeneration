package gamePlayer;

import gamedata.gamecomponents.Piece;

/**
 * Class representing the state of the grid when a particular action 
 * has been selected and is ready to be applied on the grid
 *
 */
public class ApplyState implements GridState{

    private ViewController myController;
    
    public ApplyState(ViewController controller){
        myController = controller;
    }

    @Override
    public void onClick(Piece piece) {
        Piece actor = myController.getActivePiece();
        myController.getActiveAction().doBehavior(actor, piece);
            myController.setGridState(new SelectState(myController));
     
       
    }
    


}
