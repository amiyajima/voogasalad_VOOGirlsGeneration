package authoring.concretefeatures;

import gamedata.action.Action;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import gameengine.movement.Movement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.geom.Point2D;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;
import authoring_environment.UIspecs;

/**
 * GUI element that allows users to create new Piece templates and add them to
 * the Library. User defines unit name, image, and actions. Actions define a
 * units behavior and ultimately make the unit what it is.
 * 
 * @author Mike Zhu
 */
public class UnitCreator extends PopupWindow {
	
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Unit Creator";
	private final String UNIT_NAME_LABEL = "Name";
	private final String IMAGE_LABEL = "Unit image";
	private final String LOAD_IMAGE_LABEL = "Load image";
	private final String TEMPLATE_LABEL = "Create new unit template";
	private final String DELETE = "Delete";
	private final String EDIT = "Edit";
	private LibraryView myLibrary;
	
	private int myTypeID;
	private int myUniqueID;
	private int myPlayerID;
	private String myImageLocation;

	private Point2D myLoc;
	private Stats myStats;
	private List<Action> myActions;
	private List<Movement> myPath;
	private Inventory myInventory;

	/**
	 * Constructor that sets the dimensions of the UnitCreator GUI component and
	 * initializes it.
	 * 
	 * @param library
	 *            : Library to which units will be added.
	 */
	public UnitCreator(LibraryView library) {
		myLibrary = library;
		
		myImageLocation = "";
		myPath = new ArrayList<Movement>();
		myActions = new ArrayList<Action>();
		myStats = new Stats();
		myLoc = new Point2D.Double(0, 0);
		myTypeID = 0;
		myUniqueID = 0;
		myPlayerID = 0;
		myInventory = new Inventory();

		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		initialize();
	}

	@Override
    protected void initialize () {
        VBox box = new VBox();
        box.getStylesheets().add(STYLESHEET);
        box.getStyleClass().add("vbox");
        box.setPadding(UIspecs.allPadding);
        box.setSpacing(5);
        
        HBox names = new HBox();
        HBox images = new HBox();

        Label nameLabel = new Label(UNIT_NAME_LABEL);
        nameLabel.setPadding(UIspecs.topRightPadding);
        TextField unitName = new TextField();
        names.getChildren().addAll(nameLabel, unitName);

        ImageView icon = new ImageView();
        Label loadLabel = new Label(IMAGE_LABEL);
        Button loadImage = new Button(LOAD_IMAGE_LABEL);
        loadImage.setOnAction(new EventHandler<ActionEvent>() {
        	// initSetRangeButton(rangeVBox, "Effect Range (Splashzone):",myEffectRange);
        	// @Jesse Finish this
        	// From Martin: You sure this code goes here, and not below Line 133?

            @Override
            public void handle (ActionEvent click) {
                FileChooser fileChoice = new FileChooser();
                fileChoice.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png"));
                File selectedFile = fileChoice.showOpenDialog(null);
                if (selectedFile != null) {
                    myImageLocation = selectedFile.toURI().toString();
                    Image image = new Image(myImageLocation);
                    icon.setImage(image);
                    icon.setFitHeight(40);
                    icon.setFitWidth(40);
                }
            }
        });
        images.getChildren().addAll(loadLabel, loadImage, icon);

        HBox modList = new ModulesList();

        Button goButton = new Button(TEMPLATE_LABEL);
        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                Piece unit = new Piece(myImageLocation, myPath, myActions, myStats,
                                       myLoc, myTypeID, myUniqueID, myPlayerID, myInventory);

                Label name = new Label(unitName.getText());
                name.setTranslateY(10);
                Button editButton = new Button(EDIT);
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle (ActionEvent e) {
                        PopupWindow p = new UnitEditor(unit);
                        p.show();
                    }
                });
                Button delButton = new Button(DELETE);

                UnitEntry entry = new UnitEntry(unit, icon, name, editButton, delButton);
                entry.setStyle("-fx-cursor: hand");
        		entry.setOnMouseClicked(new EventHandler<MouseEvent>(){
        			@Override
        			public void handle(MouseEvent m){
        				myLibrary.selectUnit(unit);
        			}
        		});
                
        		delButton.setOnAction(new EventHandler<ActionEvent>(){
        			@Override
        			public void handle(ActionEvent event) {
        				myLibrary.removePiece(entry);
        			}
        		});

                myLibrary.addPiece(entry);
                close();
            }
        });
        box.getChildren().addAll(names, images, modList, goButton);
        setScene(new Scene(box));
    }

	protected void initSetRangeButton(VBox rangeBox, String label, List<Point2D> range) {
		Label rangeLabel = new Label(label);
		Button setRange = new Button("Set Range...");
		setRange.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//PopupWindow actionRangeEditor = new RangeEditor(range, label);
				//actionRangeEditor.show();
				// TODO: set myRange in here somewhere (within RangeEditor?)
			}
		});
		rangeBox.getChildren().addAll(rangeLabel, setRange);
	}
}