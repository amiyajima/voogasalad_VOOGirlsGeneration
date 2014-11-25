package authoring.concretefeatures;

import gamedata.gamecomponents.Patch;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.awt.geom.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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


/**
 * GUI element used to create new Patch objects and add them to the library. Allows users
 * to specify the name and image of the patch.
 * 
 * @author Mike Zhu
 */
public class TerrainCreator extends PopupWindow {

    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final String NAME = "Terrain Creator";
    private static final String TERRAIN_NAME_LABEL = "Name";
    //private static final String IMAGE_LABEL = "Terrain image";
    private static final String LOAD_IMAGE_LABEL = "Load Terrain Image";
    private static final String TEMPLATE_LABEL = "Create new terrain template";
    private static final String DELETE = "Delete";
    private static final String EDIT = "Edit";
    private LibraryView myLibrary;
    
    private static final Insets MARGINS = new Insets(20, WIDTH/5, 20, WIDTH/5 - 10);
    private static final Insets LABEL_MARGINS = new Insets(10, 20, 10, 20);
    private static final String LABEL_CSS = "-fx-font-size: 25pt;";
    private static final String BUTTON_CSS = "-fx-padding: 10;";
    private static final String DEFAULT_IMAGE = "/resources/images/default_image.png";

    private String myName; 
    private String myImageLocation;
    private Point2D myLoc;

    /**
     * Constructor that sets the dimensions of the TerrainCreator GUI component
     * and initializes it.
     * 
     * @param library : Library to which terrain will be added.
     */

    public TerrainCreator (LibraryView library) {
        myLibrary = library;

        myName = "";
        myImageLocation = "";
        myLoc = new Point2D.Double(0, 0);

        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        initialize();
    }

    @Override
    protected void initialize () {

        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);

        VBox box = new VBox();
        box.getStylesheets().add(STYLESHEET);
        box.getStyleClass().add("vbox");
        box.setPadding(MARGINS);
        box.setSpacing(10);
        
        Label label = new Label(NAME);
        label.setStyle(LABEL_CSS);
        label.setPadding(UIspecs.topBottomPadding);
        
        HBox labelBox = new HBox();
        //labelBox.setPadding(LABEL_MARGINS);
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

        ImageView icon = new ImageView();
        //icon.setTranslateY(-7.5);
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        icon.setImage(new Image(getClass().getResourceAsStream(DEFAULT_IMAGE)));
        //Label loadLabel = new Label(IMAGE_LABEL);
        //loadLabel.setPadding(UIspecs.topRightPadding);
        Button loadImage = new Button(LOAD_IMAGE_LABEL);
        loadImage.setStyle(BUTTON_CSS);
        loadImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent click) {
                FileChooser fileChoice = new FileChooser();
                fileChoice.getExtensionFilters().add(
                                                     new ExtensionFilter("PNG Files", "*.png",
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

        Button goButton = new Button(TEMPLATE_LABEL);
        goButton.setStyle(BUTTON_CSS);
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

                Button editButton = new Button(EDIT);
                editButton.setStyle(BUTTON_CSS);
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle (ActionEvent e) {
                        PopupWindow p = new TerrainEditor(terrain);
                        p.show();
                    }
                });
                Button delButton = new Button(DELETE);
                delButton.setStyle(BUTTON_CSS);
                TerrainEntry entry = new TerrainEntry(terrain, icon, name, editButton, delButton);
                entry.setStyle("-fx-cursor: hand");
                entry.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent m) {
                        myLibrary.selectTerrain(terrain);
                    }
                });

                delButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle (ActionEvent event) {
                        myLibrary.removePatch(entry);
                    }
                });
                myLibrary.addPatch(entry);
                close();
            }
        });
        box.getChildren().addAll(labelBox, names, images, goButton);
        setScene(new Scene(box));
    }
}
