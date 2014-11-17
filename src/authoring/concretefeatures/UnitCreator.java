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

public class UnitCreator extends PopupWindow{
	
	public static final int HEIGHT = 400;
	public static final int WIDTH = 400;
	public static final String NAME = "Unit Creator";
	public static final String UNIT_NAME_LABEL = "Name";
	private LibraryView myLibrary;
	
	public UnitCreator(LibraryView library){
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
		
		Label nameLabel = new Label(UNIT_NAME_LABEL);
		nameLabel.setPadding(UIspecs.topRightPadding);
		TextField unitName = new TextField();
		names.getChildren().addAll(nameLabel, unitName);
		
		Label loadLabel = new Label("Unit image");
		Button loadImage = new Button("Load image");
		images.getChildren().addAll(loadLabel, loadImage);
		
		HBox modList = new ModulesList();
		
		Button goButton = new Button("Create new unit template");
		goButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				Hyperlink link = new Hyperlink(unitName.getText());
				myLibrary.addToLibrary(link, 0);
			}
			
		});
		box.getChildren().addAll(names, loadImage, modList, goButton);
	
		setScene(new Scene(box));
	}
}