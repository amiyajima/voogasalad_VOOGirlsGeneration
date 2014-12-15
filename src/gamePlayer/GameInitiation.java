package gamePlayer;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import fxml_main.ErrorPopUp;
import gamedata.JSON.JSONManager;
import gamedata.gamecomponents.Game;
import tests.TestGameCreator;

//This entire file is part of my masterpiece.
//Jesse Ling
public class GameInitiation {

	ViewController myViewController;
	Tab myTab;

	public GameInitiation(ViewController vc, Tab t) {
		myViewController = vc;
		myTab = t;
	}

	protected void testPlayGameInTab(Game readFromJSONFile) {
		Game myModel = readFromJSONFile;
		TestGameCreator tgc = new TestGameCreator();
		System.out.println("model found in viewcontroller: " + myModel);
		myViewController.initializeGrid();
		myTab.setContent(myViewController.getGameSpace());
		// myScene = new Scene(myGameSpace);
		// myStage.setScene(myScene);
		// mySplashStage.close();

		// System.out.println("VC: Current Level: " +
		// myModel.getCurrentLevel().getId());
		// System.out.println(myModel.getCurrentLevel().getGrid().toString());
		myModel.getCurrentLevel().getGrid().repopulateGrid();
	}

	protected void loadGameInTab() {
		Stage fileDialog = new Stage();
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
		fc.setInitialDirectory(new File("src/resources/json"));
		File f = fc.showOpenDialog(fileDialog);

		try {
			System.out.println("VC: loading game... ");
			JSONManager myJM = new JSONManager();
			myViewController.getPlayerEditor().editGUIPlayers(myJM.readFromJSONFile(f.getAbsolutePath()));
			// mySplashStage.show();
			// testPlayGameInTab(myJM.readFromJSONFile(f.getAbsolutePath()));
			System.out.println("VC: game loaded... ");
		} catch (FileNotFoundException fnfe) {
			// loadGameInTab();
			ErrorPopUp myError = new ErrorPopUp(fnfe.toString());
			myError.show();
		}

	}

}
