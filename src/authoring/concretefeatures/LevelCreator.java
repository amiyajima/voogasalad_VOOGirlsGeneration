package authoring.concretefeatures;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.AuthoringLevel;
import fxml_main.LevelController;

public class LevelCreator extends VBox {
	private AuthoringLevel myLevel;
	private LevelController myLevelController;
	private String myGridShape;
	
	public LevelCreator(AuthoringLevel level, LevelController levelController,
			String gridShape) {
		myLevel = level;
		myLevelController = levelController;
		myGridShape = gridShape;
		initialize();
	}
	
	private void initialize() {
		HBox idHBox = new HBox();
		Label idLabel = new Label("ID:");
		TextField idField = new TextField(myLevel.getId());
		idHBox.getChildren().addAll(idLabel, idField);
		
		HBox gridSizeHBox = new HBox();
		Label gridSizeLabel = new Label("Grid Size:");
		
		VBox rowVBox = new VBox();
		Label rowLabel = new Label("Rows");
		TextField rowField = new TextField("5");
		rowVBox.getChildren().addAll(rowLabel,rowField);
		VBox colVBox = new VBox();
		Label colLabel = new Label("Columns");
		TextField colField = new TextField("5");
		colVBox.getChildren().addAll(colLabel,colField);
		
		gridSizeHBox.getChildren().addAll(gridSizeLabel, rowVBox, colVBox);
		
		Button eventBtn = new Button("Add Global Events...");
		
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		HBox finalizeBtnsHBox = new HBox();
		finalizeBtnsHBox.getChildren().addAll(okBtn, cancelBtn);
		
//		okBtn.setOnAction(new EventHandler<ActionEvent>() {
//			
//		
//		});
		
		
		getChildren().addAll(idHBox, gridSizeHBox,
				eventBtn, finalizeBtnsHBox);

		
	}
	
	
	
}
