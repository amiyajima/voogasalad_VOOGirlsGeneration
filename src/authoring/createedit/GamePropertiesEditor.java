// This entire file is part of my masterpiece.
// Minkwon Lee

package authoring.createedit;

import java.net.URL;
import java.util.ResourceBundle;
import authoring.data.GamePropertiesData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class GamePropertiesEditor implements Initializable {

    private static final String RESOURCES_PROPERTIES_GRID_SHAPE = "resources.properties/gridShape";
    GamePropertiesData myGameProperties;
    @FXML
    private TextField myNumPlayersField;
    @FXML
    private ChoiceBox<String> myGridShapeBox;
    @FXML
    private Button mySaveBtn;
    private int myNumPlayers;
    private ResourceBundle myGridShapeBundle;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        fillGridShapeBox();

    }

    private void fillGridShapeBox () {
        myGridShapeBundle = ResourceBundle.getBundle(RESOURCES_PROPERTIES_GRID_SHAPE);
        myGridShapeBox.getItems().addAll(myGridShapeBundle.getString("Square"),
                                         myGridShapeBundle.getString("Hexagon"),
                                         myGridShapeBundle.getString("Circle"));
    }

    @FXML
    private void saveInput () {
        myGameProperties.setNumPlayers(Integer.parseInt(myNumPlayersField.getText()));
        myGameProperties.setGridShape(myGridShapeBox.getValue().toString());
    }

    public void addDataContainer (GamePropertiesData d) {
        myGameProperties = d;
    }

    public int getNumPlayer () {
        return myNumPlayers;
    }

}
