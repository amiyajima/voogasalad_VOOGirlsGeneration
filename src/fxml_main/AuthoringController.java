package fxml_main;

import gamePlayer.ViewController;
import gamedata.JSON.JSONManager;
import gamedata.action.Action;
import gamedata.action.ConcreteAction;
import gamedata.gamecomponents.Game;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import authoring.actionslogic.ActionLogicController;
import authoring.concretefeatures.StatsTotalEditor;
import authoring.createedit.GamePropertiesEditor;
import authoring.data.ActionData;
import authoring.data.GameAuthoringData;
import authoring.data.GamePropertiesData;
import authoring.data.LevelData;
import authoring.data.PatchTypeData;
import authoring.data.PieceTypeData;


public class AuthoringController implements Initializable {


    @FXML
    private ScrollPane myPropertiesSPane;

    @FXML
    private VBox myLevelsVBox;

    @FXML
    private VBox myPiecesVBox;

    @FXML
    private VBox myPatchesVBox;

    @FXML
    private VBox myActionsVBox;

    @FXML
    private ScrollPane myGridSPane;

    // Menu items

    @FXML
    private MenuItem actonsLogicChart;

    @FXML
    private MenuItem gameProperties;

    @FXML
    private MenuItem playerEditor;
    
    @FXML
    private MenuItem mySaveBtn;

    @FXML
    private MenuItem mySaveAsBtn;

    private GUIGridReference myGridReference;
    private PieceController myPieceController;
    private PatchController myPatchController;
    private LevelController myLevelController;
    private ActionController myActionController;

    // Authoring Data
    private GameAuthoringData myTotalData;
    private ActionData myActionData;
    private LevelData myLevelData;
    private PieceTypeData myPieceTypes;
    private PatchTypeData myPatchTypes;
    private GamePropertiesData myGamePropertiesData;

    @Override
    // This method is called by the FXMLLoader when initialization is complete
    public void initialize (URL fxmlFileLocation, ResourceBundle resources) {
        myActionData = new ActionData();
        myLevelData = new LevelData();
        myPieceTypes = new PieceTypeData();
        myPatchTypes = new PatchTypeData();
//        myGamePropertiesData = new GamePropertiesData();

        
    }

    @FXML
    private void showActionslogicChartWindow () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/authoring/actionslogic/ActionLogic.fxml"));
        ActionLogicController controller = loader.getController();
        Action actionA = new ConcreteAction("Attack", null, null, null, null);
        myActionData.add(actionA);
        myPieceTypes.add(null);
        List<String> test1 = new ArrayList<String>();
        test1.add("Attack");
        test1.add("Heal");
        List<String> test2 = new ArrayList<String>();
        test2.add("PieceA");
        test2.add("PieceB");
        controller.getData(test1, test2);
        System.out.println("not yet");
        Parent root = loader.load();
        Stage actionLogicStage = new Stage();
        actionLogicStage.setTitle("Actions Logic Chart");
        actionLogicStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        actionLogicStage.setScene(scene);

        System.out.println("done");


        actionLogicStage.showAndWait();
    }

    @FXML
    private void showGamePropertiesWindow () {
    	GamePropertiesEditor gamePptEditor = new GamePropertiesEditor(myGamePropertiesData);
    	gamePptEditor.disableChangingGridShape();
    }

    @FXML
    private void showPlayerEditWindow () {
    	StatsTotalEditor statsEditor = new StatsTotalEditor();
    	statsEditor.setTitle("Player Editor");
    	statsEditor.setX(450);
    	statsEditor.setY(200);
    	statsEditor.show();
    }


    @FXML
    private void saveGame () {

    	Game game = myTotalData.createGame();
    	Player p1 = new HumanPlayer(1);
    	List<Player> players = new ArrayList<Player>();
    	players.add(p1);
    	game.addPlayers(players);


        JSONManager myJM = new JSONManager();
        
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("src/resources/json"));
        File f = fc.showSaveDialog(null);
        String basePath = System.getProperty("user.dir");
        String absolutePath = f.getPath();
        String relativePath = "";
        if (absolutePath.startsWith(basePath)) {
                relativePath = absolutePath.substring(basePath.length() + 1);
        }
        myJM.writeToJSON(game, f.getPath());
    }
    
    @FXML

    private void saveAsGame () {
    	Game game = myTotalData.createGame();
    	Player p1 = new HumanPlayer(1);
    	List<Player> players = new ArrayList<Player>();
    	players.add(p1);
    	game.addPlayers(players);

    	Stage s = new Stage();
    	try {
    		ViewController viewCtrl = new ViewController(s);
    		viewCtrl.testPlayGame(game);
    	}
    	catch (UnsupportedAudioFileException | IOException
    			| LineUnavailableException e) {
    		System.out.println("Opening ViewController didn't work!");
    	}
    }



    public void initData(GamePropertiesData gamePropertiesData) {
    	myGamePropertiesData=gamePropertiesData;

    	myTotalData = new GameAuthoringData(myLevelData, myPieceTypes, myPatchTypes,
    			myActionData, myGamePropertiesData);
    	GUIGridReference myGridReference = new GUIGridReference();

    	myPieceController = new PieceController(myPiecesVBox, myPropertiesSPane, myGridReference,
    			myPieceTypes, myActionData, myGamePropertiesData);
    	myPatchController = new PatchController(myPatchesVBox, myPropertiesSPane, myGridReference,
    			myPatchTypes);
    	myLevelController =
    			new LevelController(myLevelsVBox, myPropertiesSPane, myGridSPane,
    					myGridReference, myLevelData, myPieceTypes, myPatchTypes,
    					myGamePropertiesData.getGridShape());

    	myActionController =
    			new ActionController(myActionsVBox, myPropertiesSPane, myGridReference,
    					myActionData, myGamePropertiesData.getGridShape());
    }


}
