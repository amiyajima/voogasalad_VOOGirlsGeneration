package authoring.concretefeatures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.CreatorPane;
import authoring.abstractfeatures.PopupWindow;

public class UnitCreator extends PopupWindow{
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = 600;
	public static final String NAME = "Create new unit template";
	
	public UnitCreator(){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		initialize();
	}
	
	@Override
	protected void initialize(){
		VBox box = new VBox();
		
		HBox names = new HBox();
		HBox images = new HBox();
		
		Label nameLabel = new Label("Name");
		TextField unitName = new TextField();
		names.getChildren().addAll(nameLabel, unitName);
		
		Label loadLabel = new Label("Unit image");
		Button loadImage = new Button("Load image");
		images.getChildren().addAll(loadLabel, loadImage);
		
		VBox modList = new ModulesList();
		
		Button goButton = new Button("Create new unit template");
		goButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				System.out.println(unitName.getText());
			}
			
		});
		box.getChildren().addAll(names, loadImage, modList, goButton);
	
		setScene(new Scene(box));
	}
}
