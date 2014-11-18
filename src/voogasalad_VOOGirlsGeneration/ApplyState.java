package voogasalad_VOOGirlsGeneration;

import gamedata.gamecomponents.Piece;

public class ApplyState implements GridState{

    private ViewController myController;
    
    public ApplyState(ViewController controller){
        myController = controller;
    }

    @Override
    public void onClick(Piece piece) {
        GameGrid grid = myController.getGrid();
       
    }
    


}
