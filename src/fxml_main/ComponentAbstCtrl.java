package fxml_main;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public abstract class ComponentAbstCtrl {
    protected VBox myVBox;
    protected ScrollPane myPropertiesSPane;

    protected ComponentAbstCtrl (VBox vbox,
                                 ScrollPane propertiesSPane) {
        myVBox = vbox;
        myPropertiesSPane = propertiesSPane;
    }

}
