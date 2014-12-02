package fxml_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PieceController {
	private VBox myPiecesVBox;
	private TabPane myGridTabPane;
	private ScrollPane myPropertiesSPane;
	
	
	public PieceController(VBox piecesBox, TabPane gridTabs, ScrollPane propertiesPane) {
		myPiecesVBox = piecesBox;
		myGridTabPane = gridTabs;
		myPropertiesSPane = propertiesPane;
		
		initControls();
	}

	private void initControls() {
		initEditorButtons();
	}

	private void initEditorButtons() {
		HBox btnHBox = new HBox();
		Button newBtn = initNewButton();
		btnHBox.getChildren().add(newBtn);
		myPiecesVBox.getChildren().add(btnHBox);
	}
	
	private Button initNewButton() {
		Button newBtn = new Button("New");
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("HI NEW BUTTON HI");
			}
		});
		return newBtn;
	}
	
}
