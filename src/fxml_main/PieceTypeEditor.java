package fxml_main;

import gamedata.action.Action;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import authoring.concretefeatures.StatsCreatorBox;
import authoring.concretefeatures.StatsTotalEditor;
import authoring.createedit.ModulesList;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.UIspecs;


/**
 * @author Mike Zhu, Jennie Ju
 *
 */
public class PieceTypeEditor extends Pane {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 150;
    private static final String CREATOR_TITLE = "Unit Creator";
    private static final String EDITOR_TITLE = "Unit Editor";
    private static final String ID_LABEL = "Unique ID";
    private static final String NAME_LABEL = "Name";
    private static final String LOADIMAGE_LABEL = "Load Unit Image";
    private static final String TEMPLATE_LABEL = "OK";
    private static final String ID_PROMPT = "Enter piece ID...";
    private static final String NAME_PROMPT = "Enter piece name...";
    private static final String STAT_CREATE_LABEL = "Add stat";
    private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";

    private static final String DEFAULT_IMAGE_LOC = "/resources/images/default_image.png";
    private static final Point2D DEFAULT_LOC = new Point2D.Double(0, 0);

    private String myEditorTitle;
    private Consumer<Piece> myOkLambda;

    private String myID;
    private String myName;
    private String myImageLocation;
    private Piece myPiece;

    private ActionData myAvailableActions;
    private Set<String> myIDSet;

    private int myPlayerID;
    private Stats myStats;
    private List<Action> myActions;
    private Inventory myInventory;

    /**
     * Called when creating a new Piece
     * 
     * @param pieceController
     */
    public PieceTypeEditor (Consumer<Piece> okLambda, PieceTypeData pieceTypes, ActionData actions) {
        myEditorTitle = CREATOR_TITLE;
        myIDSet = pieceTypes.getIdSet();
        myAvailableActions = actions;
        myID = "";
        myName = "";
        myImageLocation = DEFAULT_IMAGE_LOC;
        myActions = new ArrayList<Action>();
        myStats = new Stats();
        myPlayerID = 0;
        myInventory = new Inventory();
        constructor(okLambda);
    }

    public PieceTypeEditor (Consumer<Piece> okLambda, Piece piece, ActionData actions) {
        myEditorTitle = EDITOR_TITLE;
        myAvailableActions = actions;
        myID = piece.getID();
        myName = piece.getName();
        myImageLocation = piece.getImageLocation();
        myActions = piece.getActions();
        myStats = piece.getStats();
        myPlayerID = piece.getPlayerID();
        myInventory = piece.getInventory();
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

        HBox createStat = new HBox();
        createStat.setPadding(UIspecs.allPadding);
        createStat.setSpacing(5);

        HBox availableStats = new HBox();
        availableStats.setPadding(UIspecs.allPadding);
        availableStats.setSpacing(5);

        HBox images = new HBox();
        images.setPadding(UIspecs.allPadding);
        images.setSpacing(5);

        Button createStatButton = new Button(STAT_CREATE_LABEL);
        initStatButton(createStatButton);
        createStat.getChildren().addAll(createStatButton);

        TableView<Stats> myStats = new TableView<Stats>();
        availableStats.getChildren().addAll(myStats);

        Label idLabel = new Label(ID_LABEL);
        idLabel.setPadding(UIspecs.topRightPadding);
        TextField unitID = new TextField();
        unitID.setText(myID);
        if (!myID.equals("")) {
            myIDSet = new HashSet<String>();
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

        Button goButton = new Button(TEMPLATE_LABEL);
        initImageLoader(images);
        initGoBtn(goButton, unitID, unitName, modList);

        box.getChildren().addAll(labelBox, ids, names, createStat, availableStats,
                                 images, modList, goButton);

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
        Map<String, Double> sample = new HashMap<String, Double>();
        sample.put("sample", 5.0);
        Stats sampleStat = new Stats(sample);
        
        StatsTotalEditor editor = new StatsTotalEditor(myStats);
        editor.show();
    }

    private ModulesList initModList () {
        ObservableList<String> availableActions = myAvailableActions.getActionNames();
        ObservableList<String> addedActions = FXCollections.observableArrayList();
        // TODO : Actions need to be properly implemented before this can work.
        // for(Action action : myActions){
        // System.out.println(myActions.size());
        // availableActions.remove(action);
        // addedActions.add(action);
        // }
        return new ModulesList(availableActions, addedActions);
    }

    private void initImageLoader (HBox images) {
        ImageView icon = setImageView(myImageLocation);
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
                    Image image = new Image(myImageLocation);
                    icon.setImage(image);
                }
            }
        });
        images.getChildren().addAll(icon, loadImage);
    }

    private ImageView setImageView (String imageLocation) {
        if (myImageLocation.startsWith("/")) {
            return new ImageView(new Image(getClass().getResourceAsStream(imageLocation)));
        }
        else {
            return new ImageView(new Image(imageLocation));
        }
    }

    private void initGoBtn (Button goButton,
                            TextField unitID,
                            TextField unitName,
                            ModulesList modList) {
        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myID = unitID.getText();
                if (myIDSet.contains(myID)) {
                return;
                }
                myIDSet.add(myID);
                myName = unitName.getText();
                myActions = addSelectedActions(modList.getSelectedActions());
                myPiece = new Piece(myID, myName, myImageLocation, myActions,
                                    myStats, DEFAULT_LOC, myPlayerID, myInventory);
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

    // protected void initSetRangeButton(VBox rangeBox, String label, List<Point2D> range) {
    // Label rangeLabel = new Label(label);
    // Button setRange = new Button("Set Range...");
    // setRange.setOnAction(new EventHandler<ActionEvent>() {
    // @Override
    // public void handle(ActionEvent event) {
    // //PopupWindow actionRangeEditor = new RangeEditor(range, label);
    // //actionRangeEditor.show();
    // // TODO: set myRange in here somewhere (within RangeEditor?)
    // }
    // });
    // rangeBox.getChildren().addAll(rangeLabel, setRange);
    // }
}
