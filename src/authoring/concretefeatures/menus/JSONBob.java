package authoring.concretefeatures.menus;

import gamedata.gamecomponents.Game;
import gameengine.engine.GameBuilder;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring_environment.LibraryView;
import java.io.File;

public class JSONBob extends Menu {
    private LibraryView myLibrary;
    private static final String NAME = "JSON";
    private static final String ITEM_1 = "Save game";
    private static final String ITEM_2 = "Load game";
    public static final String SAVE_PREFIX = "Save as file: ";

    
    public JSONBob(LibraryView library) {
        super(NAME);
        myLibrary = library;
        MenuItem JSONCreator = new MenuItem(ITEM_1);
        MenuItem JSONLoader = new MenuItem(ITEM_2);
        setAction(JSONCreator);
        getItems().addAll(JSONCreator, JSONLoader);
    }

    private void setAction (MenuItem jSONCreator) {
        jSONCreator.setOnAction(event -> handle());
    }

    private void handle () {
        Stage myStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(myStage);
        GameBuilder myGameBuilder = new GameBuilder();
        JSONBobTester jb = new JSONBobTester();
        myGameBuilder.writeToJSON(jb.createNewGame(), file.getAbsolutePath());
    }
}
