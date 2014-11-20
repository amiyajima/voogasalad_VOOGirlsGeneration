package gamePlayer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import GameEngine.GameModel;
import javafx.geometry.Insets;

public class GameView{
    
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String STYLESHEET_LOCATION = "gamePlayer/stylesheet.css";
    private GameModel myModel;
    private Stage myStage;
    
    public GameView(Stage stage){
        
      //  myModel = model;
        myStage = stage;
        
       stage.setScene(initialScene());
       stage.show();
       
       
   
    }
    

    private Scene initialScene() {

        
        BorderPane startPane = new BorderPane();
        startPane.getStyleClass().add("borderPane");
        VBox vb = new VBox();
        vb.getChildren().addAll(new NewGameButton(myModel, myStage).setUpDisplay(),
                                       new LoadGameButton(myModel, myStage).setUpDisplay(),
                                       new SettingButton(myModel,myStage).setUpDisplay());

        vb.getStyleClass().add("vbox");
        startPane.setCenter(vb);
        Scene scene = new Scene(startPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(STYLESHEET_LOCATION);
        
        return scene;
        
    }

  

}
