package authoring.concretefeatures;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.UIspecs;
import authoring_environment.VoogaView;


/**
 * GUI element used to create new Patch objects and add them to the library. Allows users
 * to specify the name and image of the patch.
 * NOT WORKING YET
 * @author Sandy Lee
 */
public class GameCreator extends PopupWindow {

    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String NAME = "Game Creator";
    private final String GRID_HEIGHT_LABEL = "Grid Length";
    private final String GRID_WIDTH_LABEL = "Grid Width";
    private final String TEMPLATE_LABEL = "Create new game";
    
    private final VoogaView myVooga = new VoogaView();

    /**
     * Constructor that sets the dimensions of the TerrainCreator GUI component
     * and initializes it.
     * 
     * @param library : Library to which terrain will be added.
     */

    public GameCreator () {
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        initialize();
    }

    @Override
    protected void initialize () {
        VBox box = new VBox();
        box.setPadding(UIspecs.allPadding);
        box.setSpacing(5);

        HBox heights = new HBox();
        HBox widths = new HBox();


        //repetitive code
        //add other elements for the gameee
        Label gridHeightLabel = new Label(GRID_HEIGHT_LABEL);
        gridHeightLabel .setPadding(UIspecs.topRightPadding);
        TextField gridHeight = new TextField();
        heights.getChildren().addAll(gridHeightLabel, gridHeight);

        Label gridWidthLabel = new Label(GRID_WIDTH_LABEL);
        gridWidthLabel.setPadding(UIspecs.topRightPadding);
        TextField gridWidth = new TextField();
        widths.getChildren().addAll(gridWidthLabel, gridWidth);

        
        Button create = new Button(TEMPLATE_LABEL);
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                //should set up grid with the entered length and width
                
                //add stuffff
                
                int width = Integer.parseInt(gridWidth.getText());
                int height = Integer.parseInt(gridHeight.getText());
                myVooga.setGrid(width, height);


                close();
            }
        });
        box.getChildren().addAll(heights, widths, create);
        setScene(new Scene(box));
    }
}
