package authoring.createedit;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring.data.GamePropertiesData;


public class GamePropertiesEditor extends PopupWindow {
	private static final int DEFAULT_NUMPLAYERS = 1;
	private static final String DEFAULT_GRIDSHAPE = "";
	private static final String RESOURCES_PROPERTIES_GRID_SHAPE = "resources.properties/gridShape";
	private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";

	private int myNumPlayers;
	private String myGridShape;
	private ResourceBundle gridShapeBundle;

	private TextField myNumPlayerField;
	private ChoiceBox<String> myShapeChoice;
	private Button myCreateBtn;

	public GamePropertiesEditor(Consumer<GamePropertiesData> c) {
		setTitle("Game Properties Creator");
		myNumPlayers = DEFAULT_NUMPLAYERS;
		myGridShape = DEFAULT_GRIDSHAPE;
		initialize();
		myCreateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myNumPlayers = Integer.parseInt(myNumPlayerField.getText());
				myGridShape = myShapeChoice.getValue();
				c.accept(new GamePropertiesData(myNumPlayers, myGridShape));
				close();
			}
		});
		show();
	}

	public GamePropertiesEditor (GamePropertiesData gamePropertiesData) {
		setTitle("Game Properties Viewer");
		myNumPlayers = gamePropertiesData.getNumPlayers();
		myGridShape = gamePropertiesData.getGridShape();
		initialize();
		myNumPlayerField.setDisable(true);
		myShapeChoice.setDisable(true);
		myCreateBtn.setVisible(false);
		show();
	}

	protected void initialize () {
		centerOnScreen();
		gridShapeBundle = ResourceBundle.getBundle(RESOURCES_PROPERTIES_GRID_SHAPE);
		Set<String> gridShapeKeys = gridShapeBundle.keySet();

		VBox box = new VBox();
		Scene scene = new Scene(box, 300, 300);
		scene.getStylesheets().add(STYLESHEET);

		Label numPlayer = new Label("Number of Players:");
		myNumPlayerField = new TextField();
		myNumPlayerField.setText(""+myNumPlayers);
		myNumPlayerField.setMaxWidth(80);
		Label gridShape = new Label("Grid Shape:");
		myShapeChoice = new ChoiceBox<String>();

		for (String key : gridShapeKeys) {
			myShapeChoice.getItems().add(gridShapeBundle.getString(key));
		}
		myShapeChoice.getSelectionModel().select(myGridShape);

		myCreateBtn = new Button("Create Game");

		box.getChildren().addAll(numPlayer, myNumPlayerField, gridShape, myShapeChoice, myCreateBtn);

		this.setScene(scene);
	}

//	private class selectHandler implements EventHandler<ActionEvent> {
//		TextField numPlayer;
//		ChoiceBox<String> gridShape;
//		GamePropertiesEditor editorWindow;
//
//		public selectHandler (GamePropertiesEditor current,
//				TextField number,
//				ChoiceBox<String> shape) {
//			numPlayer = number;
//			gridShape = shape;
//			editorWindow = current;
//		}
//
//		@Override
//		public void handle (ActionEvent event) {
//			try {
//				myNumPlayers = Integer.parseInt(numPlayer.getText());
//				myGameProperties.setNumPlayers(myNumPlayers);
//				//                System.out.println(myNumPlayer);
//
//			}
//			catch (NumberFormatException e) {
//				// System.out.println(myNumPlayer);
//				System.out.println("Type in an integer please :) ");
//			}
//			myGridShape = gridShape.getValue().toString();
//			myGameProperties.setGridShape(myGridShape);
//			//            System.out.println(myGridShape);
//
//			editorWindow.close();
//		}
//
//	}

	public int getNumPlayer () {
		return myNumPlayers;
	}

//	public String getGridShape () {
//		return myGridShape;
//	}
//
//	public void disableChangingGridShape(){
//		myShapeChoice.getItems().clear();
//		myShapeChoice.getItems().add(myGridShape);
//		myShapeChoice.getSelectionModel().select(myGridShape);
//	}
}
