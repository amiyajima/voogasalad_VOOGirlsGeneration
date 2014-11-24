package authoring.concretefeatures.menus;


import java.io.File;
import java.io.FileNotFoundException;
import gamedata.JSON.JSONManager;
import gamedata.gamecomponents.Game;
import tests.JSONBobTester;
import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.GameCreator;
import authoring.concretefeatures.LevelEditor;
import authoring_environment.WorkspaceView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
// import authoring.concretefeatures.GameCreator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Basic menu options for the authoring environment
 * @author Rica
 *
 */
public class FileX extends Menu {
        public static final String myLabel = "File";
        public static final String newLevelLabel = "New Level";
        public static final String openLabel = "Open";
        public static final String saveLabel = "Save";
        public static final String exitLabel = "Exit";
	private WorkspaceView myWorkspaceView;
	
    public FileX (WorkspaceView wsView) {
        super(myLabel);
        myWorkspaceView = wsView;
        
        MenuItem newLevel = new MenuItem(newLevelLabel);
        MenuItem open = new MenuItem(openLabel);
        MenuItem save = new MenuItem(saveLabel);
        MenuItem exit = new MenuItem(exitLabel);

        newLevel.setOnAction(event -> handleNewLevel());
        open.setOnAction(event -> handleLoad());
        save.setOnAction(event -> handleSave());
        exit.setOnAction(new ExitHandler());
                
        getItems().addAll(newLevel, open, save, exit);
    }
    
    private void handleNewLevel() {
        PopupWindow p = new GameCreator(myWorkspaceView);
        p.show();
    }

    private void handleLoad () {
        Stage myStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showOpenDialog(myStage);
        JSONManager myJSONmanager = new JSONManager();
        try {
            Game loadedGame = myJSONmanager.readFromJSONFile(file.getAbsolutePath());
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO load features of game into authoring environment

    }

    /**
     * Creates a file chooser and uses that file path to save a JSON file to
     */
    private void handleSave () {
        Stage myStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
        java.io.File file = fileChooser.showSaveDialog(myStage);
        JSONManager myJSONmanager = new JSONManager();
        // TODO for now this creates a default game using the JSONBobTester
        JSONBobTester jb = new JSONBobTester();
        myJSONmanager.writeToJSON(jb.createNewGame(), file.getAbsolutePath());
    }
    
    private class ExitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent event) {
            // Saves a New Game
        }
    }
    
    

}