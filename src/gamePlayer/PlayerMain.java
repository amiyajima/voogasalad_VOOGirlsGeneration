package gamePlayer;

import GameEngine.GameModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlayerMain extends Application{

    @Override
    public void start (Stage stage) throws Exception {
        
        stage.setTitle("Player!!");
        new GameView(stage, new GameModel());
        
    }
    
    public static void main(String[] args){
        launch(args);
    }

    
    
    
}
