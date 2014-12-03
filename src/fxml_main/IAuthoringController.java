package fxml_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        Button newBtn = makeButton("New");
        Button editBtn = makeButton("Edit");
        Button delBtn = makeButton("Delete");
        initNewButton(newBtn);
        initEditButton(editBtn);
        initDeleteButton(delBtn);
        btnBox.getChildren().addAll(newBtn, editBtn, delBtn);
        myVBox.getChildren().add(btnBox);
    }

    protected Button makeButton (String buttonName) {
        Button btn = new Button(buttonName);
        return btn;
    }

    protected abstract void initNewButton (Button newBtn);

    protected abstract void initEditButton (Button editBtn);

    protected abstract void initDeleteButton (Button delBtn);

}
