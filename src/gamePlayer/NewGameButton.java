package gamePlayer;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import GameEngine.GameModel;
import javafx.scene.control.Button;

public class NewGameButton extends GameButton{
    
    public static final String NEW_GAME_BUTTON = "New Game";
    public static final String CHOOSE_GAME_TEXT = "Choose a Game:";
    public static final String POP_UP_TITLE = "my current games";

    public static final int POP_UP_HEIGHT= 300;
    public static final int POP_UP_WIDTH = 400;
    
    
    private Pane myPane;
    private VBox myVBox;
    private List<String> myOptions;
    
    public NewGameButton (GameModel model, Stage stage) {
        super(model, stage, NEW_GAME_BUTTON);
        myPane = new BorderPane();
        myVBox = new VBox();
        myOptions = getModel().getCurrentGames();
        //System.out.println(getModel().getCurrentGames());
        
        setUpContent();
    }
    
    private void setUpContent(){
        
        ArrayList<Button> labelList = new ArrayList<Button>();
        myOptions.forEach(string->{ Button l = new Button(string);
        l.setOnAction(event->{getModel().intializeGame(string);
//                                    setChanged();
//                                    notifyObservers();
        });
        
        labelList.add(l);          
        });

        myVBox.getChildren().addAll(labelList);
        myPane.getChildren().add(myVBox);
        
    }

    @Override
    protected void onClickAction () {
       
        Stage gameChoice = new Stage();
        gameChoice.setTitle(POP_UP_TITLE);
        gameChoice.initModality(Modality.WINDOW_MODAL);
        gameChoice.initOwner(getStage());
        Scene scene = new Scene(myPane, POP_UP_WIDTH, POP_UP_HEIGHT);
        scene.getStylesheets().add(GameView.STYLESHEET_LOCATION);
        gameChoice.setScene(scene);
        gameChoice.showAndWait();
        
    }
    
    

}
