package fxml_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;


public class PieceController extends IAuthoringController {

    public PieceController (VBox piecesBox, TabPane gridTabs, ScrollPane propertiesPane) {
        myVBox = piecesBox;
        myGridTabPane = gridTabs;
        myPropertiesSPane = propertiesPane;
        initControls();
    }

    @Override
    protected void initNewButton (Button newBtn) {
        // TODO Auto-generated method stub
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI NEW BUTTONFORPIECE HI");
            }
        });
    }

    @Override
    protected void initEditButton (Button editBtn) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void initDeleteButton (Button delBtn) {
        // TODO Auto-generated method stub
        
    }


}
