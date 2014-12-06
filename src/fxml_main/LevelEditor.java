package fxml_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.AuthoringLevel;

public class LevelEditor extends VBox { 
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private AuthoringLevel myLevel;
	private LevelController myLevelController;

	public LevelEditor(AuthoringLevel level, LevelController levelController) {
		myLevel = level;
		myLevelController = levelController;
		initialize();
	}

	private void initialize() {
		this.getStylesheets().add(STYLESHEET);
		this.getStyleClass().addAll("vbox");
		this.setId("vbox-main");

		HBox idHBox = new HBox();
		Label idLabel = new Label("ID:");
		TextField idField = new TextField(myLevel.getId());
		idHBox.getChildren().addAll(idLabel, idField);

		VBox gridSizeVBox = new VBox();
		Label gridSizeLabel = new Label("Grid Size:");

		VBox rowVBox = new VBox();
		Label rowLabel = new Label("Rows");
		TextField rowField = new TextField("5");
		rowVBox.getChildren().addAll(rowLabel,rowField);
		VBox colVBox = new VBox();
		Label colLabel = new Label("Columns");
		TextField colField = new TextField("5");
		colVBox.getChildren().addAll(colLabel,colField);

		HBox gridSizeHBox = new HBox();
		gridSizeHBox.getChildren().addAll(rowVBox, colVBox);
		gridSizeVBox.getChildren().addAll(gridSizeLabel,gridSizeHBox);

		Button eventBtn = new Button("Add Global Events...");

		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		HBox finalizeBtnsHBox = new HBox();
		finalizeBtnsHBox.getChildren().addAll(okBtn, cancelBtn);

		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				
			}
		});


		getChildren().addAll(idHBox, new Separator(), gridSizeVBox,
				new Separator(), eventBtn, finalizeBtnsHBox);


	}

}
