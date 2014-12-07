package fxml_main;

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
import authoring.data.ActionData;
import authoring.data.LevelData;
import authoring.data.PatchTypeData;
import authoring.data.PieceTypeData;
import authoring.eventeditor.EventEditorController;

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
	private MenuItem events;
	
	@FXML
        private MenuItem actonsLogicChart;
	
	private GUIGridReference myGridReference;
	private PieceController myPieceController;
	private PatchController myPatchController;
	private LevelController myLevelController;
	
	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		ActionData actions = new ActionData();
		LevelData myLevelData = new LevelData();
		PieceTypeData myPieceTypes = new PieceTypeData();
		PatchTypeData myPatchTypes = new PatchTypeData();
		myGridReference = new GUIGridReference();
		
		myPieceController = new PieceController(myPiecesVBox, myPropertiesSPane, myGridReference, 
				myPieceTypes, actions);
	    myPatchController = new PatchController(myPatchesVBox, myPropertiesSPane, myGridReference,
	    		myPatchTypes);
	    myLevelController = new LevelController(myLevelsVBox, myPropertiesSPane, myGridSPane,
	    		myGridReference, myLevelData, myPieceTypes, myPatchTypes);
	}
	
	@FXML
	//TODO: make the List<Event> storing all Events an input to this method!
	private void showEventsEditorWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/authoring/eventeditor/EventEditor.fxml"));
		Parent root = loader.load();

		Stage eventEditorStage  = new Stage();
		eventEditorStage.setTitle("Events Editor");
		eventEditorStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		eventEditorStage.setScene(scene);

		EventEditorController controller = loader.getController();
		controller.setStage(eventEditorStage);

		eventEditorStage.showAndWait();
	}
	
	@FXML
	//TODO: [IMPORTANT] This method will need a List<String> or Set<String> that contains names of Pieces
	//Also, need a list of existing actions
        private void showActionzlogicChartWindow() throws IOException{
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
}