package authoring.concretefeatures;

import java.io.File; 

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import gamedata.gamecomponents.Patch;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.UIspecs;

/**
 * @author Martin Tamayo, Sandy Lee
 * 
 *         GUI component for editing the properties of a given terrain.
 *         This can be accessed by clicking on the hyperlink of the
 *         terrain's name in the LibraryView of the game authoring
 *         environment.
 */
public class TerrainEditor extends PopupWindow {
	
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private final int HEIGHT = 150;
    private final int WIDTH = 400;
    private final String NAME = "Terrain Editor";
    private Patch myTerrain;
    private final String TERRAIN_NAME_LABEL = "Name";
    private final String LOAD_IMAGE_LABEL = "Load image";
    private final String IMAGE_LABEL = "Terrain image";
    private final String TEMPLATE_LABEL = "Change";

    private String myImageLocation;

    /**
     * Constructor that sets the dimensions of the TerrainEditor
     * GUI component and initializes it.
     * 
     * @param terrain : Patch class of the terrain to edit.
     */
    public TerrainEditor (Patch terrain) {
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        myTerrain = terrain;
        initialize();
    }

    protected void initialize () {
        
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);
        
        VBox box = new VBox();
        box.getStylesheets().add(STYLESHEET);
        box.getStyleClass().add("vbox");
        box.setPadding(UIspecs.allPadding);
        box.setSpacing(5);
        
        HBox names = new HBox();
        HBox images = new HBox();

        Label nameLabel = new Label(TERRAIN_NAME_LABEL);
        nameLabel.setPadding(UIspecs.topRightPadding);
        TextField terrainName = new TextField();
        names.getChildren().addAll(nameLabel, terrainName);

        ImageView icon = new ImageView();
        Label loadLabel = new Label(IMAGE_LABEL);
        Button loadImage = new Button(LOAD_IMAGE_LABEL);
        loadImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                FileChooser fileChoice = new FileChooser();
                fileChoice.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png"));
                File selectedFile = fileChoice.showOpenDialog(null);
                if (selectedFile != null) {
                    myImageLocation = selectedFile.toURI().toString();
                    Image image = new Image(myImageLocation);
                    icon.setImage(image);
                    icon.setFitHeight(40);
                    icon.setFitWidth(40);
                }
            }
        });
        images.getChildren().addAll(loadLabel, loadImage, icon);

        Button create = new Button(TEMPLATE_LABEL);
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {

                // change patches info... need access to patches made with previous info

            }
        });

        box.getChildren().addAll(names, images, create);
        setScene(new Scene(box));

    }
}
