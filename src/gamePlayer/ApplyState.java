package gamePlayer;


import javafx.scene.paint.Color;

import gamedata.gamecomponents.Piece;

/**
 * Class representing the state of the grid when a particular action 
 * has been selected and is ready to be applied on the grid
 *
 */
public class ApplyState implements IGridState{

    private ViewController myController;
    
    public ApplyState(ViewController controller){
        System.out.println("new ApplyState");
        myController = controller;
        myController.getGrid().setOnMouseEntered(event->{
            myController.changeCursor(myController.CURSOR_ATTACK_TEST);
            
            myController.getGrid().getChildren().forEach(node->{node.setOnMouseEntered(event2->{
                                                            myController.highLightEffectRange(event2, Color.RED);
                                                            });
                                                           node.setOnMouseExited(event3->myController.highLightActionRange());});
                                                                
            
            
        });
    }

    @Override
    public void onClick(Piece piece) {
        Piece actor = myController.getActivePiece();
    
        myController.getActiveAction().doBehavior(actor, piece);
        System.out.println("dobehavior called");
            myController.setGridState(new SelectState(myController));
            myController.changeCursor(myController.CURSOR_GLOVE_TEST);
       
    }
    


}
