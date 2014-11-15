package authoring.concretefeatures;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;

public class TerrainCreator extends PopupWindow{
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = 600;
	public static final String NAME = "Terrain Creator";
	private LibraryView myLibrary;
	
	public TerrainCreator(LibraryView library){
		myLibrary = library;
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
		TextField terrainName = new TextField();
		names.getChildren().addAll(nameLabel, terrainName);
		
		Label loadLabel = new Label("Terrain image");
		Button loadImage = new Button("Load image");
		images.getChildren().addAll(loadLabel, loadImage);
		
		VBox modList = new ModulesList();
		
		Button goButton = new Button("Create new terrain template");
		goButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				myLibrary.addToLibrary(terrainName.getText(), 1);
			}
			
		});
		box.getChildren().addAll(names, loadImage, modList, goButton);
	
		setScene(new Scene(box));
	}
}