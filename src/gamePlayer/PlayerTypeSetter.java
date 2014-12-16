// This entire file is part of my masterpiece.
// YOUR NAME
package gamePlayer;

import java.io.IOException;
import java.util.List;
import fxml_main.ErrorPopUp;
import gamedata.gamecomponents.Game;
import gameengine.player.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlayerTypeSetter {
    public static final String PLAYER_FXML = "playerEditorScene.fxml";
    private static final String SIMPLE_AI_PLAYER = "Simple AI player";
    private static final String HUMAN_PLAYER = "Human Player";
    private static final String PLAYER = "Player: ";
    
    @FXML
    private BorderPane myPlayers;
    @FXML
    private BorderPane editPlayerRoot;
    @FXML
    private VBox playersList;
    @FXML
    private TextField editPlayerName;
    @FXML
    private ComboBox<String> playerTypeCombo;
    @FXML
    private Button startGameButton;
    @FXML
    private Button setPlayerButton;
    private Scene myScene;
    private Game myGame;
    private ViewController myVC;
    
    /**
     * Contains scene for users to choose their player of choice
     * @param game
     */
    public PlayerTypeSetter(Game game, ViewController vc) {
        loadFXML(PLAYER_FXML,editPlayerRoot);
        myScene = new Scene(editPlayerRoot);
        myGame = game;
        myVC = vc;
        startLoadPlayers();
    }
    
    public Scene startLoadPlayers() {
        try {
            for (Player p : myGame.getPlayers()) {
                Label playerLabel = new Label(PLAYER + p.toString());
                playerLabel.setOnMouseClicked(event -> editSpecificPlayer(p.getID()));
                playersList.getChildren().add(playerLabel);
            }
            playerTypeCombo.getItems().addAll(HUMAN_PLAYER, SIMPLE_AI_PLAYER);
            playerTypeCombo.setValue(HUMAN_PLAYER);
            
            startGameButton.setOnMouseClicked(event->myVC.testPlayGame(myGame));
            return myScene;
       }
       catch (Exception e) {
           ErrorPopUp myError = new ErrorPopUp(e.toString());
           myError.show();
           return null;
       }
    }
    
    /**
     * Allows user to edit the specific player to be a player of a specific type
     * @param playerID
     */
    private void editSpecificPlayer(int playerID) {
        editPlayerName.setText(String.valueOf(playerID));
        setPlayerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle (MouseEvent arg0) {
                myGame.replacePlayer(playerID, playerTypeCombo.getValue());
            }
            
        });
    }
    
    
    /**
     * Loads FXML for this View
     * @param url
     * @param n
     */
    private void loadFXML(String url, Node n) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(n);
        try {
                fxmlLoader.load();

        } catch (IOException exception) {
            ErrorPopUp myError = new ErrorPopUp(exception.toString());
            myError.show();
                throw new RuntimeException(exception);
        }
    }
}
