package fxml_main;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.PatchTypeData;
import authoring_environment.GUIGrid;


public class PatchController extends GridComponentAbstCtrl<Patch> {

    private PatchTypeData myPatchTypes;

    /**
     * Instantiates a PatchController with the PatchTypeData
     */
    public PatchController (VBox vbox, ScrollPane propertiesSPane, GUIGridReference gridRef,
                            PatchTypeData patchTypes) {
        super(vbox, propertiesSPane, gridRef);
        myPatchTypes = patchTypes;
    }

    /**
     * Initializes the global New Button to add new patches
     */
    @Override
    protected void initGlobalNewBtn (Button newBtn) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                // Adds patchtype to data and makes an entry box
                Consumer<Patch> okLambda = (Patch patch) -> {
                    myPatchTypes.add(patch);
                    addEntry(patch);
                };
                myPropertiesSPane.setContent(new PatchTypeEditor(okLambda, myPatchTypes));
            }
        });
    }

    @Override
    protected void initGlobalEditBtn (Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                editBtn.setVisible(false);
            }
        });
    }

    /**
     * Initalizes the global delete button to remove instances
     * of patches from the current GUIGrid w/ mouse clicks
     */
    @Override
    protected void initGlobalDelBtn (Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                // Make a MouseEvent for clicking the grid
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        GUIGrid grid = myGridReference.getGrid();
                        Point2D coor = grid.findClickedCoordinate(e.getX(), e.getY());
                        grid.removePatchAtCoordinate(coor);
                    }
                };
                myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
                myGridReference.getGrid().paneSetOnMouseDragged(clickHandler);

            }
        });
    }

    @Override
    protected HBox makeEntryBox (Patch entry) {
        HBox hb = new HBox();
        Label name = new Label(entry.toString());
        name.setTranslateY(7.5);
        ImageView img = entry.getImageView();
        img.setFitHeight(40);
        img.setFitWidth(40);
        hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        GUIGrid grid = myGridReference.getGrid();
                        Point2D coor = grid.findClickedCoordinate(e.getX(), e.getY());
                        grid.addPatch(entry, coor);
                    }
                };
                myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
                myGridReference.getGrid().paneSetOnMouseDragged(clickHandler);
            }
        });
        hb.getChildren().addAll(img, name);
        return hb;
    }

    @Override
    protected void initEntryEditBtn (Patch entry, Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                Consumer<Patch> okLambda = (Patch patch) -> {
                    HBox entryBox = makeCompleteEntryBox(patch);

                    HBox entryHolderBox = myEntryMap.get(entry);
                    entryHolderBox.getChildren().clear();
                    entryHolderBox.getChildren().add(entryBox);
                    myEntryMap.put(patch, entryHolderBox);

                    myPatchTypes.replace(entry, patch);
                };
                myPropertiesSPane.setContent(new PatchTypeEditor(okLambda, entry));
            }
        });
    }

    @Override
    protected void initEntryDelBtn (Patch entry, Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myPatchTypes.remove(entry);
                myVBox.getChildren().remove(myEntryMap.get(entry));
            }
        });
    }
}
