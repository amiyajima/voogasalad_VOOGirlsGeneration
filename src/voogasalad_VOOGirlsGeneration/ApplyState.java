package voogasalad_VOOGirlsGeneration;

import gamedata.gamecomponents.Piece;

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
