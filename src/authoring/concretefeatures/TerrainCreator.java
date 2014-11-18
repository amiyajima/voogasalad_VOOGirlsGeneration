package authoring.concretefeatures;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;
import authoring_environment.UIspecs;

public class TerrainCreator extends PopupWindow{
	
	// TO DO: These numbers and strings should be stored in an external file.
	// TO DO: UnitCreator and TerrainCreator should inherit a Creator superclass.
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Terrain Creator";
	private final String TERRAIN_NAME_LABEL = "Name";
	private final String IMAGE_LABEL = "Terrain image";
	private final String LOAD_IMAGE_LABEL = "Load image";
	private final String TEMPLATE_LABEL = "Create new terrain template";
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
	    box.setPadding(UIspecs.allPadding);
	    box.setSpacing(5);
		
		HBox names = new HBox();
		HBox images = new HBox();
		
		Label nameLabel = new Label(TERRAIN_NAME_LABEL);
	    nameLabel.setPadding(UIspecs.topRightPadding);
		TextField terrainName = new TextField();
		names.getChildren().addAll(nameLabel, terrainName);
		
		Label loadLabel = new Label(IMAGE_LABEL);
		Button loadImage = new Button(LOAD_IMAGE_LABEL);
		// TO DO: Support loading and saving terrain images.
		
		images.getChildren().addAll(loadLabel, loadImage);
		
		HBox modList = new ModulesList();
		
		Button goButton = new Button(TEMPLATE_LABEL);
		goButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				Hyperlink link = new Hyperlink(terrainName.getText());
				link.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent e){
						System.out.println("Fear not, this works!");
					}
				});
				myLibrary.addToLibrary(link, 1);
			}
		});
		box.getChildren().addAll(names, loadImage, modList, goButton);
	
		setScene(new Scene(box));
	}
}