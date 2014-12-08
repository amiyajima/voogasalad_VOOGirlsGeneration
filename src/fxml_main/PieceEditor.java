package fxml_main;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import authoring.concretefeatures.StatsTotalEditor;
import authoring_environment.UIspecs;

/**
 * @author Martin Tamayo
 *
 */
public class PieceEditor extends Pane {
    private static final int HEIGHT = 150;
    private static final int WIDTH = 150;
    private static final String EDITOR_TITLE = "Individual Unit Editor";
    private static final String ID_LABEL = "Unique ID: ";
    private static final String NAME_LABEL = "Name: ";
    private static final String STAT_CREATE_LABEL = "Add stat";
    private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";

    private String myID;
    private String myName;
    private String myImageLocation;
    private Piece myPiece;
    private Stats myStats;

    public PieceEditor (Piece piece) {
        myID = piece.getID();
        myName = piece.toString();
        myStats = piece.getStats();
        myImageLocation = piece.getImageLocation();
        setHeight(HEIGHT);
        setWidth(WIDTH);
        initialize();
    }

    public void initialize () {

        VBox box = new VBox();
        box.setPadding(MARGINS);
        box.setSpacing(10);

        HBox labelBox = new HBox();
        Label eventsLabel = new Label(EDITOR_TITLE);
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

        Label idLabel = new Label(ID_LABEL + myID);
        idLabel.setPadding(UIspecs.topRightPadding);
        ids.getChildren().addAll(idLabel);

        Label nameLabel = new Label(NAME_LABEL + myName);
        nameLabel.setPadding(UIspecs.topRightPadding);
        names.getChildren().addAll(nameLabel);

        VBox modList = initPseudoModList();
        initImageDisplay(images);

        box.getChildren().addAll(labelBox, ids, names, createStat, images, modList);
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

    private VBox initPseudoModList () {
    	VBox pseudoModList = new VBox();
//        for(Action action : myPiece.getActions()){
//        	pseudoModList.getChildren().add(new Label(action.toString()));
//        }
        return pseudoModList;
    }

    private void initImageDisplay (HBox images) {
        ImageView icon = setImageView();
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        images.getChildren().addAll(icon);
    }
    
    private ImageView setImageView() {
        if (myImageLocation.startsWith("/")) {
            return new ImageView(new Image(getClass().getResourceAsStream(myImageLocation)));
        }
        else {
            return new ImageView(new Image(myImageLocation));
        }
    }
}
