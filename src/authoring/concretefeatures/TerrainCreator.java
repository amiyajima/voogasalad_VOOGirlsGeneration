package authoring.concretefeatures;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.SquarePatch;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.abstractfeatures.PopupWindow;
import authoring.data.PatchData;
import authoring_environment.LibraryView;
import authoring_environment.UIspecs;

/**
 * GUI element used to create new Patch objects and add them to the library. Allows users
 * to specify the name and image of the patch. 
 * 
 * @author Mike Zhu
 */
public class TerrainCreator extends PopupWindow {
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String TERRAIN = "Terrain";
	private final String NAME = "Terrain Creator";
	private final String TERRAIN_NAME_LABEL = "Name";
	private final String IMAGE_LABEL = "Terrain image";
	private final String LOAD_IMAGE_LABEL = "Load image";
	private final String TEMPLATE_LABEL = "Create new terrain template";
	private final String DELETE = "Delete";
	private LibraryView myLibrary;
	
	private PatchData myPatchData;
        private int myState;
        private int myID;
        private Point2D myLoc;
        private ImageView myImage;
        
	/**
	 * Constructor that sets the dimensions of the TerrainCreator GUI component
	 * and initializes it.
	 * 
	 * @param library : Library to which terrain will be added.
	 */
	public TerrainCreator(LibraryView library, PatchData patchData){
	    //maybe get rid of library?
		myLibrary = library;
		
		//set to some default values
		myPatchData = patchData;
		myState = 0;
		myID = 0;
		myLoc = new Point2D(0,0);
		myImage = new ImageView();
		
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
		
		String[] imageLocation = new String[1];
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
//					imageLocation[0] = selectedFile.toURI().toString();
					Image image = new Image(imageLocation[0]);
					icon.setImage(image);
					icon.setFitHeight(40);
					icon.setFitWidth(40);
				}
			}
		});
		
		images.getChildren().addAll(loadLabel, loadImage, icon);
		
		HBox modList = new ModulesList();
		
		Button create = new Button(TEMPLATE_LABEL);
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent click) {
				
				ImageView imageCopy = new ImageView();
				myImage = imageCopy;
				imageCopy.setImage(icon.getImage());
				imageCopy.setFitHeight(40);
				imageCopy.setFitWidth(40);
			
				Patch terrain = new SquarePatch(myState, myID, myImage, myLoc);
				Hyperlink link = new Hyperlink(terrainName.getText());
				link.setTranslateY(10);;
				link.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e){
						PopupWindow p = new TerrainEditor(terrain);
						p.show();
					}
				});
				Button delButton = new Button(DELETE);
				delButton.setLayoutY(5);
				HBox entry = new TerrainEntry(delButton, imageCopy, link, terrain);
				delButton.setOnAction(new EventHandler<ActionEvent>(){
        			@Override
        			public void handle(ActionEvent event) {
        				myLibrary.removeFromLibrary(entry, TERRAIN);
        			}
        		});
				myLibrary.addToLibrary(entry, TERRAIN);
				close();
			}
		});
		box.getChildren().addAll(names, images, modList, create);
	
		setScene(new Scene(box));
	}
}