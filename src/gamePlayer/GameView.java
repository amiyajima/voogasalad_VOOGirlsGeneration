package gamePlayer;

import java.awt.Paint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import GameEngine.GameModel;
import javafx.application.Application;
import javafx.geometry.Pos;

public class GameView{
    
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String STYLESHEET_LOCATION = "gamePlayer/stylesheet.css";
    private GameModel myModel;
    private Stage myStage;
    
    public GameView(Stage stage, GameModel model){
        
        myModel = model;
        myStage = stage;
        
       stage.setScene(initialScene());
       stage.show();
       
   
    }
    

    private Scene initialScene() {
        
        Pane startPane = new Pane();
        VBox vb = new VBox();
        vb.getChildren().addAll(new NewGameButton(myModel, myStage).setUpDisplay(),
                                       new LoadGameButton(myModel, myStage).setUpDisplay(),
                                       new SettingButton(myModel,myStage).setUpDisplay());
        startPane.getChildren().add(vb);
        Scene scene = new Scene(startPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(STYLESHEET_LOCATION);
        
        return scene;
        
    }
    
    
    

}
