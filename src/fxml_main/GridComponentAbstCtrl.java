// This entire file is part of my masterpiece.
// Sandy Lee

package fxml_main;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring_environment.GUIGrid;


/**
 * 
 * Abstract class for grid component controller which initializes various boxes
 * and buttons for creating, editing and deleting grid components like patches, pieces and levels.
 * The controller is for the authoring environment fxml (left hand side titled pane),
 * where the user will be able to perform such creation and edition of components.
 * 
 * @author Sandy Lee, Jennie Ju
 * @param <T>
 *
 */
public abstract class GridComponentAbstCtrl<T> {
    protected VBox myVBox;
    protected ScrollPane myPropertiesSPane;
    protected GUIGridReference myGridReference;
    protected Map<T, HBox> myEntryMap;

    private static final String NEW_BUTTON_LABEL = "New";
    private static final String EDIT_BUTTON_LABEL = "Edit";
    private static final String DELETE_BUTTON_LABEL = "Delete";

    protected GridComponentAbstCtrl (VBox vbox, ScrollPane propertiesSPane,
                                     GUIGridReference gridRef) {
        myVBox = vbox;
        myPropertiesSPane = propertiesSPane;
        myGridReference = gridRef;
        myEntryMap = new HashMap<T, HBox>();
        initGlobalControls();
    }

    private void initGlobalControls () {
        HBox btnBox = new HBox();
        Button newBtn = new Button(NEW_BUTTON_LABEL);
        Button editBtn = new Button(EDIT_BUTTON_LABEL);
        Button delBtn = new Button(DELETE_BUTTON_LABEL);
        initGlobalNewBtn(newBtn);
        initGlobalEditBtn(editBtn);
        initGlobalDelBtn(delBtn);
        btnBox.getChildren().addAll(newBtn, editBtn, delBtn);
        myVBox.getChildren().addAll(btnBox, new Separator());
    }

    /**
     * Sets the on-click actions for the New component button.
     * @param newBtn - button allowing for creation of new components
     */
    protected abstract void initGlobalNewBtn (Button newBtn);

    /**
     * Sets the on-click actions to Edit INDIVIDUAL component button.
     * @param editBtn - button allowing editing of components on the grid
     */

    protected void initGlobalEditBtn (Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        globalEditPropertiesPane(clickedCoord(e));
                    }
                };
                initGridMouseEvent(clickHandler);
            }
        });
    }

    /**
     * Sets the on-click actions to Delete INDIVIDUAL component button.
     * @param delBtn - button allowing for deletion of previously created components
     */
    protected void initGlobalDelBtn (Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        Point2D.Double coor = clickedCoord(e);
                        removeEntry(coor);
                    }
                };
                initGridMouseEvent(clickHandler);
            }
        });
    }

    /**
     * Allows the newly created component to be shown on the left hand pane in authoring
     * EntryHolderBox needs to contain EntryCompleteBox for edit purposes in the future
     * 
     * @param entry - grid component
     */
    protected void addEntry (T entry) {
        HBox entryHolderBox = new HBox();
        HBox entryCompleteBox = makeCompleteEntryBox(entry);
        myEntryMap.put(entry, entryHolderBox);
        entryHolderBox.getChildren().add(entryCompleteBox);
        myVBox.getChildren().add(entryHolderBox);
    }

    /**
     * @param entry - grid component
     * @return HBox - contains all elements (name, image and the buttons) related to the component
     */
    protected HBox makeCompleteEntryBox (T entry) {
        HBox entryBox = makeEntryBox(entry);
        HBox entryCtrls = initEntryControls(entry);
        HBox entryCompleteBox = new HBox();
        entryCompleteBox.getChildren().addAll(entryCtrls, entryBox);
        return entryCompleteBox;
    }

    /**
     * @param entry - grid component
     * @return HBox - box that triggers mouse event and has associated component image and name
     */
    protected HBox makeEntryBox (T entry) {
        HBox hb = initEntryBox(entry);
        hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        Point2D.Double coor = clickedCoord(e);
                        placeEntry(entry, coor);
                    }
                };
                initGridMouseEvent(clickHandler);
            }
        });
        return hb;
    }

    /**
     * @param entry - grid component
     * @return HBox - contains edit/delete button for each newly created component (type)
     */
    protected HBox initEntryControls (T entry) {
        HBox btnBox = new HBox();
        Button editBtn = new Button(EDIT_BUTTON_LABEL);
        Button delBtn = new Button(DELETE_BUTTON_LABEL);
        initEntryEditBtn(entry, editBtn);
        initEntryDelBtn(entry, delBtn);
        btnBox.getChildren().addAll(editBtn, delBtn);

        return btnBox;
    }

    /**
     * Sets the on-click actions to Edit button which deletes all components of the same type
     * 
     * @param entry - grid component
     * @param editBtn
     */
    protected void initEntryEditBtn (T entry, Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                Consumer<T> okLambda = (T component) -> {
                    HBox entryBox = makeCompleteEntryBox(component);

                    HBox entryHolderBox = myEntryMap.get(entry);
                    entryHolderBox.getChildren().clear();
                    entryHolderBox.getChildren().add(entryBox);
                    myEntryMap.put(component, entryHolderBox);

                    replaceEntry(entry, component);
                };

                editPropertiesPane(okLambda, entry);
            }

        });
    }

    /**
     * finding clicked coordinate
     * @param e 
     * @return coord
     */
    private Point2D.Double clickedCoord (MouseEvent e) {
        GUIGrid grid = myGridReference.getGrid();
        return grid.findClickedCoordinate(e.getX(), e.getY());
    }

    /**
     * initializes mouse event for authoring grid
     * @param clickHandler
     */
    private void initGridMouseEvent (EventHandler<MouseEvent> clickHandler) {
        myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
        myGridReference.getGrid().paneSetOnMouseDragged(clickHandler);
    }

    /**
     * @param entry
     * @return HBox - basic entryBox with name Label and ImageView of the component
     */
    protected abstract HBox initEntryBox (T entry);

    /**
     * Editor to show up when editing a component
     * @param coor - coordinate of the component
     */
    protected abstract void globalEditPropertiesPane (Double coor);

    /**
     * removes the component whose coordinate is clicked on from the grid
     * @param coor - clicked coordinate
     */
    protected abstract void removeEntry (Double coor);

    /**
     * initializes each sub-entry box
     * 
     * @param entry - grid component
     * @return HBox - entry box with the name and image of the entry
     */
    protected HBox setBoxLayout (String string, ImageView img) {
        HBox hb = new HBox();
        Label name = new Label(string);
        name.setTranslateY(7.5);
        img.setFitHeight(40);
        img.setFitWidth(40);
        hb.getChildren().addAll(name, img);
        return hb;
    }
    /**
     * Editor to show up when deleting a component TYPE
     * @param okLamda -
     * @param entry - component
     */
    protected abstract void editPropertiesPane (Consumer<T> okLambda, T entry);

    /**
     * Sets the action to be performed when an entry is selected and clicked onto the grid for
     * placement
     * @param entry - grid component
     * @param coor - coordinate of an instance of entry to be placed on the grid
     */
    protected abstract void placeEntry (T entry, Double coor);

    /**
     * Sets the action to be performed when an entry is selected and clicked onto the grid for edit
     * @param entry
     * @param component
     */
    protected abstract void replaceEntry (T entry, T component);

    /**
     * Sets the on-click actions to Delete button which deletes all components of the same type
     * @param entry - grid component
     * @param delBtn
     */
    protected abstract void initEntryDelBtn (T entry, Button delBtn);

}
