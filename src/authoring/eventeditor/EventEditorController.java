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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EventEditorController implements Initializable {

	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private static final String NAME_PROMPT = "New Event name:";
	
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
	private Button saveEvents;
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
	
	private Stage newConditionStage;
	
	private List<Event> myEvents;
	private List<Condition> myConditions;
	private List<GlobalAction> myActions;
	private Stage myStage;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		//Makes it so that the right-hand Editor updates with respect to the selected
		//Event on the left side
		eventsListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedEvent) -> showEventInEditor(selectedEvent));
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
		VBox root = new VBox();
		root.getStylesheets().add(STYLESHEET);
		
		Stage newEventStage  = new Stage();
		newEventStage.setTitle("New Event");
		newEventStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		newEventStage.setScene(scene);

		Label nameLabel = new Label(NAME_PROMPT);
		TextField eventName = new TextField();
		eventName.setPromptText("Enter Event name");
		
		Button doneButton = new Button("Done");
		doneButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent click) {
				myEvents.add(new Event(eventName.getText()));
				newEventStage.close();
			}
		});
		
		root.getChildren().addAll(nameLabel, eventName, doneButton);
		newEventStage.showAndWait();
	}
	
	@FXML
	private void handleSaveEvents(){
		Stage stage = (Stage) saveEvents.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void handleDelEvent(){
		int delIdx = eventsListView.getSelectionModel().getSelectedIndex();
		conditionsListView.getItems().remove(delIdx);
	}

	/**
	 * When user selects an Event from the left-hand list, it populates the Conditions
	 * and GlobalActions lists on the right-hand side. Also enables all Buttons allowing
	 * interaction with those objects.
	 * @param event
	 */
	private void showEventInEditor(Event event){
		myConditions = event.getConditions();
		myActions = event.getGlobalActions();

		conditionsListView.setItems((ObservableList<Condition>) myConditions);
		actionsListView.setItems((ObservableList<GlobalAction>) myActions);
	
		newCondition.setDisable(false);
		editCondition.setDisable(false);
		delCondition.setDisable(false);
		delAction.setDisable(false);
		editAction.setDisable(false);
		newAction.setDisable(false);
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

		Stage newActionStage  = new Stage();
		newActionStage.setTitle("New Action");
		newActionStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		newActionStage.setScene(scene);

		newActionStage.showAndWait();
	}

	/**
	 * Loads a Level's Events into the editor. We pass the entire collection into this class
	 * because Editor must be able to add/remove from the list and modify individual Events
	 * 
	 * @param myEvents - an ObservableList to interface with the ListView
	 */
	public void loadEvents(ObservableList<Event> events) {
		myEvents = events;
		eventsListView.setItems(events);
	}

}
