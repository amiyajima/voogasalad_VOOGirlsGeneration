package authoring.actionslogic;

import gamedata.action.Action;
import gamedata.action.ConcreteAction;
import gamedata.events.Event;
import gamedata.gamecomponents.Piece;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ActionLogicController implements Initializable {
    
    @FXML
    private ListView<Action> actionsListView;
    
    @FXML
    private ListView<Piece> piecesListView;
    
    @FXML
    private Button save;
    
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
        //for testing
        actionsListView.getItems().addAll(new ConcreteAction("Attack", null, null, null, null));
        

    }

}
