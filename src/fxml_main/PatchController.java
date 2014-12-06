package fxml_main;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import authoring.createedit.TerrainEditor;
import authoring.data.PatchTypeData;
import authoring_environment.ShapeGrid;


public class PatchController extends GridComponentAbstCtrl<Patch> {

    private PatchTypeData myPatchTypes;

    private static final int WIDTH = 150;
    private static final String NAME = "Terrain Creator";
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
        Pane box = new newTerrainCreator(this);
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
//                VBox box = initPatchCreator();                
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
        myPatchTypes.add(entry);
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
                Pane p =  new TerrainEditor(entry);
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
}
