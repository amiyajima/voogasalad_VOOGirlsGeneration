package authoring.concretefeatures;

import gamedata.gamecomponents.Patch; 

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.geom.Point2D;

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
import authoring_environment.LibraryView;
import authoring_environment.UIspecs;


/**
 * GUI element used to create new Patch objects and add them to the library. Allows users
 * to specify the name and image of the patch.
 * 
 * @author Mike Zhu
 */
public class TerrainCreator extends PopupWindow {

    private final int HEIGHT = 150;
    private final int WIDTH = 400;
    private final String TERRAIN = "Terrain";
    private final String NAME = "Terrain Creator";
    private final String TERRAIN_NAME_LABEL = "Name";
    private final String IMAGE_LABEL = "Terrain image";
    private final String LOAD_IMAGE_LABEL = "Load image";
    private final String TEMPLATE_LABEL = "Create new terrain template";
    private final String DELETE = "Delete";
    private final String EDIT = "Edit";
    private LibraryView myLibrary;

    private int myState;
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

        myState = 0;
        myImageLocation = "";
        myLoc = new Point2D.Double(0, 0);

        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        initialize();
    }

    @Override
    protected void initialize () {
        VBox box = new VBox();
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

        Button goButton = new Button(TEMPLATE_LABEL);
        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
            	Patch terrain = new Patch(myState, myImageLocation, myLoc);

            	Label name = new Label(terrainName.getText());
                name.setTranslateY(10);
                Button editButton = new Button(EDIT);
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle (ActionEvent e) {
                        PopupWindow p = new TerrainEditor(terrain);
                        p.show();
                    }
                });
                Button delButton = new Button(DELETE);

                HBox entry = new TerrainEntry(terrain, icon, name, editButton, delButton);

                delButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle (ActionEvent event) {
                        myLibrary.removeFromLibrary(entry, TERRAIN);
                    }
                });
                myLibrary.addToLibrary(entry, TERRAIN);
                close();
            }
        });
        box.getChildren().addAll(names, images, goButton);
        setScene(new Scene(box));
    }
}
