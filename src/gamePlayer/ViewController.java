package gamePlayer;

import gamedata.JSON.JSONManager;

import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
// import com.leapmotion.leap.Controller;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import tests.JSONBobTester;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author
 *
 */
public class ViewController {

	public static final String GAMESPACE_FXML = "gameSpace.fxml";
	public static final String INITIALSCENE_FXML = "initialScene.fxml";
	public static final String SCOREBOARD_FXML = "scoreBoard.fxml";
	public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
	public static final String GAME_LOCATION = "/src/resources/json";

	public static final String POPUP_FXML = "popup.fxml";

	// public static final String ENGLISH = "English";
	// public static final String Chinese = "Chinese";

	// public static final String AUDIO_TEST = "/src/gamePlayer/audioTest.mp3";
	private static final String MUSIC = "/src/resources/music/Cut_Gee_VooGirls.mp3";
	public static final String CURSOR_ATTACK_TEST = "/gamePlayer/Cursor_attack.png";
	public static final String CURSOR_GLOVE_TEST = "/gamePlayer/pointer-glove.png";

	private ResourceBundle myLanguages;
	private Stage myStage;
	private BorderPane myGameSpace;
	private BorderPane myPopup;
	private VBox myInitialScene;
	private VBox myScoreBoard;
	private Scene scoreScene;
	private Scene myPopupScene;
	private Scene myScene;

	private Game myModel;
	private GameGrid myGrid;

	// private SampleListener myLeapListener;

	private Boolean keyControlOn;
	private KeyboardController myKeyboardController;

	// private Point2D myCurrentLocation;
	private Piece activePiece;
	private Action activeAction;

	// private MouseController myMouseController;

	private AudioClip myAudio;
	@FXML
	protected VBox statsPane;
	@FXML
	protected VBox controlPane;
	@FXML
	private MenuButton newGameButton;
	@FXML
	private Text gameName;
	@FXML
	private VBox scores;

	private Point2D currentClick;

	private IGridState gridState;

	private JSONManager myJSONManager;

	public ViewController(Stage s) {
		myStage = s;
		myInitialScene = new VBox();
		myGameSpace = new BorderPane();
		myScoreBoard = new VBox();
		myPopup = new BorderPane();
		myJSONManager = new JSONManager();
		// myLeapController = new Controller();

		loadFXML(GAMESPACE_FXML, myGameSpace);
		loadFXML(INITIALSCENE_FXML, myInitialScene);
		loadFXML(POPUP_FXML, myPopup);
		loadFXML(SCOREBOARD_FXML, myScoreBoard);

		scoreScene = new Scene(myScoreBoard);
		myPopupScene = new Scene(myPopup);

		myStage.setScene(new Scene(myInitialScene));

		try {
			newGame();
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {
		}

		myStage.show();

	}

	/**
	 * the method allows user to load the previously saved json representation
	 * of the game and uses JSON reader from Game Data to generate an instance
	 * of Game.
	 */

	@FXML
	protected void loadGame() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
		fc.setInitialDirectory(new File("src/resources/json"));
		File f = fc.showOpenDialog(myStage);

		// fc.setInitialDirectory(new File(System.getProperty("user.dir") +
		// "/src/resources"));
		// uses JSON reader to generate an instance of the game

		try {
			myModel = myJSONManager.readFromJSONFile(f.getPath());
			initializeGrid();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * the method to restart the game; it asks the use whether to save the
	 * current game
	 * 
	 */
	@FXML
	protected void restartGame() {

		// Stage stage = new Stage();
		//
		// stage.setScene(myPopupScene);
		// stage.show();
		// stage.setAlwaysOnTop(true);
		initializeGrid();
		statsPane.getChildren().clear();
		controlPane.getChildren().clear();

	}

	@FXML
	protected void exitGame() {
		myStage.close();
	}

	/**
	 * to save the current game (state and settings) to a json file which could
	 * be later loaded in
	 */
	@FXML
	protected void saveGame() {
		FileChooser fileChooser = new FileChooser();
		File f = fileChooser.showSaveDialog(myStage);
		String basePath = System.getProperty("user.dir");
		String absolutePath = f.getPath();
		String relativePath = "";
		if (absolutePath.startsWith(basePath)) {
			relativePath = absolutePath.substring(basePath.length() + 1);
		}
		myModel.getLevels().forEach(level -> level.deleteObserver(this.myGrid));
		myJSONManager.writeToJSON(myModel, f.getPath());

	}

	/**
	 * Initiates a Button for a Test Game
	 */
	@FXML
	private void testGame() {

		myScene = new Scene(myGameSpace);
		myStage.setScene(myScene);
		JSONBobTester JSBTester = new JSONBobTester();
		myModel = JSBTester.createNewGame();
		initializeGrid();
	}

	@FXML
	private void doSettings() {

	}

	/**
	 * loads the players and their scores of the current game; display the
	 * Highest score in the high score display at the bottom
	 */
	@FXML
	protected void showScore() {
		Stage stage = new Stage();
		stage.setScene(scoreScene);
		loadScores();
		stage.show();

	}

	@FXML
	protected void cancelPopup() {

	}

	/**
	 * Loads an FXML file
	 * 
	 * @param url
	 * @param n
	 */
	private void loadFXML(String url, Node n) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(n);
		try {
			fxmlLoader.load();

		} catch (IOException exception) {

			throw new RuntimeException(exception);
		}

	}

	/**
	 * Generates a drop down menu that allow user to choose a new Game to play.
	 * The Games are generated from the directory that stores all json files
	 * defined from authoring environment
	 * 
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	protected void newGame() throws UnsupportedAudioFileException, IOException,

	LineUnavailableException {

		List<File> games = getGames();

		games.forEach(file -> {
			MenuItem l = new MenuItem();
			l.setText(file.getName().substring(0, file.getName().length() - 5));
			l.getStyleClass().add("button");
			newGameButton.getItems().add(l);

		});

	}

	private void initializeGrid() {
		// myModel.play();

		myGrid = new SquareGameGrid(myModel.getCurrentLevel().getGrid()
				.getRow(), myModel.getCurrentLevel().getGrid().getColumn());

		myGameSpace.setCenter(myGrid);
		myGrid.setAlignment(Pos.CENTER);
		myGrid.populateGrid(
				myModel.getCurrentLevel().getGrid().getAllPatches(), myModel
						.getCurrentLevel().getGrid().getAllPieces());
		myModel.getLevels().forEach(level -> level.addObserver(this.myGrid));
		setOnClick();

		setGridState(new SelectState(this));
		getGrid().setOnMouseExited(event -> {
			changeCursor(CURSOR_GLOVE_TEST);
		});

		keyControlOn = false;
	}

	/**
	 * Loads the Score from a Player for Display
	 */
	protected void loadScores() {
		gameName.setText(gameName.getText() + myModel.toString());

		// TODO: add in scores
		// myModel.getPlayers().forEach(player-> scores.getChildren().
		// add(new Text(player.getID()+": ")));

	}

	/**
	 * The method to get all json files from the resources directory that stores
	 * all the games user has defined from the authoring environment
	 */
	private List<File> getGames() {

		List<File> gameList = new ArrayList<File>();

		File files = new File(System.getProperty("user.dir") + GAME_LOCATION);

		for (File f : files.listFiles()) {

			if (f.getName().endsWith(".json")) {

				gameList.add(f);
			}
		}
		return gameList;
	}

	/**
	 * Update the stats panel with stats from the selected piece
	 * 
	 * @param piece
	 */
	protected void updateStats(Piece piece) {

		statsPane.getChildren().clear();
		ArrayList<Text> stats = new ArrayList<Text>();

		piece.getStats()
				.getStatNames()
				.forEach(
						key -> stats.add(new Text(key + ":  "
								+ piece.getStats().getValue(key))));

		statsPane.getChildren().addAll(stats);

	}

	/**
	 * Update the action panel with actions from the selected piece
	 * 
	 * @param piece
	 */
	protected void updateActions(Piece piece) {
		// setActivePiece(piece);
		controlPane.getChildren().clear();
		ArrayList<Label> actions = new ArrayList<Label>();

		Map<KeyCode, Action> actionMap = myModel.getCurrentPlayer()
				.getActionKeyMap();
		System.out.println(actionMap);
		Map<Action, KeyCode> keyMap = actionMap
				.entrySet()
				.stream()
				.collect(
						Collectors
								.toMap(Map.Entry::getValue, Map.Entry::getKey));

		piece.getActions().forEach(action -> {
			Label l = new Label(action.toString());
			l.setOnMouseClicked(event -> bindAction(action));
			actions.add(l);

		});

		controlPane.getChildren().addAll(actions);

	}

	/**
	 * Updates the list of actions displayed
	 * 
	 * @param actions
	 */
	public void updateActionList(ArrayList<Label> actions) {
		controlPane.getChildren().clear();

		// l.setOnKeyPressed(event -> {
		// if (keyMap.containsKey(action)) {
		// if (event.getCode().equals(keyMap.get(action))) {
		// bindAction(action);
		// }
		//
		// }
		// });
		// });

		controlPane.getChildren().addAll(actions);

	}

	// public void updateActionList(ArrayList<Label> actions){
	// controlPane.getChildren().clear();
	// controlPane.getChildren().addAll(actions);
	// System.out.println("i get here");
	// }

	/**
	 * Method called when user clicks an action button
	 * 
	 * @param action
	 */
	protected void bindAction(Action action) {

		if (activePiece == null)
			return;
		setActiveAction(action);

		highLightActionRange();

		setGridState(new ApplyState(this));

	}

	/**
	 * TODO: Add javadoc
	 */
	private void setOnClick() {
		myGrid.setOnMouseClicked(event -> {
			performAction(event.getX(), event.getY());
		});
	}

	/**
	 * TODO: Add javadoc
	 */
	public void setOnEnterKey() {

		myGrid.requestFocus();
		myGrid.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {

				if (arg0.getCode() == KeyCode.F) {

					System.out.println("f");
					performAction(myKeyboardController.getCurrentLocation()
							.getX(), myKeyboardController.getCurrentLocation()
							.getY());

				}

			}

		});

		// myGrid.setOnKeyPressed(event->{
		// if (event.getCode() == KeyCode.F){
		// System.out.println("enter");
		// performAction(myKeyboardController.getCurrentLocation().getX(),
		// myKeyboardController.getCurrentLocation().getY());
		// }
		// }
		// );

	}

	/**
	 * Perform the actions of a click at position (x,y) on game grid
	 * 
	 * @param x
	 * @param y
	 */
	public void performAction(double x, double y) {
		System.out.println("current mouse location:" + x + ", " + y);
		System.out.println("myGrid size is" + myGrid.getWidth() + "*"
				+ myGrid.getHeight());
		System.out.println(myGrid.getBoundsInParent());

		if (myModel.getCurrentLevel().getGrid()
				.getPiece(findPosition(x - 45, y - 20)) == null) {
			System.out.println("no piece");
		}

		gridState.onClick(myModel.getCurrentLevel().getGrid()
				.getPiece(findPosition(x - 45, y - 20)));
		myGrid.clearEffect();
		highlightCurrent(findPosition(x - 45, y - 20), Color.BLUE);
		// addDropShadow(myGrid.get(((int)findPosition(x,y).getX()),
		// ((int)findPosition(x,y).getY())), Color.PURPLE);
	}

	public void performActionKeyboard(Point2D location) {

		gridState.onClick(myModel.getCurrentLevel().getGrid()
				.getPiece(location));
		myGrid.clearEffect();
		highlightCurrent(location, Color.BLUE);
	}

	/**
	 * Method to convert pixel coordinates into tile coordinates
	 * 
	 * @param x
	 * @param y
	 * @return a Point2D representing tile coordinates
	 */
	public Point2D findPosition(double x, double y) {
		double patchHeight = 500 / (double) myGrid.getCol();
		double patchWidth = 500 / (double) myGrid.getRow();
		int xCor = (int) (x / patchWidth);
		int yCor = (int) (y / patchHeight);
		// System.out.println("Current Mouse Coodinatate:"+ xCor +" "+ yCor);
		currentClick = new Point2D.Double(yCor, xCor);
		return currentClick;
	}

	/**
	 * Highlight the tiles that represent the possible range of the action
	 * selected
	 */
	@FXML
	protected void highLightActionRange() {

		myGrid.clearEffect();
		if (activePiece != null && activeAction != null) {

			activeAction.getActionRange(activePiece.getLoc()).forEach(
					point -> {

						if (point.getX() < myGrid.getRow()
								&& point.getY() < myGrid.getCol()
								&& point.getX() > 0 && point.getY() > 0) {
							Node n = myGrid.get((int) point.getX(),
									(int) point.getY());
							addDropShadow(n, Color.YELLOW);

						}

					});
		}
	}

	/**
	 * Highlight the effect range of an action if to be applied at a given
	 * position Highlight the effect range of an action if to be applied at a
	 * given position
	 * 
	 * @param n
	 * @param red
	 */

	protected void highLightEffectRange(MouseEvent me, Color c) {

		if (activePiece != null && activeAction != null) {

			activeAction.getActionRange(activePiece.getLoc()).forEach(
					point -> {
						Point2D temp = findPosition(me.getSceneX(),
								me.getSceneY());
						if (temp.equals(point)) {
							activeAction.getEffectRange().forEach(
									point2 -> {
										Node n = myGrid.get(
												(int) (temp.getX() + point2
														.getX()),
												(int) (temp.getY() + point2
														.getY()));

										addDropShadow(n, c);
									});

						}
					});

		}

	}

	/**
	 * Changes the image of the Cursor
	 * 
	 * @param filename
	 */
	public void changeCursor(String filename) {
		Image image = new Image(filename);
		myScene.setCursor(new ImageCursor(image, image.getWidth() / 4, image
				.getWidth() / 4));

	}

	/**
	 * Toggles whether the Keyboard Controls are active or inactive
	 */
	public void toggleKeyboardControl() {
		if (keyControlOn) {

			keyControlOn = false;
			unhighlight(myKeyboardController.getCurrentLocation());
			myKeyboardController = null;
		} else {
			myKeyboardController = new KeyboardController();
			myKeyboardController.setMovementKeyControl(this, myGrid, myModel);
			keyControlOn = true;
			// setOnEnterKey();

		}
	}

	/**
	 * Returns a Point2D representing the Location of a Click
	 * 
	 * @return
	 */
	public Point2D getCurrentClick() {
		return currentClick;

	}

	/**
	 * Getter to get the GridState
	 * 
	 * @return
	 */
	public IGridState getGridState() {
		return gridState;
	}

	/**
	 * Getter for the Active Action
	 * 
	 * @return
	 */
	protected Action getActiveAction() {
		return activeAction;
	}

	/**
	 * Getter to get the Scene
	 * 
	 * @return
	 */
	protected Scene getScene() {
		return myScene;
	}

	/**
	 * Getter to get the Game Grid
	 * 
	 * @return
	 */
	protected GameGrid getGrid() {
		return myGrid;
	}

	/**
	 * Getter to get the Game
	 * 
	 * @return
	 */
	protected Game getGame() {
		return myModel;
	}

	/**
	 * Getter to get the Active Piece
	 * 
	 * @return
	 */
	protected Piece getActivePiece() {
		return activePiece;
	}

	/**
	 * Setter to set the Active Piece
	 * 
	 * @param piece
	 */
	protected void setActivePiece(Piece piece) {
		activePiece = piece;
	}

	/**
	 * Setter to set the Active Action
	 * 
	 * @param action
	 */
	protected void setActiveAction(Action action) {
		activeAction = action;
	}

	/**
	 * Method to switch the state of the game grid between select mode and apply
	 * mode Method to switch the state of the game grid between select mode and
	 * apply mode
	 * 
	 * @param state
	 *            the current state of the Grid, select/ apply action Mode
	 */
	public void setGridState(IGridState state) {
		gridState = state;
	}
}
