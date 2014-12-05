/**
 * Sample Skeleton for "simple.fxml" Controller Class
 * Use copy/paste to copy paste this code into your favorite IDE
 **/

package authoring.eventeditor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

//TODO: RIGHT NOW THIS CLASS ALSO HOLDS A LOT OF DATA. WE SHOULD NOT DO THAT
public class EventEditorController implements Initializable {

	//Lists
	@FXML
	private ListView<String> eventsListView;
	@FXML
	private ListView<String> conditionsListView;
	@FXML
	private ListView<String> actionsListView;
	
	//Buttons
	@FXML
	private Button newEvent;
	@FXML
	private Button delEvent;
	@FXML
	private Button newCondition;
	@FXML
	private Button delCondition;
	@FXML
	private Button delAction;
	@FXML
	private Button newAction;
	
	//The model
	private EventEditorMain myMain;
	
	private final ObservableList<String> conditionsList = FXCollections.observableArrayList();
	private final ObservableList<String> actionsList = FXCollections.observableArrayList();
	
	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		eventsListView.getItems().addAll("Event 1", "Event 2");
		
		//Makes it so that the right-hand Editor updates with respect to the selected
		//Event on the left side
		eventsListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedEvent) -> showEventInEditor(selectedEvent));
	}
	
	public void setMain(EventEditorMain main){
		myMain = main;
	}
	
	@FXML
	private void showEventInEditor(String s){
		conditionsListView.setItems(actionsList);
		actionsListView.setItems(actionsList);
	}
	
	@FXML
	private void handleNewCondition() throws IOException{
		showNewConditionWindow();
	}
	
	@FXML
	private void handleDelCondition(){
	    int delIdx = conditionsListView.getSelectionModel().getSelectedIndex();
	    conditionsListView.getItems().remove(delIdx);
	}
	
	@FXML
	private void handleNewAction() throws IOException{
		showNewActionWindow();
	}
	
	@FXML
	private void handleDelAction(){
		int delIdx = actionsListView.getSelectionModel().getSelectedIndex();
	    actionsListView.getItems().remove(delIdx);
	}
	
	@FXML
	private void handleClose(){
		
	}
	
	private void showNewConditionWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/authoring/eventeditor/NewCondition.fxml"));
		Parent root = loader.load();

		Stage newConditionStage  = new Stage();
		newConditionStage.setTitle("New Condition");
		newConditionStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		newConditionStage.setScene(scene);

		NewConditionController controller = loader.getController();

		//		controller.setEvent(event);

		newConditionStage.showAndWait();
	}
	
	private void showNewActionWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/authoring/eventeditor/NewAction.fxml"));
		Parent root = loader.load();

		Stage newConditionStage  = new Stage();
		newConditionStage.setTitle("New Action");
		newConditionStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		newConditionStage.setScene(scene);

		NewConditionController controller = loader.getController();

		//		controller.setEvent(event);

		newConditionStage.showAndWait();
	}
}