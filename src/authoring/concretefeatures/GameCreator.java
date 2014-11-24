package authoring.concretefeatures;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring.data.PatchData;
import authoring.data.PieceData;
import authoring_environment.JennieGrid;
import authoring_environment.SandyGrid;
import authoring_environment.UIspecs;
import authoring_environment.VoogaView;


/**
 * Creates a new game. The user can set elements for each game.
 * (includes number of levels, grid size, grid type and number of players)
 * 
 * @author Sandy Lee
 */
public class GameCreator extends PopupWindow {

    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String NAME = "Game Creator";
    private final String GRID_HEIGHT_LABEL = "Grid Length Level:";
    private final String GRID_WIDTH_LABEL = "Grid Width Level:";
    private final String PLAYER_NUMBER_LABEL = "Number of Players:";
    private final String TEMPLATE_LABEL = "Create";

    private ChoiceBox<String> gridChoiceBox;

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
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);

        VBox box = new VBox();
        box.setPadding(UIspecs.allPadding);
        box.setSpacing(5);

        VBox heights = new VBox();
        VBox widths = new VBox();
        VBox players = new VBox();
        VBox grid = new VBox();

        // choose # of player
        TextField player = initLabel(players, PLAYER_NUMBER_LABEL);
        
        //set grid
        TextField gridHeight = initLabel(heights, GRID_HEIGHT_LABEL + "1");
        TextField gridWidth = initLabel(widths, GRID_WIDTH_LABEL + "1");

        //set grid type
        gridChoiceBox = new ChoiceBox<String>();
        gridChoiceBox = initChoiceBox(grid, gridChoiceBox);
        
        Button create = new Button(TEMPLATE_LABEL);
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                //set grid with such dimensions
                int width = Integer.parseInt(gridWidth.getText());
                int height = Integer.parseInt(gridHeight.getText());
                
                //TODO: hard coded grid type
                if (gridChoiceBox.getValue().equals("Square Grid")){
                    new SandyGrid(width, height, 40, new PieceData(), new PatchData());
                }
                if (gridChoiceBox.getValue().equals("Hexagon Grid")){
                    new JennieGrid(width, height, 40, new PieceData(), new PatchData());
                }
                
                close();
            }
        });
        box.getChildren().addAll(grid, players, heights, widths, create);
        root.setContent(box);
        setScene(scene);
    }

    public ChoiceBox<String> initChoiceBox (VBox box, ChoiceBox<String> choices) {
        Label choiceLabel = new Label("Grid Type");
        choiceLabel.setPadding(UIspecs.topRightPadding);
        choices.getItems().addAll("Square Grid", "Hexagon Grid");
//        choices.getSelectionModel().selectFirst(); -> automatically chooses the first one
        box.getChildren().addAll(choiceLabel, choices);
     
        return choices;
    }

    public TextField initLabel (VBox box, String labelName) {
        Label l = new Label(labelName);
        l.setPadding(UIspecs.topRightPadding);
        TextField lTextField = new TextField();
        box.getChildren().addAll(l, lTextField);

        return lTextField;
    }
}
