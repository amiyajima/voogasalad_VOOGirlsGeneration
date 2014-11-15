package gamePlayer;

import java.util.List;
import javafx.stage.Stage;
import GameEngine.GameModel;

public abstract class Grid extends ViewComponent{
 

    private int myNumRow;
    private int myNumCol;
    
    public Grid (GameModel model, Stage stage, int row, int col) {
        super(model, stage);
        
        myNumRow = row;
        myNumCol = col;
    }
 
    public abstract void showPossibleMoves(Actor actor);
    
   // public setActiveActor()
}
