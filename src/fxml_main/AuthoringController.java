package fxml_main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import authoring.data.ActionData;
import authoring.eventeditor.EventEditorController;
import authoring.eventeditor.NewConditionController;
import authoring_environment.ShapeGrid;
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

public class AuthoringController implements Initializable {

	@FXML
	private ScrollPane myPropertiesSPane;
	
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
	
	private ShapeGrid myCurrentGrid;
	private PieceController myPieceController;
	private PatchController myPatchController;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		ActionData actions = new ActionData();
		
		myPieceController = new PieceController(myPiecesVBox, myPropertiesSPane, myCurrentGrid, actions);
	    myPatchController = new PatchController(myPatchesVBox, myPropertiesSPane, myCurrentGrid);
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
		
		//EventsEditorController.setEvents(events); 

		eventEditorStage.showAndWait();
	}
}