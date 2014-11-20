package gamePlayer;

import GameEngine.GameModel;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameDisplay{
     
     private Grid myGrid;
     private GameModel myGameModel;
     
     public static final int GAME_WIDTH = 500;
     public static final int GAME_HEIGHT = 400;
     
     public GameDisplay(GameModel model){
         myGameModel = model;
         myGrid = model.InitializeGame();
         
         
         
         
     }
     
    
//    
//
//    private Scene myGameScene;
//    private BorderPane myPane;
//    
//    private GameModel myModel;
//    private Stage myStage;
//    
//   // private Grid myGrid;
//    
//    public GameDisplay (GameModel model, Stage stage) {
//        //super(model, stage);
//        myGameScene = new Scene((Parent) setUpDisplay(), GameView.SCREEN_WIDTH, GameView.SCREEN_HEIGHT);
//        
//        stage.setScene(myGameScene);
//        myGameScene.getStylesheets().add(GameView.STYLESHEET_LOCATION);
//    }
//
//
////
////    public GameSpace (GameModel model, Stage stage) {
////        super(model, stage);
////        myGameScene = new Scene((Parent) setUpDisplay(), GameView.SCREEN_WIDTH, GameView.SCREEN_HEIGHT);
////        
////        getStage().setScene(myGameScene);
////        myGameScene.getStylesheets().add(GameView.STYLESHEET_LOCATION);
////    }
//
//    protected Node setUpDisplay () {
//       
//        myPane = new BorderPane();
//        myPane.setCenter(tempararyGrid());
//        
//        ScorePane sp = new ScorePane(getModel(), getStage());
//        ToggleScoreLabel ts = new ToggleScoreLabel(getModel(), getStage());
//       
//        ts.addObserver(sp);
//
//        myPane.setTop(sp.getNode());
//        myPane.setRight(ts.getNode());
//            
//        return myPane;
//
//    }
    
    protected GridPane tempararyGrid(){
        
        GridPane gp = new GridPane();
        
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                Rectangle r = new Rectangle(30, 30);
                r.setFill(Color.WHITE);
                gp.add(r, i, j);
            }
        }
        gp.getStyleClass().add("gridpane");
        return gp;
    }
    
    

}
