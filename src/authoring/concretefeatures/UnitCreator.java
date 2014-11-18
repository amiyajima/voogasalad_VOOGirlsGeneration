package authoring.concretefeatures;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;
import authoring_environment.UIspecs;

public class UnitCreator extends PopupWindow {
	
	// TO DO: These numbers and strings should be stored in an external file.
	// TO DO: UnitCreator and TerrainCreator should inherit a Creator superclass.
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Unit Creator";
	private final String UNIT_NAME_LABEL = "Name";
	private final String IMAGE_LABEL = "Unit image";
	private final String LOAD_IMAGE_LABEL = "Load image";
	private final String TEMPLATE_LABEL = "Create new unit template";
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
		
		ImageView icon = new ImageView();
		Label loadLabel = new Label(IMAGE_LABEL);
		Button loadImage = new Button(LOAD_IMAGE_LABEL);
		loadImage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				FileChooser fileChoice = new FileChooser();
				fileChoice.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png"));
				File selectedFile = fileChoice.showOpenDialog(null);
				if(selectedFile != null){
					Image image = new Image(selectedFile.toURI().toString());
					icon.setImage(image);
					icon.setFitHeight(40);
					icon.setFitWidth(40);
				}
			}
		});
		
		images.getChildren().addAll(loadLabel, loadImage, icon);
		
		HBox modList = new ModulesList();
		
		Button goButton = new Button(TEMPLATE_LABEL);
		goButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent click) {
				ImageView copy = new ImageView();
				copy.setImage(icon.getImage());
				copy.setFitHeight(40);
				copy.setFitWidth(40);
				copy.setStyle("-fx-cursor: hand");
				copy.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					@SuppressWarnings("static-access")
					public void handle(MouseEvent m){
						myLibrary.currentlySelectedImage = copy;
					}
				});
				Hyperlink link = new Hyperlink(unitName.getText());
				link.setTranslateY(10);;
				link.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e){
						PopupWindow p = new UnitEditor();
						p.show();
					}
				});
				HBox entry = new HBox(copy, link);
				myLibrary.addToLibrary(entry, 0);
			}
		});
		box.getChildren().addAll(names, images, modList, goButton);
	
		setScene(new Scene(box));
	}
}