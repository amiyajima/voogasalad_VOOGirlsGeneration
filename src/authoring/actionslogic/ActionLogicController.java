package authoring.actionslogic;

import gamedata.action.Action;
import gamedata.action.ConcreteAction;
import gamedata.events.Event;
import gamedata.gamecomponents.Piece;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.ReceiverEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;


public class ActionLogicController implements Initializable {

    @FXML
    private ListView<Action> actionsListView;

    @FXML
    private ChoiceBox<String> actorsChoiceBox;

    @FXML
    private VBox myReceiversVBox;

    @FXML
    private Button save;

    private List<String> myPieceTypes = new ArrayList<String>();

    @Override
    // TODO: [IMPORTANT] This constructor will need a List<String> or Set<String> that contains
    // names of Pieces as its argument
    // also, need a list of actions
    public void initialize (URL location, ResourceBundle resources) {

        // for testing
        actionsListView.getItems().addAll(new ConcreteAction("Attack", null, null, null, null));
        actionsListView.getItems().addAll(new ConcreteAction("Heal", null, null, null, null));
        actionsListView.getItems().addAll(new ConcreteAction("Run", null, null, null, null));
        myPieceTypes.add("Piece A");
        myPieceTypes.add("Piece B");
        myPieceTypes.add("Piece C");

        actorsChoiceBox.getItems().addAll(myPieceTypes);
        

        actorsChoiceBox
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                             (observable, oldValue, selectedActor) -> updatePossibleReceivers(selectedActor));
    }

    private void updatePossibleReceivers (String selectedActor) {
        // TODO Auto-generated method stub
        List<String> myPosReceivers = getReceivers(myPieceTypes, selectedActor);
        for (String p : myPosReceivers) {
            myReceiversVBox.getChildren().add(new CheckBox(p));
        }
    }

    


}
