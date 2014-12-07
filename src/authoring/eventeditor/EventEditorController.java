package authoring.eventeditor;

import gamedata.events.Condition;
import gamedata.events.Event;
import gamedata.events.GlobalAction;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventEditorController implements Initializable {


	//Lists
	@FXML
	private ListView<Event> eventsListView;
	@FXML
	private ListView<Condition> conditionsListView;
	@FXML
	private ListView<GlobalAction> actionsListView;

	//Buttons
	@FXML
	private Button newEvent;
	@FXML
	private Button delEvent;
	@FXML
	private Button newCondition;
	@FXML
	private Button editCondition;
	@FXML
	private Button delCondition;
	@FXML
	private Button delAction;
	@FXML
	private Button editAction;
	@FXML
	private Button newAction;
	
	private Stage myParentStage;
	private Stage newConditionStage;
	
	private List<Condition> myConditions;
	private List<GlobalAction> myActions;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		//Testing
		eventsListView.getItems().addAll(new Event("Test Event 1"),
										 new Event("Test Event 2"));

		//Makes it so that the right-hand Editor updates with respect to the selected
		//Event on the left side
		eventsListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedEvent) -> showEventInEditor(selectedEvent));
	}

	@FXML
	private void showEventInEditor(Event event){
		myConditions = event.getConditions();
		myActions = event.getGlobalActions();

		conditionsListView.setItems((ObservableList<Condition>) myConditions);
		actionsListView.setItems((ObservableList<GlobalAction>) myActions);
	}

	@FXML
	private void handleNewCondition() throws IOException{
		Consumer<Condition> okLambda = (Condition condition) -> {
		    conditionsListView.getItems().add(condition);
		    newConditionStage.close();
		};
		showNewConditionWindow(okLambda, null);
	}
	
	@FXML
	private void handleEditCondition() throws IOException{
		Condition entry = conditionsListView.getSelectionModel().getSelectedItem();
		
		System.out.println("what");
		System.out.println(entry);
		
		if(entry==null){
			return;
		}
		
		Consumer<Condition> okLambda = (Condition condition) -> {
			conditionsListView.getItems().remove(entry);
		    conditionsListView.getItems().add(condition);
		    newConditionStage.close();
		};
		showNewConditionWindow(okLambda, entry);
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
	private void handleEditAction() throws IOException{
		
	}

	@FXML
	private void handleDelAction(){
		int delIdx = actionsListView.getSelectionModel().getSelectedIndex();
		actionsListView.getItems().remove(delIdx);
	}
	
	@FXML
	private void handleNewEvent(){
		
	}
	
	@FXML
	private void handleDelEvent(){
		int delIdx = eventsListView.getSelectionModel().getSelectedIndex();
		conditionsListView.getItems().remove(delIdx);
	}

	private void showNewConditionWindow(Consumer<Condition> okLambda, Condition entry) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/authoring/eventeditor/NewCondition.fxml"));
		Parent root = loader.load();

		newConditionStage  = new Stage();
		newConditionStage.setTitle("New Condition");
		newConditionStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		newConditionStage.setScene(scene);

		NewConditionController controller = loader.getController();
		
		controller.loadLambda(okLambda);
		controller.loadEntryCondition(entry);

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

		newConditionStage.showAndWait();
	}

	/**
	 * Used to link the Controller with the Stage that contains the Event Editor
	 * @param eventEditorStage
	 */
	public void setStage(Stage eventEditorStage) {
		myParentStage = eventEditorStage;
	}
}
