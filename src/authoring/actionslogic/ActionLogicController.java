package authoring.actionslogic;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;


/**
 * Controller for Action Logic Chart in Authoring Environment
 *
 */
public class ActionLogicController implements Initializable {

    @FXML
    private ListView<String> actionsListView;
    @FXML
    private ChoiceBox<String> actorsChoiceBox;
    @FXML
    private VBox myReceiversVBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button getDataButton;

    private List<String> myPieceTypes;
    private List<String> myActionTypes;
    private Map<String, Map<String, List<String>>> myLogicMap =
            new HashMap<String, Map<String, List<String>>>();
    private List<CheckBox> myCBList = new ArrayList<CheckBox>();

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        myPieceTypes = new ArrayList<String>();
        actorsChoiceBox
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                             (observable, oldValue, selectedActor) -> updatePossibleReceivers(selectedActor));
    }

    private void updateLogicMap () {
        for (String actor : myLogicMap.keySet()) {
            if (!myPieceTypes.contains(actor)) {
                myLogicMap.remove(actor);
            }
        }
    }

    private void updatePossibleReceivers (String selectedActor) {
        List<String> myPosReceivers = getReceivers(myPieceTypes, selectedActor);
        myCBList.clear();
        myReceiversVBox.getChildren().clear();
        for (String p : myPosReceivers) {
            CheckBox receiverCB = new CheckBox(p);
            myCBList.add(receiverCB);
            myReceiversVBox.getChildren().add(receiverCB);
        }
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
    private void saveLogic () {
        String currAction = actionsListView.getSelectionModel().getSelectedItem();
        String currActor = actorsChoiceBox.getSelectionModel().getSelectedItem();
        Map<String, List<String>> actionReceiver = myLogicMap.get(currActor);
        List<String> receiverList = new ArrayList<String>();

        for (CheckBox cb : myCBList) {
            if (cb.isSelected()) {
                receiverList.add(cb.getText());
            }
        }
        if (actionReceiver == null) {
            actionReceiver = new HashMap<String, List<String>>();
        }
        actionReceiver.put(currAction, receiverList);
        myLogicMap.put(currActor, actionReceiver);
        System.out.println(myLogicMap);

    }

    /**
     * @return a map that contains actors as keys and maps that relates actions and receivers as
     *         values
     */
    public Map<String, Map<String, List<String>>> getActionLogic () {
        return myLogicMap;
    }

    public void getData (ActionData actionData, PieceTypeData ptData) {
        ActionData actData = actionData;
        PieceTypeData pieceData = ptData;
        myActionTypes = actData.getActionNames();
        myPieceTypes.addAll(pieceData.getIdSet());
    }

    @FXML
    private void loadData () {
        actorsChoiceBox.getItems().addAll(myPieceTypes);
        actionsListView.getItems().addAll(myActionTypes);
    }

}
