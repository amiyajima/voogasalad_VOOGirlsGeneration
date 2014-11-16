package gamePlayer;

import GameEngine.GameModel;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameSpace extends ViewComponent{
    
    private Scene myGameScene;
    private BorderPane myPane;
    private Grid myGrid;
    
//    private GridPane tempGrid;
    private KeyboardControl myKeyboardControl;
    private HBox myMenuBar;
    
    private GameModel myGameModel;
    private Stage myStage;

    public GameSpace (GameModel model, Stage stage) {
        super(model, stage);
        myGameScene = new Scene((Parent) setUpDisplay(), GameView.SCREEN_WIDTH, GameView.SCREEN_HEIGHT);
       myGameScene.getStylesheets().add(GameView.STYLESHEET_LOCATION);
        getStage().setScene(myGameScene);
        
        myGameModel = model;
        myStage = stage;
        addUserControl();
    }

    @Override
    protected Node setUpDisplay () {
        myPane = new BorderPane();
        myPane.setCenter(tempararyGrid());
//        myPane.setTop(new ScorePane(getModel(), getStage()).getNode());
        
        addMenuBar();
        myPane.setTop(myMenuBar);
        return myPane;
    }
    
    private void addUserControl() {
       myKeyboardControl = new KeyboardControl();
       myKeyboardControl.controlActiveActor(myGrid, myGameScene);
       
       //also add mousecontrol here?
    }
    
    private void addMenuBar() {
        myMenuBar = new HBox();
        myMenuBar.getChildren().add(new Button("this")); 
        
        SaveButton sb = new SaveButton(myGameModel, myStage, null);
        myMenuBar.getChildren().add(sb.setUpDisplay());
    }
    
    private GridPane tempararyGrid(){
        
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
