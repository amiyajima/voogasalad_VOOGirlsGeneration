package authoring.createedit;

import gamedata.gamecomponents.Patch;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
public class TerrainEditor extends Pane {

    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private final int HEIGHT = 150;
    private final int WIDTH = 400;
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

        HBox nameBox = new HBox();
        HBox imageBox = new HBox();

        // name
        TextField nameTf = nameField(nameBox, TERRAIN_NAME_LABEL);

        // loading image
        initImageLoader(imageBox, IMAGE_LABEL, LOAD_IMAGE_LABEL);

        Button create = new Button(TEMPLATE_LABEL);
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                String name = nameTf.getText();
                myTerrain.setMyName(name);
                myTerrain.setMyImage(myImageLocation);
            }
        });

        box.getChildren().addAll(nameBox, imageBox, create);
        getChildren().add(box);

    }

    /**
     * Initializes so that user can type in name in a textfield
     * 
     * @param box - HBox to put name label and textfield for name
     * @param labelName - name of the name label
     * @return textfield - user types in name
     */
    public TextField nameField (HBox box, String labelName) {
        Label nameLabel = new Label(labelName);
        nameLabel.setPadding(UIspecs.topRightPadding);
        TextField tf = new TextField(myTerrain.getName());
        box.getChildren().addAll(nameLabel, tf);
        return tf;
    }

    /**
     * Initializes so that the user can add load in image
     * (in order to change patch's image)
     * 
     * @param imageBox - HBox for image loader related elements
     * @param labelName - name for label
     * @param buttonName - name for button
     */
    public void initImageLoader (HBox imageBox, String labelName, String buttonName) {
        ImageView icon = new ImageView();
        Label loadLabel = new Label(labelName);
        Button loadImage = new Button(buttonName);
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
        imageBox.getChildren().addAll(loadLabel, loadImage, icon);
        
    }
}
