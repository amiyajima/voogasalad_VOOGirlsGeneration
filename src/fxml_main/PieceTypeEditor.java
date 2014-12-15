package fxml_main;

import gamedata.action.Action;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.concretefeatures.RangeEditor;
import authoring.concretefeatures.StatsTotalEditor;
import authoring.createedit.ModulesList;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.UIspecs;

/**
 * @author Mike Zhu, Jennie Ju, Martin Tamayo
 *
 */
public class PieceTypeEditor extends Pane {
    private static final int HEIGHT = 150;
    private static final int WIDTH = 150;
    private static final String CREATOR_TITLE = "Piece Creator";
    private static final String EDITOR_TITLE = "Piece Editor";
    private static final String ID_LABEL = "Unique ID";
    private static final String NAME_LABEL = "Name";
    private static final String LOADIMAGE_LABEL = "Load Piece Image";
    private static final String TEMPLATE_LABEL = "OK";
    private static final String ID_PROMPT = "Enter piece ID...";
    private static final String NAME_PROMPT = "Enter piece name...";
    private static final String STAT_CREATE_LABEL = "Add stat";
    private static final String MOVE_RANGE_LABEL = "Set Movement Range...";
    private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";

    private static final String DEFAULT_IMAGE_LOC = "/resources/images/default_image.png";
    private static final String IMAGE_LOC = "/resources/images";
    private static final Point2D.Double DEFAULT_LOC = new Point2D.Double(0, 0);

    private String myEditorTitle;
    private Consumer<Piece> myOkLambda;

    private String myID;
    private String myName;
    private String myImageLocation;
    private Piece myPiece;

    private String myGridShape;
    private ActionData myAvailableActions;
    private Set<String> myIDSet;

    private int myPlayerID;
    private Stats myStats;
    private Movement myMovement;
    private List<Action> myActions;
    private Inventory myInventory;
    private boolean myHasInventory;
    private boolean myIsItem;

    /**
     * Called when creating a new Piece
     * 
     * @param pieceController
     */
    public PieceTypeEditor (Consumer<Piece> okLambda, PieceTypeData pieceTypes,
                            ActionData actions, String gridShape) {
        myEditorTitle = CREATOR_TITLE;
        myGridShape = gridShape;
        myIDSet = pieceTypes.getIdSet();
        myAvailableActions = actions;
        myID = "";
        myName = "";
        myImageLocation = DEFAULT_IMAGE_LOC;
        myMovement = new Movement();
        myActions = new ArrayList<Action>();
        myStats = new Stats();
        myPlayerID = 1;
        myInventory = new Inventory();
        myHasInventory = false;
        myIsItem = false;
        constructor(okLambda);
    }

    public PieceTypeEditor (Consumer<Piece> okLambda, Piece piece,
                            ActionData actions, String gridShape) {
        myEditorTitle = EDITOR_TITLE;
        myGridShape = gridShape;
        myIDSet = new HashSet<String>();
        myAvailableActions = actions;
        myID = piece.getID();
        myName = piece.getName();
        myImageLocation = piece.getImageLocation();
        myMovement = piece.getMovement();
        myActions = piece.getOriginalActions();
        myStats = piece.getStats();
        myPlayerID = piece.getPlayerID();
        myInventory = piece.getInventory();
        myHasInventory = piece.hasInventory();
        myIsItem = piece.isItem();
        constructor(okLambda);
    }

    public void constructor (Consumer<Piece> okLambda) {
        myOkLambda = okLambda;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        initialize();
    }

    public void initialize () {

        VBox box = new VBox();
        box.setPadding(MARGINS);
        box.setSpacing(10);

        HBox labelBox = new HBox();
        Label eventsLabel = new Label(myEditorTitle);
        eventsLabel.setStyle(LABEL_CSS);
        labelBox.getChildren().add(eventsLabel);

        HBox ids = new HBox();
        ids.setPadding(UIspecs.allPadding);
        ids.setSpacing(5);

        HBox names = new HBox();
        names.setPadding(UIspecs.allPadding);
        names.setSpacing(5);

        HBox images = new HBox();
        images.setPadding(UIspecs.allPadding);
        images.setSpacing(5);

        HBox createStat = new HBox();
        createStat.setPadding(UIspecs.allPadding);
        createStat.setSpacing(5);

        HBox movements = new HBox();
        movements.setPadding(UIspecs.allPadding);
        movements.setSpacing(5);

        Button rangeEditorBtn = new Button(MOVE_RANGE_LABEL);
        initRangeEditorButton(rangeEditorBtn);
        movements.getChildren().addAll(rangeEditorBtn);

        Button createStatButton = new Button(STAT_CREATE_LABEL);
        initStatButton(createStatButton);
        createStat.getChildren().addAll(createStatButton);

        Label idLabel = new Label(ID_LABEL);
        idLabel.setPadding(UIspecs.topRightPadding);
        TextField unitID = new TextField();
        unitID.setText(myID);
        if (!myID.equals("")) {
            unitID.setDisable(true);
        }
        unitID.setPromptText(ID_PROMPT);
        ids.getChildren().addAll(idLabel, unitID);

        Label nameLabel = new Label(NAME_LABEL);
        nameLabel.setPadding(UIspecs.topRightPadding);
        TextField unitName = new TextField();
        unitName.setText(myName);
        unitName.setPromptText(NAME_PROMPT);
        names.getChildren().addAll(nameLabel, unitName);

        ModulesList modList = initModList();

        CheckBox cbInventory = new CheckBox("Has an inventory");
        cbInventory.setAllowIndeterminate(false);
        cbInventory.setSelected(myHasInventory);

        CheckBox cbItem = new CheckBox("Is an item");
        cbInventory.setAllowIndeterminate(false);
        cbItem.setSelected(myIsItem);

        Button goButton = new Button(TEMPLATE_LABEL);
        initImageLoader(images);
        initGoBtn(goButton, unitID, unitName, modList,
                  cbInventory, cbItem);

        box.getChildren().addAll(labelBox, ids, names, images, movements,
                                 createStat, modList, cbInventory, cbItem, goButton);
        getChildren().add(box);
    }

    private void initStatButton (Button createStatButton) {
        createStatButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                showStatEditorWindow();
            }
        });
    }

    private void showStatEditorWindow () {
        StatsTotalEditor editor = new StatsTotalEditor(myStats);
        editor.show();
    }

    private ModulesList initModList () {
        ObservableList<String> availableActions = myAvailableActions.getActionNames();
        ObservableList<String> addedActions = FXCollections.observableArrayList();
        for (Action action : myActions) {
            // availableActions.remove(action.getName());
            addedActions.add(action.getName());
        }
        return new ModulesList(availableActions, addedActions);
    }

    private void initImageLoader (HBox images) {
        ImageView icon = setImageView();
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        Button loadImage = new Button(LOADIMAGE_LABEL);
        loadImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent click) {
                FileChooser fileChoice = new FileChooser();
                fileChoice.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png",
                                                                         "*.gif"));
                File selectedFile = fileChoice.showOpenDialog(null);
                if (selectedFile != null) {

                    myImageLocation = selectedFile.toURI().toString();
                    System.out.println("PieceTypeEditor: image location = " + myImageLocation);
                    Image image = new Image(myImageLocation);
                    icon.setImage(image);

                }
            }
        });
        images.getChildren().addAll(icon, loadImage);
    }

    private void initRangeEditorButton (Button rangeEditorButton) {
        rangeEditorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                List<Point2D.Double> range = myMovement.getRelativeMoves();
                Consumer<List<Point2D.Double>> consumer = (List<Point2D.Double> rg) -> {
                    range.clear();
                    range.addAll(rg);
                };

                RangeEditor rEditor = new RangeEditor(range, consumer, myGridShape);
                rEditor.show();
            }
        });
    }

    private ImageView setImageView () {
        if (myImageLocation.startsWith("/")) {
            return new ImageView(new Image(getClass().getResourceAsStream(myImageLocation)));
        }
        else {
            return new ImageView(new Image(myImageLocation));
        }

    }

    private void initGoBtn (Button goButton,
                            TextField unitID,
                            TextField unitName,
                            ModulesList modList,
                            CheckBox cbInventory,
                            CheckBox cbItem) {
        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myID = unitID.getText();
                if (myIDSet.contains(myID) || (myID.equals(""))) {
                return;
                }
                if (!unitID.isDisabled()) {
                    myIDSet.add(myID);
                }
                myName = unitName.getText();
                myActions = addSelectedActions(modList.getSelectedActions());

                myHasInventory = cbInventory.isSelected();
                myIsItem = cbItem.isSelected();
                myPiece = new Piece(myID, myName, myImageLocation, myMovement, myActions,
                                    myStats, DEFAULT_LOC, myPlayerID, myHasInventory, myIsItem);
                System.out.println(myMovement.toString());
                myOkLambda.accept(myPiece);
            }
        });
    }

    protected List<Action> addSelectedActions (List<String> selected) {
        List<Action> list = new ArrayList<>();
        for (String s : selected) {
            list.add(myAvailableActions.getAction(s));
        }
        return list;
    }
}
