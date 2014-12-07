package authoring.eventeditor;

import gamedata.events.Condition;
import gamedata.events.Event;
import gamedata.events.GlobalAction;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class EventEditorController implements Initializable {

    // Lists
    @FXML
    private ListView<Event> eventsListView;
    @FXML
    private ListView<Condition> conditionsListView;
    @FXML
    private ListView<GlobalAction> actionsListView;

    // Buttons
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

    @Override
    // This method is called by the FXMLLoader when initialization is complete
    public void initialize (URL fxmlFileLocation, ResourceBundle resources) {

        // Testing
        eventsListView.getItems()
                .addAll(new Event(new ArrayList<>(), new ArrayList<>(), "Testing"));

        // Makes it so that the right-hand Editor updates with respect to the selected
        // Event on the left side
        eventsListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                             (observable, oldValue, selectedEvent) -> showEventInEditor(selectedEvent));
    }

    @FXML
    private void showEventInEditor (Event event) {
        ObservableList<Condition> conditions = FXCollections.observableArrayList();
        ObservableList<GlobalAction> actions = FXCollections.observableArrayList();

        conditions.addAll(event.getConditions());
        actions.addAll(event.getGlobalActions());

        conditionsListView.setItems(conditions);
        actionsListView.setItems(actions);
    }

    @FXML
    private void handleNewCondition () throws IOException {
        showNewConditionWindow();
    }

    @FXML
    private void handleEditCondition () throws IOException {

    }

    @FXML
    private void handleDelCondition () {
        int delIdx = conditionsListView.getSelectionModel().getSelectedIndex();
        conditionsListView.getItems().remove(delIdx);
    }

    @FXML
    private void handleNewAction () throws IOException {
        showNewActionWindow();
    }

    @FXML
    private void handleEditAction () throws IOException {

    }

    @FXML
    private void handleDelAction () {
        int delIdx = actionsListView.getSelectionModel().getSelectedIndex();
        actionsListView.getItems().remove(delIdx);
    }

    private void showNewConditionWindow () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/authoring/eventeditor/NewCondition.fxml"));
        Parent root = loader.load();

        Stage newConditionStage = new Stage();
        newConditionStage.setTitle("New Condition");
        newConditionStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        newConditionStage.setScene(scene);

        NewConditionController controller = loader.getController();

        // controller.setEvent(event);

        newConditionStage.showAndWait();
    }

    private void showNewActionWindow () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/authoring/eventeditor/NewAction.fxml"));
        Parent root = loader.load();

        Stage newConditionStage = new Stage();
        newConditionStage.setTitle("New Action");
        newConditionStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        newConditionStage.setScene(scene);

        NewConditionController controller = loader.getController();

        newConditionStage.showAndWait();
    }
}
