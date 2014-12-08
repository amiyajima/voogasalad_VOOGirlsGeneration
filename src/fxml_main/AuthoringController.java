package fxml_main;

import gamedata.gamecomponents.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	
	//Menu items
	
	@FXML
    private MenuItem actonsLogicChart;
	
    @FXML
    private MenuItem gameProperties;

    @FXML
    private MenuItem playerEditor;
    
    @FXML
    private MenuItem mySaveBtn;

    private GUIGridReference myGridReference;
    private PieceController myPieceController;
    private PatchController myPatchController;
    private LevelController myLevelController;
    private ActionController myActionController;
    
    
    // Authoring Data
    private GameAuthoringData myTotalData;
    private GamePropertiesData myGamePropertiesData;


	
	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		ActionData actionData = null;
		LevelData levelData = null;
		PieceTypeData pieceTypes = null;
		PatchTypeData patchTypes = null;
		myGamePropertiesData = null;
		
		myTotalData = new GameAuthoringData();
		myTotalData.initDataPointers(levelData, pieceTypes, patchTypes,
				actionData, myGamePropertiesData);
		
		GUIGridReference myGridReference = new GUIGridReference();

		myPieceController = new PieceController(myPiecesVBox, myPropertiesSPane, myGridReference, 
				pieceTypes, actionData);
	    myPatchController = new PatchController(myPatchesVBox, myPropertiesSPane, myGridReference,
	    		patchTypes);
	    myLevelController = new LevelController(myLevelsVBox, myPropertiesSPane, myGridSPane,
	    		myGridReference, levelData, pieceTypes, patchTypes);
	    
	    myActionController = new ActionController(myActionsVBox, myPropertiesSPane, myGridReference, actionData);
	}
	
	@FXML
	//TODO: [IMPORTANT] This method will need a List<String> or Set<String> that contains names of Pieces
	//Also, need a list of existing actions
        private void showActionslogicChartWindow() throws IOException{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/authoring/actionslogic/ActionLogic.fxml"));
                Parent root = loader.load();

                Stage eventEditorStage  = new Stage();
                eventEditorStage.setTitle("Actions Logic Chart");
                eventEditorStage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(root);
                eventEditorStage.setScene(scene);

                ActionLogicController controller = loader.getController();
                
                eventEditorStage.showAndWait();
        }
	
	@FXML
	private void showGamePropertiesWindow(){
			GamePropertiesEditor gamePptEditor=new GamePropertiesEditor(myGamePropertiesData);
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
    private void saveGame() {
    	Game game = myTotalData.createGame();
    }
    

}
