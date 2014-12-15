// This entire file is part of my masterpiece.
// Sandy Lee

package fxml_main;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D.Double;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.PatchTypeData;


/**
 * Controller which allows the user to create/edit/delete patches in the
 * authoring environment.
 * 
 * @author Sandy Lee
 *
 */
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
                Consumer<Patch> okLambda = (Patch patch) -> {
                    myPatchTypes.add(patch);
                    addEntry(patch);
                };
                myPropertiesSPane.setContent(new PatchTypeEditor(okLambda, myPatchTypes));
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

    @Override
    protected void placeEntry (Patch entry, Double coor) {
        myGridReference.getGrid().addPatchAtLoc(entry, coor);  
    }

    @Override
    protected HBox initEntryBox (Patch entry) {
        return setBoxLayout(entry.getName(), entry.getImageView());
    }

    @Override
    protected void globalEditPropertiesPane (Double coor) {
        Patch patch = myGridReference.getGrid().getPatch(coor);
        myPropertiesSPane.setContent(new PatchViewer(patch));
    }

    @Override
    protected void removeEntry (Double coor) {
        myGridReference.getGrid().removePatchAtCoordinate(coor);
    }

    @Override
    protected void replaceEntry (Patch orig, Patch newP) {
        myPatchTypes.replace(orig, newP);   
    }

    @Override
    protected void editPropertiesPane (Consumer<Patch> okLambda, Patch entry) {
      myPropertiesSPane.setContent(new PatchTypeEditor(okLambda, entry));
    }
     
}
