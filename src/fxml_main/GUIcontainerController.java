package fxml_main;

import gamePlayer.ViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import authoring.createedit.GamePropertiesEditor;
import authoring.data.GamePropertiesData;


public class GUIcontainerController implements Initializable {

    private Scene myScene;
    private int myAuthorTabCount = 0;
    private int myPlayerTabCount = 0;

    @FXML
    private MenuItem gameProperties;

    @FXML
    private MenuItem newAuthor;

    @FXML
    private MenuItem newPlayer;

    @FXML
    private Tab testauthor;

    @FXML
    private TabPane displayedTabs;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        newAuthor.setOnAction(event -> LoadNewAuthoring());

        newPlayer.setOnAction(event -> loadNewPlayer());

    }

    private void loadNewPlayer () {
        // Stage player=new Stage();
        // try {
        // new ViewController(player);
        // } catch (UnsupportedAudioFileException | IOException
        // | LineUnavailableException e) {
        // e.printStackTrace();
        // }
        //
        // player.show();

        Tab playerTab = new Tab("Player" + ++myPlayerTabCount);
        try {
            new ViewController(playerTab);
        }
        catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        displayedTabs.getTabs().add(playerTab);
        displayedTabs.getSelectionModel().select(playerTab);

    }

    private void LoadNewAuthoring () {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIcontainerController.class.getResource("Voogirls_Authoring.fxml"));
            Parent root = loader.load();
            AuthoringController authorController = loader.getController();

            Consumer<GamePropertiesData> c = (GamePropertiesData gpd) -> {
                authorController.initData(gpd);
                Tab tab = new Tab("Authoring" + ++myAuthorTabCount);
                tab.setContent(root);
                displayedTabs.getTabs().add(tab);
                displayedTabs.getSelectionModel().select(tab);
            };

            GamePropertiesEditor gpEditor = new GamePropertiesEditor(c);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showGamePropertiesWindow () {
    }

}
