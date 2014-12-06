package fxml_main;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring_environment.UIspecs;


/**
 * GUI element used to create new Patch objects and add them to the library. Allows users
 * to specify the name and image of the patch.
 * 
 * @author Sandy Lee
 */
public class newTerrainCreator extends Pane {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 150;
    private static final String NAME = "Terrain Creator";
    private static final String TERRAIN_NAME_LABEL = "Name";
    private static final String LOAD_IMAGE_LABEL = "Load Terrain Image";
    private static final String TEMPLATE_LABEL = "Create new terrain template";
    private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";
    private static final String DEFAULT_IMAGE = "/resources/images/default_image.png";
    private PatchController myPatchController;
    private String myName;
    private String myImageLocation;
    private Point2D myLoc;

    /**
     * Constructor that sets the dimensions of the TerrainCreator GUI component
     * and initializes it.
     * 
     * @param library : Library to which terrain will be added.
     */

    public newTerrainCreator (PatchController controller) {

        myName = "";
        myImageLocation = "";
        myLoc = new Point2D.Double(0, 0);
        myPatchController = controller;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        initialize();
    }

    protected void initialize () {

        VBox box = new VBox();
        box.setPadding(MARGINS);
        box.setSpacing(10);

        HBox labelBox = new HBox();
        Label eventsLabel = new Label(NAME);
        eventsLabel.setStyle(LABEL_CSS);
        labelBox.getChildren().add(eventsLabel);

        HBox names = new HBox();
        names.setPadding(UIspecs.allPadding);
        names.setSpacing(5);

        HBox images = new HBox();
        images.setPadding(UIspecs.allPadding);
        images.setSpacing(5);

        Label nameLabel = new Label(TERRAIN_NAME_LABEL);
        nameLabel.setPadding(UIspecs.topRightPadding);
        TextField terrainName = new TextField();
        names.getChildren().addAll(nameLabel, terrainName);

        Button goButton = new Button(TEMPLATE_LABEL);
        initImageLoader(images);
        initGoBtn(goButton, terrainName);

        box.getChildren().addAll(labelBox, names, images, goButton);

        getChildren().add(box);
    }

    /**
     * initializes necessary contents for the user to load an img
     * 
     * @param images - HBox
     */
    private void initImageLoader (HBox images) {
        ImageView icon = new ImageView();
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        icon.setImage(new Image(getClass().getResourceAsStream(DEFAULT_IMAGE)));
        Button loadImage = new Button(LOAD_IMAGE_LABEL);
        loadImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent click) {
                FileChooser fileChoice = new FileChooser();
                fileChoice.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png",
                                                                         "*.gif"));
                File selectedFile = fileChoice.showOpenDialog(null);
                if (selectedFile != null) {
                    myImageLocation = selectedFile.toURI().toString();
                    Image image = new Image(myImageLocation);
                    icon.setImage(image);
                }
            }
        });
        images.getChildren().addAll(icon, loadImage);
    }

    /**
     * initializes the go button
     * 
     * @param goButton
     * @param terrainName
     */
    private void initGoBtn (Button goButton, TextField terrainName) {
        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myName = terrainName.getText();
                if (myImageLocation.equals("") || terrainName.getText().equals("")) {
                return;
                }

                Patch terrain = new Patch(myName, myImageLocation, myLoc);
                Label name = new Label(terrainName.getText());
                name.setTranslateY(7.5);

                myPatchController.addEntry(terrain);
            }
        });
    }

}
