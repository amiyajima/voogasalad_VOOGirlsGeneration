package authoring.createedit;

import gamedata.action.Action;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.awt.geom.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.concretefeatures.LibraryUnitEditor;
import authoring.concretefeatures.UnitEntry;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.UIspecs;

/**
 * GUI element that allows users to create new Piece templates and add them to
 * the Library. User defines unit name, image, and actions. Actions define a
 * units behavior and ultimately make the unit what it is.
 * 
 * @author Mike Zhu
 */
public class UnitCreator extends TitledPane {

	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private static final int HEIGHT = 400;
	private static final int WIDTH = 300;
	private static final String NAME = "Unit Creator";
	private static final String UNIT_NAME_LABEL = "Name: ";
	private static final String LOAD_IMAGE_LABEL = "Load Unit Image";
	private static final String TEMPLATE_LABEL = "Create new unit template";
	private static final String DELETE = "Delete";
	private static final String EDIT = "Edit";
	
	private VBox myUnitListView;
	private ActionData myAvailableActions;

	private static final Insets MARGINS = new Insets(10, WIDTH/8, 10, WIDTH/8 - 10);
	private static final Insets LABEL_MARGINS = new Insets(10, WIDTH/7, 10, WIDTH/7 - 10);
	private static final String LABEL_CSS = "-fx-font-size: 25pt;";
	private static final String DEFAULT_IMAGE = "/resources/images/default_image.png";

	private String myName;
	private int myPlayerID;
	private String myImageLocation;

	private Point2D myLoc;
	private Stats myStats;
	private List<Action> myActions;
	private List<Movement> myPath;
	private Inventory myInventory;

	private PieceTypeData myPieceTypes;

	public UnitCreator(ActionData actions, PieceTypeData pieceTypes, VBox unitListView) {
		myUnitListView = unitListView;
		
		myPieceTypes = pieceTypes;
		myAvailableActions = actions;
		myName = "";
		myImageLocation = "";
		myPath = new ArrayList<Movement>();
		myActions = new ArrayList<Action>();
		myStats = new Stats();
		myLoc = new Point2D.Double(0, 0);
		myPlayerID = 0;
		myInventory = new Inventory();

		setText(NAME);
		
		VBox box = new VBox();
		box.getStylesheets().add(STYLESHEET);
		box.getStyleClass().add("vbox");
		box.setPadding(MARGINS);
		box.setSpacing(10);

		HBox names = new HBox();
		names.setPadding(UIspecs.allPadding);
		names.setSpacing(10);
		HBox images = new HBox();
		images.setPadding(UIspecs.allPadding);
		images.setSpacing(10);

		Label nameLabel = new Label(UNIT_NAME_LABEL);
		nameLabel.setPadding(UIspecs.topRightPadding);
		TextField unitName = new TextField();
		unitName.setMinWidth(WIDTH/2 + 20);
		names.getChildren().addAll(nameLabel, unitName);

		ImageView icon = new ImageView();
		icon.setFitHeight(40);
		icon.setFitWidth(40);
		icon.setImage(new Image(getClass().getResourceAsStream(DEFAULT_IMAGE)));
		Button loadImageButton = new Button(LOAD_IMAGE_LABEL);
		loadImageButton.setOnAction(new EventHandler<ActionEvent>() {
			// initSetRangeButton(rangeVBox, "Effect Range (Splashzone):",myEffectRange);
			// @Jesse Finish this
			// From Martin: You sure this code goes here, and not below the goButton ActionEvent?

			@Override
			public void handle (ActionEvent click) {
				FileChooser fileChoice = new FileChooser();
				fileChoice.getExtensionFilters().add(
						new ExtensionFilter("Image Files", "*.png", "*.gif"));
				File selectedFile = fileChoice.showOpenDialog(null);
				if (selectedFile != null) {
					myImageLocation = selectedFile.toURI().toString();
					Image image = new Image(myImageLocation);
					icon.setImage(image);
				}
			}
		});
		images.getChildren().addAll(icon, loadImageButton);

		ModulesList modList = new ModulesList(myAvailableActions.getActionNames(), FXCollections.observableArrayList());

		Button goButton = new Button(TEMPLATE_LABEL);
		goButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				myName = unitName.getText();
				if(myImageLocation.equals("") || myName.equals("")){
					return;
				}
				myActions = addSelectedActions(modList.getSelectedActions());

				Piece unit = new Piece(myName, myImageLocation, myPath, myActions, myStats,
						myLoc, myPlayerID, myInventory);
				myPieceTypes.add(unit);
				Label name = new Label(unitName.getText());
				name.setTranslateY(7.5);

				Button editButton = new Button(EDIT);
				editButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle (ActionEvent e) {
						TitledPane p = new LibraryUnitEditor(unit, myAvailableActions);
						// p.show();
					}
				});
				Button delButton = new Button(DELETE);
				UnitEntry entry = new UnitEntry(unit, icon, name, editButton, delButton);
				entry.setStyle("-fx-cursor: hand");
				entry.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent m){
						// myLibrary.selectUnit(unit);
					}
				});

				delButton.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						// myLibrary.removePiece(entry);
					}
				});
				//myLibrary.addPiece(entry);
				myUnitListView.getChildren().add(entry);
			}
		});
		box.getChildren().addAll(names, images, modList, goButton);
		setContent(box);
	}
	
	protected List<Action> addSelectedActions(List<String> selected){
		List<Action> list = new ArrayList<>();
		for(String s: selected){
			list.add(myAvailableActions.getAction(s));
		}
		return list;
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