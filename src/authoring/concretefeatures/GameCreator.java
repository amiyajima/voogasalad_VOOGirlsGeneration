package authoring.concretefeatures;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import authoring_environment.UIspecs;
import authoring_environment.WorkspaceView;


/**
 * Creates a new game. The user can set elements for each game.
 * (includes number of levels, grid size, grid type and number of players)
 * 
 * @author Sandy Lee
 */
public class GameCreator extends Stage {
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private static final int HEIGHT = 600;
    private static final int WIDTH = 400;
    private static final Insets MARGINS = new Insets(20, WIDTH/4, 20, WIDTH/4 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 25pt;";
    private static final String BUTTON_CSS = "-fx-padding: 10;";
    
    private static final String NAME = "Level Creator";
    private static final String DEFAULT_VALUES_BUTTON = "Load Default Values";
    private static final String GRID_HEIGHT_LABEL = "Number of Rows:";
    private static final String GRID_WIDTH_LABEL = "Number of Columns:";
    private static final String PLAYER_NUMBER_LABEL = "Number of Players:";
    private static final String NAME_LABEL = "Level name";
    private static final String TEMPLATE_LABEL = "Create";
    private static final String GRID_CHOICE_LABEL = "Grid Type: ";
    private static final String SQUARE_GRID = "Square Grid";
    private static final String HEXAGON_GRID = "Hexagon Grid";
    private static final String DEFAULT_LEVEL_NAME = "Level 1";
    private static final String DEFAULT_PLAYER_NUM = "3";
    private static final String DEFAULT_GRID_HEIGHT = "10";
    private static final String DEFAULT_GRID_WIDTH = "10";
    
    private List<String> gridTypes = new ArrayList<String>();
    private ComboBox<String> gridChoiceBox;
    private WorkspaceView myWorkspaceView;
    private VBox box = new VBox();
    
    /**
     * Constructor that sets the dimensions of the TerrainCreator GUI component
     * and initializes it.
     * 
     * @param library : Library to which terrain will be added.
     */

    public GameCreator (WorkspaceView wsView) {
        //Stage pop = new Stage();
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        
        gridTypes.add(SQUARE_GRID);
        gridTypes.add(HEXAGON_GRID);
        
        myWorkspaceView = wsView;
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);
        initializeVBoxContent();
        // these must happen after initialization
        root.setContent(box);
        setScene(scene);
        show();
    }

    protected void initializeVBoxContent() {
        Label label = new Label(NAME);
        label.setStyle(LABEL_CSS);
        label.setPadding(UIspecs.topBottomPadding);
        
        Button defaultValuesButton = new Button(DEFAULT_VALUES_BUTTON);
        defaultValuesButton.setStyle(BUTTON_CSS);
        defaultValuesButton.setMaxWidth(WIDTH/2);
                
        VBox heights = new VBox();
        VBox widths = new VBox();
        VBox name = new VBox();
        VBox players = new VBox();
        VBox grid = new VBox();
        
        box.setPadding(MARGINS);
        box.setSpacing(10);
        
        TextField levelName = initLabel(name, NAME_LABEL);
        TextField player = initLabel(players, PLAYER_NUMBER_LABEL);
        TextField gridHeight = initLabel(heights, GRID_HEIGHT_LABEL);
        TextField gridWidth = initLabel(widths, GRID_WIDTH_LABEL);
        
        levelName.setMaxWidth(WIDTH/2);
        player.setMaxWidth(WIDTH/2);
        gridHeight.setMaxWidth(WIDTH/2);
        gridWidth.setMaxWidth(WIDTH/2);
        
        // set grid type
        gridChoiceBox = new ComboBox<String>();
        gridChoiceBox.setMaxWidth(WIDTH/2);
        gridChoiceBox = initChoiceBox(grid, gridChoiceBox);
        
        defaultValuesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                levelName.setText(DEFAULT_LEVEL_NAME);
                player.setText(DEFAULT_PLAYER_NUM);
                gridHeight.setText(DEFAULT_GRID_HEIGHT);
                gridWidth.setText(DEFAULT_GRID_WIDTH);
                gridChoiceBox.setValue(SQUARE_GRID);
            }
        });

        Button create = new Button(TEMPLATE_LABEL);
        create.setStyle(BUTTON_CSS);
        create.setMaxWidth(WIDTH/2);
        create.setOnAction(event -> handleCreate(levelName.getText(),
                                                 gridChoiceBox.getValue(),
                                                 Integer.parseInt(gridHeight.getText()), 
                                                 Integer.parseInt(gridWidth.getText())));
        box.getChildren().addAll(label, name, grid, players, heights, widths, create, defaultValuesButton);
    }

    /**
     * When the user clicks the create button, a LevelEditor is created which creates the new Workspace tab.
     * Also, I add the level editor to my list to keep track so when data needs to be pulled, you can get 
     * the level data from each of my level editors
     * @param levelName
     * @param gridType
     * @param col
     * @param row
     */
    private void handleCreate (String levelName, String gridType, int col, int row) {
        new LevelEditor(myWorkspaceView, gridType, levelName, row, col);
        close();
    }

    public ComboBox<String> initChoiceBox (VBox box, ComboBox<String> choices) {
        Label choiceLabel = new Label(GRID_CHOICE_LABEL);
        choiceLabel.setPadding(UIspecs.topRightPadding);
        choices.getItems().addAll(gridTypes);
        choices.setPrefWidth(WIDTH/2);
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
