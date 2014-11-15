package gamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import GameEngine.GameModel;
import javafx.scene.control.Button;

public class NewGameButton extends GameButton{

    public static final String NEW_GAME_BUTTON = "New Game";
    public static final String CHOOSE_GAME_TEXT = "Choose a Game:";
    public static final String POP_UP_TITLE = "my current games";

    public static final int POP_UP_HEIGHT= 300;
    public static final int POP_UP_WIDTH = 400;


    private BorderPane myPane;
    private VBox myVBox;
    private List<String> myOptions;
    private Stage myStage;
    private Scene myScene;

    public NewGameButton (GameModel model, Stage stage) {
        super(model, stage, NEW_GAME_BUTTON);
        myPane = new BorderPane();
        myVBox = new VBox();
        myOptions = getModel().getCurrentGames();

        myStage = new Stage();
        setUpContent();
    }

    private void setUpContent(){

        ArrayList<Button> buttonList = new ArrayList<Button>();
        myOptions.forEach(string->{ Button l = new Button(string);
        l.setOnAction(event->{
            getModel().intializeGame(string);
            myStage.close();
            new GameSpace(getModel(), getStage());


        });
        buttonList.add(l);          
        });

        myVBox.getChildren().addAll(buttonList);
        myVBox.getStyleClass().add("vbox");

        myPane.setCenter(myVBox);
        myScene = new Scene(myPane, POP_UP_WIDTH, POP_UP_HEIGHT);
        myScene.getStylesheets().add(GameView.STYLESHEET_LOCATION);
    }

    @Override
    protected void onClickAction () {


        myStage.setTitle(POP_UP_TITLE);

        myStage.setScene(myScene);
        myStage.show();

    }

    @Override
    public void update(Observable o, Object obj){
        if (o instanceof GameModel){
            myStage.close();
            enterGame();
        }
    }

    private void enterGame(){

    }



}
