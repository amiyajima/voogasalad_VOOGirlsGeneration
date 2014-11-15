package gamePlayer;

import java.util.List;
import javafx.stage.Stage;
import GameEngine.GameModel;

public abstract class Grid extends ViewComponent{
 

    private int myNumRow;
    private int myNumCol;
    
    public Grid (GameModel model, Stage stage) {
        super(model, stage);
        // TODO Auto-generated constructor stub
    }


    
    public abstract void showPossibleMoves(Actor actor);
}
