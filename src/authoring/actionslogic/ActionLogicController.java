package authoring.actionslogic;

import gamedata.action.Action;
import gamedata.action.ConcreteAction;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;


public class ActionLogicController implements Initializable {

    @FXML
    private ListView<String> actionsListView;

    @FXML
    private ChoiceBox<String> actorsChoiceBox;

    @FXML
    private VBox myReceiversVBox;

    @FXML
    private Button saveButton;

    private List<String> myPieceTypes = new ArrayList<String>();
    private Map<String,Map> myLogicMap = new HashMap<String,Map>();

    @Override
    // TODO: [IMPORTANT] This constructor will need a List<String> or Set<String> that contains
    // names of Pieces as its argument
    // also, need a list of existing actions(or names of actions)
    public void initialize (URL location, ResourceBundle resources) {

        // for testing
        actionsListView.getItems().addAll("Attack");
        actionsListView.getItems().addAll("Heal");
        actionsListView.getItems().addAll("Run");
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
        List<String> myPosReceivers = getReceivers(myPieceTypes, selectedActor);
        ChoiceBox<String> cb = new ChoiceBox<String>();
        cb.getItems().addAll(myPosReceivers);
        myReceiversVBox.getChildren().add(cb);
//        for (String p : myPosReceivers) {
//            myReceiversVBox.getChildren().add(new CheckBox(p));
//        }
    }

    private List<String> getReceivers (List<String> myPieceTypes, String actor) {
        List<String> receivers = new ArrayList<String>();
        for (String p : myPieceTypes) {
            if (!p.equals(actor)) {
                receivers.add(p);
            }
        }
        return receivers;
    }
    
    @FXML
    private void saveLogic(){
        String currAction = actionsListView.getSelectionModel().getSelectedItem();
        System.out.println(currAction);
        String currActor = actorsChoiceBox.getSelectionModel().getSelectedItem();
        System.out.println(currActor);
        
    }

}
