package fxml_main;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.createedit.TerrainEditor;
import authoring.data.PatchTypeData;
import authoring_environment.ShapeGrid;
import authoring_environment.UIspecs;


public class PatchController extends GridComponentAbstCtrl<Patch> {

    private PatchTypeData myPatchTypes;

    private static final int WIDTH = 150;
    private static final String NAME = "Terrain Creator";
    private static final String CANCEL = "Cancel";
    private static final String TERRAIN_NAME_LABEL = "Name";
    private static final String LOAD_IMAGE_LABEL = "Load Terrain Image";
    private static final String TEMPLATE_LABEL = "Create new terrain template";
    private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";
    private static final String DEFAULT_IMAGE = "/resources/images/default_image.png";

    private String myName;
    private String myImageLocation;
    private Point2D myLoc;

    public PatchController (VBox vbox, ScrollPane propertiesSPane, ShapeGrid currGrid) {
        super(vbox, propertiesSPane, currGrid);
        myPatchTypes = new PatchTypeData();
    }

    @Override
    protected void initGlobalNewBtn (Button newBtn) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                VBox box = initPatchCreator();
                myPropertiesSPane.setContent(box);
            }
        });
    }

    @Override
    protected void initGlobalEditBtn (Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI EDIT BUTTONFORPATCH HI");
            }
        });
    }

    @Override
    protected void initGlobalDelBtn (Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI DELETE BUTTONFORPATCH HI");
            }
        });
    }

    @Override
    protected HBox makeEntryBox (Patch entry) {
        // TODO Auto-generated method stub
        HBox hb = new HBox();
        ImageView img = entry.getImageView();
        img.setFitHeight(40);
        img.setFitWidth(40);
        hb.getChildren().add(img);
        return hb;
    }

    @Override
    protected void initEntryEditBtn (Patch entry, Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            // TODO: need to clean code
            public void handle (ActionEvent e) {
                TerrainEditor editor = new TerrainEditor(entry);
                Button cancelBtn = new Button(CANCEL);
                initCancelBtn(cancelBtn);
                editor.addToBox(cancelBtn);
                Pane p = editor;
                myPropertiesSPane.setContent(p);
            }
        });
    }

    @Override
    protected void initEntryDelBtn (Patch entry, Button delBtn) {

        delBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent event) {
                HBox entryBox = myEntryMap.get(entry);
                myVBox.getChildren().remove(entryBox);
            }
        });

    }

    /**
     * initializes a VBox with contents that allows the user to
     * create a patch
     * 
     * @return VBox - with name field, load image, create buttons
     */
    private VBox initPatchCreator () {
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
        Button cancelButton = new Button(CANCEL);

        initImageLoader(images);
        initGoBtn(goButton, terrainName);
        initCancelBtn(cancelButton);

        box.getChildren().addAll(labelBox, names, images, goButton, cancelButton);
        return box;
    }

    private void initCancelBtn (Button cancelBtn) {
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myPropertiesSPane.setContent(null);
            }

        });
    }

    private void initGoBtn (Button goButton, TextField terrainName) {
        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myName = terrainName.getText();
                if (myImageLocation.equals("") || terrainName.getText().equals("")) {
                return;
                }

                myLoc = new Point2D.Double(0, 0);
                Patch terrain = new Patch(myName, myImageLocation, myLoc);
                myPatchTypes.add(terrain);
                Label name = new Label(terrainName.getText());
                name.setTranslateY(7.5);

                addEntry(terrain);
            }
        });
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
}