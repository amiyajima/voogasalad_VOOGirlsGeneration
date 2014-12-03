package fxml_main;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public abstract class IAuthoringController {
    protected VBox myVBox;
    protected TabPane myGridTabPane;
    protected ScrollPane myPropertiesSPane;

    public IAuthoringController () {
    }

    protected void initControls () {
        initButtons();
    }

    protected void initButtons () {
        HBox btnBox = new HBox();
        Button newBtn = new Button("New");
        Button editBtn = new Button("Edit");
        Button delBtn = new Button("Delete");
        initNewButton(newBtn);
        initEditButton(editBtn);
        initDeleteButton(delBtn);
        btnBox.getChildren().addAll(newBtn, editBtn, delBtn);
        myVBox.getChildren().add(btnBox);
    }

    protected abstract void initNewButton (Button newBtn);

    protected abstract void initEditButton (Button editBtn);

    protected abstract void initDeleteButton (Button delBtn);

}
