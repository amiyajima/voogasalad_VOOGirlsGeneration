package authoring.concretefeatures.menus;

import gamedata.JSON.JSONManager;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import tests.JSONBobTester;


/**
 * Menu selection to save game to JSON or read game from JSON
 * 
 * @author Rica
 *
 */
public class JSONBob extends Menu {
    private static final String NAME = "Save Game";
    private static final String ITEM_1 = "Save game to JSON";
    private static final String ITEM_2 = "Load game from JSON";
    public static final String SAVE_PREFIX = "Save as file: ";

    /**
     * Constructs menu items
     * 
     * @param library
     */
    public JSONBob () {
        super(NAME);
        MenuItem JSONCreator = new MenuItem(ITEM_1);
        MenuItem JSONLoader = new MenuItem(ITEM_2);
        JSONCreator.setOnAction(event -> handleSave());
        JSONLoader.setOnAction(event -> handleLoad());
        getItems().addAll(JSONCreator, JSONLoader);
    }

    /**
     * Creates a file chooser and uses that file path to save a JSON file to
     */
    private void handleSave () {
        Stage myStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(myStage);
        JSONManager myJSONmanager = new JSONManager();
        // TODO for now since game construction in front end doesn't work, this uses
        // the JSONBobTester to create a default game
        JSONBobTester jb = new JSONBobTester();
        // myJSONmanager.writeToJSON(jb.createNewGame(), file.getAbsolutePath());
        myJSONmanager.writeToJSON(jb.createNewGame(), file.getAbsolutePath());
    }

    private void handleLoad () {
        Stage myStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
        // TODO finish load implementation
    }
}
