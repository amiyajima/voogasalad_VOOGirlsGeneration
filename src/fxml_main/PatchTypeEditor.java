package fxml_main;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
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
import authoring.data.PatchTypeData;
import authoring_environment.UIspecs;


/**
 * GUI element used to create new Patch objects and add them to the library. Allows users
 * to specify the name and image of the patch.
 * 
 * @author Sandy Lee
 */
public class PatchTypeEditor extends Pane {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 150;
    private static final String CREATOR_TITLE = "Patch Creator";
    private static final String EDITOR_TITLE = "Patch Editor";
    private static final String ID_LABEL = "Unique ID";
    private static final String NAME_LABEL = "Name";
    private static final String LOADIMAGE_LABEL = "Load Terrain Image";
    private static final String TEMPLATE_LABEL = "OK";
    private static final String ID_PROMPT = "Enter patch ID...";
    private static final String NAME_PROMPT = "Enter patch name...";
    private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";

    private static final String DEFAULT_IMAGE_LOC = "/resources/images/default_image.png";
    private static final Point2D.Double DEFAULT_LOC = new Point2D.Double(0, 0);

    private String myEditorTitle;
    private Consumer<Patch> myOkLambda;

    private Set<String> myIDSet;

    private String myID;
    private String myName;
    private String myImageLocation;
    private Patch myPatch;

    /**
     * Constructor that sets the dimensions of the TerrainCreator GUI component
     * and initializes it.
     * this is for patch CREATOR
     * 
     */
    public PatchTypeEditor (Consumer<Patch> okLambda, PatchTypeData patchTypes) {
        myEditorTitle = CREATOR_TITLE;
        myIDSet = patchTypes.getIdSet();
        myID = "";
        myName = "";
        myImageLocation = DEFAULT_IMAGE_LOC;
        constructor(okLambda);
    }

    /**
     * Constructor that sets the dimensions of the TerrainCreator GUI component
     * and initializes it.
     * this is for patch EDITOR
     */
    public PatchTypeEditor (Consumer<Patch> okLambda, Patch patch) {
        myEditorTitle = EDITOR_TITLE;
        myIDSet = new HashSet<String>();
        myID = patch.getID();
        myName = patch.getName();
        myImageLocation = patch.getImageLocation();
        myPatch = patch;
        constructor(okLambda);
    }

    public void constructor (Consumer<Patch> okLambda) {
        myOkLambda = okLambda;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        initialize();
    }

    protected void initialize () {

        VBox box = new VBox();
        box.setPadding(MARGINS);
        box.setSpacing(10);

        HBox labelBox = new HBox();
        Label eventsLabel = new Label(myEditorTitle);
        eventsLabel.setStyle(LABEL_CSS);
        labelBox.getChildren().add(eventsLabel);

        HBox ids = new HBox();
        ids.setPadding(UIspecs.allPadding);
        ids.setSpacing(5);

        HBox names = new HBox();
        names.setPadding(UIspecs.allPadding);
        names.setSpacing(5);

        HBox images = new HBox();
        images.setPadding(UIspecs.allPadding);
        images.setSpacing(5);

        Label idLabel = new Label(ID_LABEL);
        idLabel.setPadding(UIspecs.topRightPadding);
        TextField terrainID = new TextField();
        terrainID.setText(myID);
        if (!myID.equals("")) {
            terrainID.setDisable(true);
        }
        terrainID.setPromptText(ID_PROMPT);
        ids.getChildren().addAll(idLabel, terrainID);

        Label nameLabel = new Label(NAME_LABEL);
        nameLabel.setPadding(UIspecs.topRightPadding);
        TextField terrainName = new TextField();
        terrainName.setText(myName);
        terrainName.setPromptText(NAME_PROMPT);
        names.getChildren().addAll(nameLabel, terrainName);

        Button goButton = new Button(TEMPLATE_LABEL);
        initImageLoader(images);
        initGoBtn(goButton, terrainID, terrainName);

        box.getChildren().addAll(labelBox, ids, names, images, goButton);

        getChildren().add(box);
    }

    /**
     * initializes necessary contents for the user to load an img
     * 
     * @param images - HBox
     */
    private void initImageLoader (HBox images) {
        ImageView icon = setImageView();
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        Button loadImage = new Button(LOADIMAGE_LABEL);
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

    private ImageView setImageView () {
        if (myImageLocation.startsWith("/")) {
            return new ImageView(new Image(getClass().getResourceAsStream(myImageLocation)));
        }
        else {
            return new ImageView(new Image(myImageLocation));
        }
    }

    /**
     * initializes the go button
     * 
     * @param goButton
     * @param terrainName
     */
    private void initGoBtn (Button goButton, TextField terrainID, TextField terrainName) {
        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myID = terrainID.getText();
                if (myIDSet.contains(myID) || myID.equals("")) {
                	return;
                }
                if (!terrainID.isDisabled()) {
                    myIDSet.add(myID);
                }
                myName = terrainName.getText();
                myPatch = new Patch(myID, myName, myImageLocation, DEFAULT_LOC);
                myOkLambda.accept(myPatch);
            }
        });
    }
}
