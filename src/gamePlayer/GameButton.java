package gamePlayer;

import GameEngine.GameModel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class GameButton extends ViewComponent{
    
   // public static final int BUTTON_PADDING = 13;
    
    private String myButtonText;
    
    public GameButton(GameModel model, Stage stage, String text){
        super(model, stage);
        myButtonText = text;
           
    }
    @Override
    protected Node setUpDisplay(){
        Button button = new Button(myButtonText);
        //button.setPadding(new Insets(BUTTON_PADDING));
        button.setOnAction(event-> onClickAction());
        return button;
    }
    
    protected abstract void onClickAction();
    
}
