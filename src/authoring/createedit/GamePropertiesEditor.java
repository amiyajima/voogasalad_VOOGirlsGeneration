package authoring.createedit;

import java.util.ResourceBundle;
import authoring.abstractfeatures.PopupWindow;
import authoring.data.GamePropertiesData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class GamePropertiesEditor extends PopupWindow {

    private static final String RESOURCES_PROPERTIES_GRID_SHAPE = "resources.properties/gridShape";
    private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";


    GamePropertiesData myGameProperties;

    private int myNumPlayer;
    private String myGridShape;
    private ResourceBundle gridShapeBundle;
    private ChoiceBox<String> myShapeChoice;

    public GamePropertiesEditor (GamePropertiesData gamePropertiesData) {
        setTitle("Game Properties Editor");
        centerOnScreen();
        myGameProperties = gamePropertiesData;
        myNumPlayer = myGameProperties.getNumPlayers();
        myGridShape = myGameProperties.getGridShape();
        initialize();
        show();
    }

    protected void initialize () {
        gridShapeBundle = ResourceBundle.getBundle(RESOURCES_PROPERTIES_GRID_SHAPE);
        VBox box = new VBox();
        Scene scene = new Scene(box, 300, 300);
        scene.getStylesheets().add(STYLESHEET);

        Label numPlayer = new Label("Number of Player:");
        TextField number = new TextField();
        number.setText(""+myNumPlayer);
        number.setMaxWidth(80);
        Label gridShape = new Label("Grid Shape:");
        myShapeChoice = new ChoiceBox<String>();
        myShapeChoice.getItems().addAll(gridShapeBundle.getString("Square"),
                                gridShapeBundle.getString("Hexagon"),
                                gridShapeBundle.getString("Circle"));
        
        myShapeChoice.getSelectionModel().select(myGridShape);
        myShapeChoice.show();
        
        Button select = new Button("Select");
        select.setOnAction(new selectHandler(this, number, myShapeChoice));

        box.getChildren().addAll(numPlayer, number, gridShape, myShapeChoice, select);

        this.setScene(scene);

    }

    private class selectHandler implements EventHandler<ActionEvent> {
        TextField numPlayer;
        ChoiceBox<String> gridShape;
        GamePropertiesEditor editorWindow;

        public selectHandler (GamePropertiesEditor current,
                              TextField number,
                              ChoiceBox<String> shape) {
            numPlayer = number;
            gridShape = shape;
            editorWindow = current;
        }

        @Override
        public void handle (ActionEvent event) {
            try {
                myNumPlayer = Integer.parseInt(numPlayer.getText());
                myGameProperties.setNumPlayers(myNumPlayer);
//                System.out.println(myNumPlayer);

            }
            catch (NumberFormatException e) {
                // System.out.println(myNumPlayer);
                System.out.println("Type in an integer please:) ");
            }
            myGridShape = gridShape.getValue().toString();
            myGameProperties.setGridShape(myGridShape);
//            System.out.println(myGridShape);

            editorWindow.close();
        }

    }

    public int getNumPlayer () {
        return myNumPlayer;
    }

    public String getGridShape () {
        return myGridShape;
    }
    
    public void disableChangingGridShape(){
    	myShapeChoice.getItems().clear();
    	myShapeChoice.getItems().add(myGridShape);
    	myShapeChoice.getSelectionModel().select(myGridShape);
    }
}
