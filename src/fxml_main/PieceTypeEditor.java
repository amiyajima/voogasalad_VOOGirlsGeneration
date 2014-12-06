package fxml_main;

import gamedata.action.Action;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import gameengine.movement.Movement;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.createedit.ModulesList;
import authoring.data.ActionData;
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
	private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
	private static final String LABEL_CSS = "-fx-font-size: 14pt;";

	private static final String DEFAULT_IMAGE_LOC = "/resources/images/default_image.png";
	private static final Point2D DEFAULT_LOC = new Point2D.Double(0,0);
	
	private String myEditorTitle;
	private Consumer<Piece> myOkLambda;
	
	private String myID;
	private String myName;
	private String myImageLocation;
	private Piece myPiece;
	
	private ActionData myAvailableActions;

	private int myPlayerID;
	private Stats myStats;
	private List<Action> myActions;
	private Movement myPath;
	private Inventory myInventory;
	
	/**
	 * Called when creating a new Piece
	 * @param pieceController
	 */
	public PieceTypeEditor(Consumer<Piece> okLambda) {
		myEditorTitle = CREATOR_TITLE;
		myID = "";
		myName = "";
		myImageLocation = DEFAULT_IMAGE_LOC;
		
//		TODO : myPath should not be null. It takes in the GUI Grid and
//				a list of relative locations that the unit can move to.
		myPath = null;
		myActions = new ArrayList<Action>();
		myStats = new Stats();
		myPlayerID = 0;
		myInventory = new Inventory();
		constructor(okLambda);
	}
	
	public PieceTypeEditor(Consumer<Piece> okLambda, Piece piece, ActionData actions) {
		myEditorTitle = EDITOR_TITLE;
		myAvailableActions = actions;
		myID = piece.getID();
		myName = piece.getName();
		myImageLocation = piece.getImageLocation();
		myPath = piece.getMovement();
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
	
	public void initialize(){
		
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
		
		Label idLabel = new Label(ID_LABEL);
		idLabel.setPadding(UIspecs.topRightPadding);
		TextField unitID = new TextField();
		unitID.setText(myID);
		if(!myID.equals("")){
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
		
		//ModulesList modList = new ModulesList(myAvailableActions.getActionNames(), FXCollections.observableArrayList());
		
		Button goButton = new Button(TEMPLATE_LABEL);
		initImageLoader(images);
		initGoBtn(goButton, unitID, unitName);

		box.getChildren().addAll(labelBox, ids, names, images, goButton);

		getChildren().add(box);
	}

	private void initImageLoader(HBox images) {
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
	
	private ImageView setImageView(String imageLocation) {
		if(myImageLocation.startsWith("/")){
			return new ImageView(new Image(getClass().getResourceAsStream(imageLocation)));
		}
		else{
			return new ImageView(new Image(imageLocation));
		}
	}
	
	private void initGoBtn(Button goButton, TextField unitID, TextField unitName) {
		goButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle (ActionEvent click) {
				 myID = unitID.getText();
				 myName = unitName.getText();
				 myPiece = new Piece(myID, myName, myImageLocation, myPath, myActions, 
						 myStats, DEFAULT_LOC, myPlayerID, myInventory);
				 myOkLambda.accept(myPiece);
			 }
		 });
	}

//	public void oldInitialize(){
//		VBox box = new VBox();
//		box.getStyleClass().add("vbox");
//		box.setPadding(MARGINS);
//		box.setSpacing(10);
//
//		HBox names = new HBox();
//		names.setPadding(UIspecs.allPadding);
//		names.setSpacing(10);
//		HBox images = new HBox();
//		images.setPadding(UIspecs.allPadding);
//		images.setSpacing(10);
//
//		Label nameLabel = new Label(UNIT_NAME_LABEL);
//		nameLabel.setPadding(UIspecs.topRightPadding);
//		TextField unitName = new TextField();
//		unitName.setMinWidth(WIDTH/2 + 20);
//		names.getChildren().addAll(nameLabel, unitName);
//
//		ImageView icon = new ImageView();
//		icon.setFitHeight(40);
//		icon.setFitWidth(40);
//		icon.setImage(new Image(getClass().getResourceAsStream(DEFAULT_IMAGE)));
//		Button loadImageButton = new Button(LOAD_IMAGE_LABEL);
//		loadImageButton.setOnAction(new EventHandler<ActionEvent>() {
//			// initSetRangeButton(rangeVBox, "Effect Range (Splashzone):",myEffectRange);
//			// @Jesse Finish this
//			// From Martin: You sure this code goes here, and not below the goButton ActionEvent?
//
//			@Override
//			public void handle (ActionEvent click) {
//				FileChooser fileChoice = new FileChooser();
//				fileChoice.getExtensionFilters().add(
//						new ExtensionFilter("Image Files", "*.png", "*.gif"));
//				File selectedFile = fileChoice.showOpenDialog(null);
//				if (selectedFile != null) {
//					myImageLocation = selectedFile.toURI().toString();
//					Image image = new Image(myImageLocation);
//					icon.setImage(image);
//				}
//			}
//		});
//		images.getChildren().addAll(icon, loadImageButton);
//
//		
//		Button goButton = new Button(TEMPLATE_LABEL);
//		goButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle (ActionEvent click) {
//				myName = unitName.getText();
//				if(myImageLocation.equals("") || myName.equals("")){
//					return;
//				}
//				myActions = addSelectedActions(modList.getSelectedActions());
//
//				Piece unit = new Piece(myID, myName, myImageLocation, myPath, myActions, myStats,
//						myLoc, myPlayerID, myInventory);
//				// myPieceTypes.add(unit);
//				Label name = new Label(unitName.getText());
//				name.setTranslateY(7.5);
//
//				Button editButton = new Button(EDIT);
//				editButton.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle (ActionEvent e) {
//						TitledPane p = new LibraryUnitEditor(unit, myAvailableActions);
//						// p.show();
//					}
//				});
//				Button delButton = new Button(DELETE);
//				UnitEntry entry = new UnitEntry(unit, icon, name, editButton, delButton);
//				entry.setStyle("-fx-cursor: hand");
//				entry.setOnMouseClicked(new EventHandler<MouseEvent>(){
//					@Override
//					public void handle(MouseEvent m){
//						// myLibrary.selectUnit(unit);
//					}
//				});
//
//				delButton.setOnAction(new EventHandler<ActionEvent>(){
//					@Override
//					public void handle(ActionEvent event) {
//						// myLibrary.removePiece(entry);
//					}
//				});
//				//myLibrary.addPiece(entry);
//				// myUnitListView.getChildren().add(entry);
//			}
//		});
//		box.getChildren().addAll(names, images, modList, goButton);
//		getChildren().add(box);
//	}
//
//	protected List<Action> addSelectedActions(List<String> selected){
//		List<Action> list = new ArrayList<>();
//		for(String s: selected){
//			list.add(myAvailableActions.getAction(s));
//		}
//		return list;
//	}
//
//	protected void initSetRangeButton(VBox rangeBox, String label, List<Point2D> range) {
//		Label rangeLabel = new Label(label);
//		Button setRange = new Button("Set Range...");
//		setRange.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				//PopupWindow actionRangeEditor = new RangeEditor(range, label);
//				//actionRangeEditor.show();
//				// TODO: set myRange in here somewhere (within RangeEditor?)
//			}
//		});
//		rangeBox.getChildren().addAll(rangeLabel, setRange);
//	}
}
