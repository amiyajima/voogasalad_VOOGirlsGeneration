package gamePlayer;

//This entire file is part of my masterpiece
// Yiran Zhu

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import fxml_main.ErrorPopUp;
import gamedata.JSON.JSONManager;
import gamedata.gamecomponents.Piece;

/**
 * the class to configure setup of player GUI. also 
 * allows for altenative ways of updating view components (stats panel 
 * for example) corresponding Lambda function is used in View Controller class that allows
 * for editing without passing around object or data structure. 
 * 
 * @author yiran
 *
 */
public class ViewConfig {

    public static final String ERROR_FINDING = "Could not find file: \n";
    public static final String USER_DIR = "user.dir";
    private ViewController myViewController;


    public ViewConfig(ViewController vc){
        myViewController = vc;

    }

    /**
     * Loads an FXML file to a given node
     * 
     * @param url the url of the fxml file
     * @param n the node to load the fxml root to
     */
    protected void loadFXML(String url, Node n) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        fxmlLoader.setController(myViewController);
        fxmlLoader.setRoot(n);
        try {
            fxmlLoader.load();

        } catch (IOException exception) {

            showErrorPopup(exception.toString());
        }
    }

    /**
     * Generates a drop down menu that allow user to choose a new Game to play.
     * The Games are generated from the directory that stores all json files
     * defined from authoring environment
     * 
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    protected void newGame() throws UnsupportedAudioFileException, IOException,
    LineUnavailableException {

        List<File> games = getResourceGames();

        games.forEach(file -> {
            MenuItem l = new MenuItem();
            l.setText(file.getName().substring(0, file.getName().length() - myViewController.JSON_EXTENSION.length()));

            myViewController.initGameMenu(newGameMenu->initMenu(newGameMenu, l));

        });

    }

    private void initMenu (MenuButton newGameMenu, MenuItem l) {

        newGameMenu.getItems().add(l);
        l.setOnAction(event -> {
            String gameLoc = myViewController.GAME_LOCATION + l.getText() + myViewController.JSON_EXTENSION;
            try {
                JSONManager myJM = new JSONManager();

                myViewController.testPlayGame(myJM.readFromJSONFile(gameLoc));

            }
            catch (FileNotFoundException e) {

                showErrorPopup(ERROR_FINDING+ gameLoc);
            }
        });
    }


    /**
     * The method to get all json files from the resources directory that stores
     * all the games user has defined from the authoring environment
     */
    private List<File> getResourceGames() {

        List<File> gameList = new ArrayList<File>();
        File files = new File(System.getProperty(USER_DIR) + myViewController.GAME_LOCATION);

        for (File f : files.listFiles()) {
            gameList.add(f);
        }

        return gameList;
    }
    /**
     * method to show the error dialog box
     * @param error the error message to show
     */
    protected void showErrorPopup(String error){
        ErrorPopUp message = new ErrorPopUp(error);
        message.show();
    }

    /**
     * Update the stats pane with stats from the selected piece
     * 
     * @param piece
     */
    protected void updateStats(Piece piece) {

        myViewController.updateStats(statsPane->update(piece, statsPane));
    }

    private void update (Piece piece, VBox statsPane) {
        statsPane.getChildren().clear();
        ArrayList<Text> stats = new ArrayList<Text>();

        piece.getStats()
        .getStatNames()
        .forEach(
                 key -> stats.add(new Text(key + ":  "
                         + piece.getStats().getValue(key))));

        statsPane.getChildren().addAll(stats);

    }
}
