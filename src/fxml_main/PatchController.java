package fxml_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class PatchController extends IAuthoringController {

    public PatchController (VBox patchesBox, TabPane gridTabs, ScrollPane propertiesPane) {
        myVBox = patchesBox;
        myGridTabPane = gridTabs;
        myPropertiesSPane = propertiesPane;
        initControls();
    }

    @Override
    protected void initNewButton (Button newBtn) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI NEW BUTTONFORPATCH HI");
            }
        });
    }

    @Override
    protected void initEditButton (Button editBtn) {
    	editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI EDIT BUTTONFORPATCH HI");
            }
        });
    }

    @Override
    protected void initDeleteButton (Button delBtn) {
    	delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI DELETE BUTTONFORPATCH HI");
            }
        });
    }
}