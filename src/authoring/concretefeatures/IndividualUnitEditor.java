package authoring.concretefeatures;

import gamedata.gamecomponents.Piece;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring.datawrappers.GridPieceWrapper;
import authoring_environment.UIspecs;


/**
 * 
 * 
 * @author Sandy Lee
 **/
public class IndividualUnitEditor extends PopupWindow {

    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String NAME = "Piece Editor";
    private final String NAME_LABEL = "Name: ";
    private final String STAT_EDITOR_LABEL = "Stats Editor";
    private final String INVENTORY_EDITOR_LABEL = "Inventory Editor";
    private final String PLAYER_LABEL = "Player ID";
    private final String ROTATION_LABEL = "Image Rotation (degrees)";
    private GridPieceWrapper myUnitWrapper;
    private Piece myUnit;

    /**
     * Constructor that sets the dimensions of the TerrainCreator GUI component
     * and initializes it.
     * 
     * @param library : Library to which terrain will be added.
     */

    public IndividualUnitEditor () {
        // myUnitWrapper = pieceWrapper;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        initialize();
    }

    // after the piece class gets edited, wrapper class should be switched to piece class
    @Override
    protected void initialize () {
        VBox box = new VBox();
        box.setPadding(UIspecs.allPadding);
        box.setSpacing(5);

        HBox names = new HBox();
        HBox images = new HBox();
        HBox players = new HBox();
        HBox imageRotation = new HBox();
        HBox imageFlip = new HBox();

        // piece name display
        initNameField(names);

        // piece image display
        initImage(images);

        // playerID setting
        ChoiceBox<Integer> playersChoice = new ChoiceBox<Integer>();
        initPlayerID(players, playersChoice);

        // rotating image of an individual piece
        TextField rotationAmt = new TextField();
        initRot(imageRotation, rotationAmt);

        // flipping image
        CheckBox cb1 = new CheckBox("Horizontal Flip  ");
        CheckBox cb2 = new CheckBox("Vertical Flip");
        imageFlip.getChildren().addAll(cb1, cb2);

        // button that links to states editor, link to inventory editor
        Button statsEdit = new Button(STAT_EDITOR_LABEL);
        statsEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                // TODO: pops up stats editor screen
                // PopupWindow p = new StatesEditor();
                PopupWindow p = new GameCreator();
                p.show();
            }
        });
        
        Button inventoryEditor = new Button(INVENTORY_EDITOR_LABEL);
        statsEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                // TODO: pops up stats editor screen
                // PopupWindow p = new InventoryEditor();
                PopupWindow p = new GameCreator();
                p.show();
            }
        });

        // get the data from above and set them when OK is pressed
        Button create = new Button("OK");
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                int playerID = playersChoice.getValue();
                double rotate = 0;

                if (!rotationAmt.getText().equals("")) {
                    rotate = Double.parseDouble(rotationAmt.getText());
                }
                boolean hFlip = cb1.isSelected();
                boolean vFlip = cb2.isSelected();
                // TODO: myUnitWrapper.rewriteParameters(playerID, rotate, hFlip, vFlip);

                close();
            }
        });

        box.getChildren().addAll(names, images, players, imageRotation, imageFlip, statsEdit,
                                 create);
        setScene(new Scene(box));
    }

    /**
     * This method is to display name of the piece in the editor
     * @param names contain labels
     */
    private void initNameField (HBox names) {
        // TODO: pieceName should be really its name... needs to be changed later
        // String pieceName = Integer.toString(myUnitWrapper.getTypeID());
        String pieceName = "yea";
        Label nameLable = new Label(NAME_LABEL + pieceName);
        nameLable.setPadding(UIspecs.topRightPadding);
        names.getChildren().addAll(nameLable);
    }

    /**
     * This method is to display image of the piece
     * @param images contains imageview 
     */
    private void initImage (HBox images) {
        // TODO: ImageView pieceImage = myUnitWrapper.getImageView();
        ImageView pieceImage =
                new ImageView(new Image(getClass().getResourceAsStream("images/233.gif")));
        images.getChildren().addAll(pieceImage);
    }

    /**
     * This method is to allow users to choose player for individual units
     * @param players is an hbox
     * @param playersChoice contain playerID choices
     */
    private void initPlayerID (HBox players, ChoiceBox<Integer> playersChoice) {
        Label playersLable = new Label(PLAYER_LABEL);
        playersLable.setPadding(UIspecs.topRightPadding);
        // TODO: DEFAULT_NUMBER_PLAYERS -> # of players should be obtained from somewhere..
        int DEFAULT_NUMBER_PLAYERS = 5;
        for (int i = 1; i < DEFAULT_NUMBER_PLAYERS + 1; i++) {
            playersChoice.getItems().addAll(i);
        }
        playersChoice.getSelectionModel().selectFirst();
        players.getChildren().addAll(playersLable, playersChoice);
    }

    /**
     * This method is to allow users to rotate img of a unit
     * @param imageRotation is an hbox displayed in the editor
     * @param rotationAmt where the user types in amt to be rototated
     */
    private void initRot (HBox imageRotation, TextField rotationAmt) {
        Label rotationLable = new Label(ROTATION_LABEL);
        rotationLable.setPadding(UIspecs.topRightPadding);
        rotationAmt.setPromptText("Enter degrees");
        imageRotation.getChildren().addAll(rotationLable, rotationAmt);
    }
}
