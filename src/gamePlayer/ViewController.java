package gamePlayer;

import fxml_main.ErrorPopUp;
import gamedata.JSON.JSONManager;
import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.GameState;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import gameengine.player.SimpleAIPlayer;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import tests.TestGameCreator;
// import com.leapmotion.leap.Controller;
import authoring_environment.GUIGrid;
import authoring_environment.SuperTile;

public class ViewController {

	private static final String INVENTORY_FONT_NAME = "Consolas";
        private static final int ACTIONNAME_FONT_SIZE = 16;
        private static final int ITEMNAME_FONT_SIZE = 24;
        private static final String SIMPLE_AI_PLAYER = "Simple AI player";
	private static final String HUMAN_PLAYER = "Human Player";
	public static final String ADD_HIGH_SCORE_FXML = "newHighScore.fxml";
	public static final String GAMESPACE_FXML = "gameSpace.fxml";
	public static final String INITIALSCENE_FXML = "initialScene.fxml";
	public static final String SCOREBOARD_FXML = "scoreBoard.fxml";
	public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
	public static final String GAME_LOCATION = "/src/resources/json";
	public static final String POPUP_FXML = "popup.fxml";
	public static final String SETTINGS_FXML = "settings.fxml";
	private static final String HIGH_SCORE_FILE = "/resources/highscore/highscore.txt";
	private static final String WIN_SCREEN = "winLoseScreen.fxml";
	private static final String YOU_LOSE = "You Lose :(";
	
	private Stage myStage;
	private List<Player> myPlayerList;

	private BorderPane myGameSpace;
	private BorderPane myPopup;
	private VBox mySettings;
	@FXML
	private VBox myInitialVBox;
	private VBox myScoreBoard;
	private Scene initialScene;
	private Scene mySettingsScene;
	private Scene scoreScene;
	private Scene myPopupScene;
	private Scene myScene;
	private Scene newHighScoreScene;
	private Scene winLoseScene;
	private Game myModel;
	private GUIGrid myGrid;

	private Player myCurrentPlayer;

	private Boolean keyControlOn;
	private Boolean clickSoundOn;
	private Boolean backgroundMusicOn;
	private Boolean actionActiveOn;

	private KeyboardAction myKeyboardAction;
	private KeyboardMovement myKeyboardMovement;

	private Piece activePiece;
	private Action activeAction;

	private Audio myAudio;
	
	private Tab myTab;

	@FXML
	protected VBox statsPane;
	@FXML
	protected VBox inventoryPane;
	@FXML
	protected VBox controlPane;
	@FXML
	private MenuButton newGameMenu;
	@FXML
	private Text gameName;
	@FXML
	private Text highestScore;
	@FXML
	private VBox scores;
	@FXML
	private Label playerTurn;
	@FXML
	private Button showScoreButton;
	@FXML
	private MenuButton gameMenu;
	@FXML
	private TabPane tabPane;
	@FXML
	private AnchorPane languagesPane;
	@FXML
	private Button clearHighScores;
	@FXML
	private ChoiceBox players;
	@FXML
	private ChoiceBox playerType;
	@FXML
	private TextField playerName;
	@FXML
	private GridPane newHighScoreRoot;
	@FXML
	private TextField highScoreName;
	@FXML
	private Label playerHighScore;
	@FXML
	private Button highScoreOK;
	@FXML
	private BorderPane myWinLoseScreen;
	@FXML
	private Label winLose;
	
	private PlayerTypeSetter myPlayerTypeSetter;

	private ScrollPane myGridPane;
	private Point2D.Double currentClick;
	private IGridState gridState;
	private JSONManager myJSONManager;
	private GameGridEffect myGameGridEffect;
	private SuperTile keySelectedTile;
	private Languages myLanguages;

	public ViewController() throws UnsupportedAudioFileException,
	IOException, LineUnavailableException {
		openInitialMenu();
		myStage = new Stage();
		openInitialMenu();
		myStage.setScene(new Scene(myInitialVBox));
	        myStage.show();
	}
	
	public ViewController(Tab tab) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		myTab = tab;	
		openInitialMenu();
	}
	
	/**
         * Sets up and opens the initial scene
         * 
         * @throws LineUnavailableException
         * @throws IOException
         * @throws UnsupportedAudioFileException
         */
        @FXML
        protected void openInitialMenu() throws UnsupportedAudioFileException,
        IOException, LineUnavailableException {
                myGameSpace = new BorderPane();
                myScoreBoard = new VBox();
                scores = new VBox();
                myPopup = new BorderPane();
                mySettings = new VBox();

                myJSONManager = new JSONManager();
                loadFXML(INITIALSCENE_FXML, myInitialVBox);
                loadFXML(GAMESPACE_FXML, myGameSpace);
                loadFXML(POPUP_FXML, myPopup);
                loadFXML(SCOREBOARD_FXML, myScoreBoard);
                loadFXML(SETTINGS_FXML, mySettings);
                loadFXML(ADD_HIGH_SCORE_FXML, newHighScoreRoot);
                loadFXML(WIN_SCREEN, myWinLoseScreen);
                
                initialScene = new Scene(myInitialVBox);
                scoreScene = new Scene(myScoreBoard);
                myPopupScene = new Scene(myPopup);
                mySettingsScene = new Scene(mySettings);
                newHighScoreScene = new Scene(newHighScoreRoot);
                winLoseScene = new Scene(myWinLoseScreen);

                if (myTab == null) {
                    myStage.setScene(new Scene(myInitialVBox));
                }

                myAudio = new Audio();
                myAudio.playBackground();

                myLanguages = new Languages(languagesPane, tabPane, gameMenu, showScoreButton);
        }

        /**
	 * Allows user to load game
	 */
	@FXML
	protected void loadGame() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
		fc.setInitialDirectory(new File("src/resources/json"));
		File f = fc.showOpenDialog(myStage);

		try {
		        myModel = myJSONManager.readFromJSONFile(f.getAbsolutePath());
			myPlayerTypeSetter = new PlayerTypeSetter(myModel, this);
			myStage.setScene(myPlayerTypeSetter.startLoadPlayers());
		}
		catch (Exception e) {
		    ErrorPopUp myError = new ErrorPopUp(e.toString());
                    myError.show();
		}
		
	}
	
	 /**
         * Initiates a Button for a Test Game
         */
        @FXML
        private void testGame() {
                TestGameCreator JSBTester = new TestGameCreator();
                myModel = JSBTester.createNewGame();
                myPlayerTypeSetter = new PlayerTypeSetter(myModel, this);
                myStage.setScene(myPlayerTypeSetter.startLoadPlayers());
        }

	@FXML
	protected void openSettings() {
		System.out.println("opensettings");
		Stage stage = new Stage();
		stage.setScene(mySettingsScene);
		stage.show();
	}

	/**
	 * the method to restart the game; it asks the user whether to save the
	 * current game
	 * 
	 */
	@FXML
	protected void restartGame() {
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
		myModel.setCurrentPlayer(myCurrentPlayer);
		try {
		          myJSONManager.writeToJSON(myModel, f.getPath());
		}
		catch (Exception e) {
		    ErrorPopUp myError = new ErrorPopUp(e.toString());
                    myError.show();
		}

	}


	/**
	 * Updates the current language of the game.
	 */
	@FXML
	public void updateLanguage() {
		myLanguages.findCurrentLanguage();
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
	protected void updatePlayers(){
		String player=players.getValue().toString();
		//Start from 0
		int playerID=Integer.parseInt(player.substring(player.length()-1));
		//Currently name is not used.
		String name=playerName.getText();
		String type=playerType.getValue().toString();

		removePreviousPlayer(playerID);

		//ID start from 1
		if (type.equals(HUMAN_PLAYER)){
			myPlayerList.add(new HumanPlayer(playerID));
		}else{
			myPlayerList.add(new SimpleAIPlayer(playerID));
		}

	}
	

	private void removePreviousPlayer(int playerID) {
		int removeIndex=-1;
		for (int i=0;i<myPlayerList.size();i++){
			if (myPlayerList.get(i).getID()==playerID){
				removeIndex=i;
			}
		}
		if (removeIndex>-1) myPlayerList.remove(removeIndex);
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
		    ErrorPopUp myError = new ErrorPopUp(exception.toString());
                    myError.show();
			throw new RuntimeException(exception);
		}
	}
	
	public void beginGame() {
	    initializeGrid();
	    myModel.getCurrentLevel().getGrid().repopulateGrid();
	}
        
         /**
          * Loads a game into the GUI, calls the loading of the grid
          * @param gameToLoad
          */
        public void testPlayGame(Game gameToLoad) {
                initializeGrid();
                if (myTab != null) {
                    myTab.setContent(myGameSpace);
                }
                else {
                    myScene = new Scene(myGameSpace);
                    myStage.setScene(myScene);
                }
                myModel.getCurrentLevel().getGrid().repopulateGrid();
        }

	/**
	 * Initializes grid and its effects manager (gamegrideffect)
	 */
	private void initializeGrid() {
		myAudio.playSelection();

		myGridPane = new ScrollPane();
		Level currentLevel = myModel.getCurrentLevel();
		myGrid = currentLevel.getGrid();
		myGrid.displayPane(myGridPane);

		myGameSpace.setCenter(myGridPane);

		setOnClick();
		setGridState(new SelectState(this));
		keyControlOn = false;
		backgroundMusicOn = true;
		clickSoundOn = true;
		myGameGridEffect = new GameGridEffect(this);
		clearHighScores.setOnMouseClicked(event -> handleClearHighScores());
	}

	/**
	 * Loads the scores from the current game
	 */
	protected void loadScores() {
		List<Double> scoreList = new ArrayList<Double>();
		gameName.setText(myModel.toString());
		scores.getChildren().clear();
		for (Player p : myModel.getPlayers()) {
			scoreList.add(p.getScore());
			Text playerScore = new Text("Player " + p.getID() + ": "
					+ String.valueOf(p.getScore()));
			playerScore.setFill(Color.WHITE);
			scores.getChildren().add(playerScore);
		}
		highestScore.setText(String.valueOf(Collections.max(scoreList)));
	}
	
	protected void updateInventory(Piece piece) {
	    inventoryPane.getChildren().clear();
	    System.out.println("update inv");
	    ArrayList<Text> inv = new ArrayList<Text>();
	    
	    if (piece.getInventory() != null){
	    Map<String, List<String>> invMap = piece.getInventory().getStringDisplay();

	    for (String item: invMap.keySet()){
	        Text itemName = new Text("\n" + item + ": ");
	        itemName.setFont(Font.font(INVENTORY_FONT_NAME, FontWeight.BOLD, ITEMNAME_FONT_SIZE));
	        inv.add(itemName);
	        for (String action: invMap.get(item)){
	            Text actionName = new Text(action);
	            actionName.setFont(Font.font(INVENTORY_FONT_NAME, ACTIONNAME_FONT_SIZE));
	            inv.add(actionName);
	        }
	    }
	    
	    inventoryPane.getChildren().addAll(inv);
	    }
	}
	

	/**
	 * Update the stats panel with stats from the selected piece
	 * 
	 * @param piece
	 */
	protected void updateStats(Piece piece) {
		statsPane.getChildren().clear();
		ArrayList<Text> stats = new ArrayList<Text>();
		piece.getStats().getStatNames().forEach(
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
		controlPane.getChildren().clear();
		ArrayList<Label> actions = new ArrayList<Label>();
		piece.getActions().forEach(action -> {
			Label l = new Label(action.getName());
			l.setOnMouseClicked(event -> bindAction(action));
			actions.add(l);
		});

		controlPane.getChildren().addAll(actions);

	}

	/**
	 * Clears control pane of actions after you've selected one to do
	 */
	public void clearActions() {
		controlPane.getChildren().clear();
	}

	/**
	 * Updates the list of actions displayed
	 * 
	 * @param actions
	 */
	public void updateActionList(ArrayList<Label> actions) {

		controlPane.getChildren().clear();
		controlPane.getChildren().addAll(actions);
	}

	/**
	 * Method called when user clicks an action button
	 * 
	 * @param action
	 */
	protected void bindAction(Action action) {
//		System.out.println(String.format("BIND %s", action));
		if (clickSoundOn) {
			myAudio.playSelection();
		}

		if (activePiece == null)
			return;
//		System.out.println("SETTING ACTIVE ACTION " + action);
		setActiveAction(action);
		myGameGridEffect.highlightActionRange();
		setGridState(new ApplyState(this));
		if (keyControlOn) {

			// after i click action button, i need to go back to KeyboardMovement
			myKeyboardAction = null;
			myKeyboardMovement = new KeyboardMovement();
			myKeyboardMovement.setMovementKeyControl(this);
			
			actionActiveOn = true;
		}
	}

	private void setOnClick() {
	           if (clickSoundOn) {
                       myAudio.playSelection();
               }
		myGridPane.getContent().setOnMouseClicked(event -> {
			Point2D.Double loc = findPosition(event.getX(), event.getY());
			performAction(loc);
		});
	}

	/**
	 * Perform the actions of a click at position (x,y) on game grid and
	 * highlights the piece that was clicked
	 * 
	 * @param x
	 * @param y
	 */
	public void performAction(Point2D.Double myCurrentLocation) {
		gridState.onClick(myModel.getCurrentLevel().getGrid().getPiece(myCurrentLocation));
		while (myCurrentPlayer.getType().equals("AI")) {
			myCurrentPlayer.play();
			checkEndActions();
		}


		if (keyControlOn) {

			if ((myKeyboardMovement != null) & (!actionActiveOn)){
				myKeyboardMovement = null;
				myKeyboardAction = new KeyboardAction();
				myKeyboardAction.setActionKeyControl(this);
			}
			
                        if ((myKeyboardMovement == null) & (actionActiveOn)){
				myKeyboardAction = null;
				myKeyboardMovement = new KeyboardMovement();
				myKeyboardMovement.setMovementKeyControl(this);
			}
                        
                        if (actionActiveOn){
                            actionActiveOn = false;
                        }
		}
	}

	/**
	 * Select state tells VC to highlight the selected piece
	 * 
	 * @param p
	 */
	public void highlightSelected(Piece p) {
		myGameGridEffect.highlightCurrent(p.getLoc(), myModel.getCurrentLevel()
				.getGrid().getPiece(p.getLoc()));

	}

	/**
	 * Method to convert pixel coordinates into tile coordinates
	 * 
	 * @param x
	 * @param y
	 * @return a Point2D.Double representing tile coordinates
	 */
	public Point2D.Double findPosition(double x, double y) {
	    currentClick = myGrid.findClickedCoordinate(x, y);
		return currentClick;
	}
	


	/**
	 * Toggles clicking sound
	 */
	public void toggleClickSound() {
		if (clickSoundOn) {
			clickSoundOn = false;
		} else {
			clickSoundOn = true;
		}
	}

	/**
	 * Toggles background music sound
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void toggleBackgroundMusic() throws UnsupportedAudioFileException,
	IOException, LineUnavailableException {
		if (backgroundMusicOn) {
			myAudio.muteDefault();
			backgroundMusicOn = false;
			System.out.println("BGMusic Off");
		} else {
			myAudio.playBackground();
			backgroundMusicOn = true;
			System.out.println("BGMusic On");
		}
	}

	/**
	 * Toggles whether the Keyboard Controls are active or inactive
	 */
	public void toggleKeyboardControl() {
		if (keyControlOn) {
			keyControlOn = false;

			// dehighlighting the tile the keyboard is currently highlighting
			if (myKeyboardMovement != null) {
				keySelectedTile = myGrid.findTile(myKeyboardMovement
						.getCurrentLocation());
				keySelectedTile.deselectTile();
			}
			myKeyboardMovement = null;
			myKeyboardAction = null;
			System.out.println("Keyboard OFF");
		} else {

			// dehighlighting the tile the mouse click is currently highlighting
			if (currentClick != null) {
				SuperTile selectedTile = myGrid.findTile(currentClick);
				selectedTile.deselectTile();
			}

			actionActiveOn = false;
			myKeyboardMovement = new KeyboardMovement();
			myKeyboardMovement.setMovementKeyControl(this);
			
			keyControlOn = true;
		}
	}

	/**
	 * Returns a Point2D.Double representing the Location of a Click
	 * 
	 * @return
	 */
	public Point2D.Double getCurrentClick() {
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
	 * Getter to get the GameGrid
	 * 
	 * @return
	 */

	protected ScrollPane getGridPane() {
		return myGridPane;
	}

	/**
	 * Getter to get the GUIGrid
	 * 
	 * @return
	 */

	protected GUIGrid getGrid() {
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
	 * Also checks if the game has been won to show high score
	 * 
	 * @param state
	 *            the current state of the Grid, select/ apply action Mode
	 */
	protected void setGridState(IGridState state) {
		myCurrentPlayer = myModel.getCurrentPlayer();
		setPlayerTurnDisplay();
		gridState = state;
	}

	/**
	 * Changes the player turn label to show current turn
	 */
	protected void setPlayerTurnDisplay() {
		playerTurn.setText("Player " + myCurrentPlayer.getID() + "'s Move");	
	}

	/**
	 * returns the GameGridEffect
	 * 
	 * @return
	 */
	protected GameGridEffect getGameGridEffect() {
		return myGameGridEffect;
	}


	protected void endAction() {
		this.checkEndActions();
		this.getGrid().repopulateGrid();
	}

	protected void checkEndActions() {
		Level currentLevel = myModel.getCurrentLevel();
		myModel.getCurrentPlayer().playTurn();
		GameState.movesMade++;
		System.out.println(GameState.movesMade);
		currentLevel.runGameEvents();
		if (GameState.getGameWon()) {
		    winLose.setText("You Win!");
		    Stage newStage = new Stage();
		    newStage.setScene(winLoseScene);
		    newStage.show();
                    String highScorer = "Bob";
                    int highScore = 0;
                    for (Player p : myModel.getPlayers()) {
                        
                             if (p.getScore() > highScore) { 
                                 highScorer = "Player" + p.getID();
                                 highScore = (int) p.getScore(); 
                             }
                    }
                    enterHighScoreInfo(highScorer, highScore);
		}
		if (GameState.getGameLost()) {
		    winLose.setText(YOU_LOSE);
		    Stage newStage = new Stage();
                    newStage.setScene(winLoseScene);
                    newStage.show();
		}
		if (GameState.getTurnOver()) {
			GameState.setTurnFalse();
			myModel.nextPlayer();
			myCurrentPlayer = myModel.getCurrentPlayer();
			setPlayerTurnDisplay();
		}

		if (GameState.getNextLevelID() != null) {
			System.out.println("NEXT LEVEL");
			// myController.getGame().changeLevel(currentLevel.getNextLevelID());
			myModel.changeLevel(GameState.getNextLevelID());
			// myController.getGame().getCurrentLevel().getGrid().displayPane(myController.getGridPane());
			initializeGrid();
			myGrid.repopulateGrid();
		}
	}
	
	       /**
         * Allows winning player to enter their information
         * @param highScorer
         * @param highScore
         */
        private void enterHighScoreInfo(String highScorer, int highScore) {
                Stage newScore = new Stage();
                newScore.setScene(newHighScoreScene);
                playerHighScore.setText(playerHighScore.getText() + " " + highScore);
                highScoreOK.setOnMouseClicked(event -> 
                    addEntryToHallOfFame(newScore, highScoreName.getText(), highScore));
                newScore.show();
        }

        /**
         * Adds new high score to the hall of fame
         * @param stage
         * @param nickname
         * @param score
         */
        private void addEntryToHallOfFame(Stage stage, String nickname, int score) {
                try {
                        File myScores = new File(getClass().getResource(HIGH_SCORE_FILE).getPath());
                        Scanner in = new Scanner(myScores);
                        String content = "";
                        List<List<String>> highScores = new ArrayList<List<String>>();
                        while(in.hasNextLine()) {
                                String line = in.nextLine();
                                content += line + "\n";
                                List<String> listLine = Arrays.asList(line.split("\\s*: \\s*"));
                                System.out.println(listLine.get(0) + listLine.get(1));
                                highScores.add(listLine);
                        }
                        in.close();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                              new FileOutputStream(myScores, true)));
                        writer.write(content);
                        writer.write(nickname + ": " + score + "\n");
                        writer.close();
                        List<String> currentScore = new ArrayList<String>();
                        currentScore.add(nickname);
                        currentScore.add(String.valueOf(score));
                        highScores.add(currentScore);
                        
                        Collections.sort(highScores, new Comparator<List<String>> () {
                            @Override
                            public int compare(List<String> a, List<String> b) {
                                return String.valueOf(a.get(1)).compareTo(String.valueOf(b.get(1)));
                            }
                        });
                        
                        myScoreBoard.getChildren().add(1, new Label(nickname + ": " + currentScore));
                        stage.setScene(scoreScene);
                        stage.show();
                }
                catch (FileNotFoundException f)  {
                    ErrorPopUp myError = new ErrorPopUp(f.toString());
                    myError.show();
                        System.out.println("High scores file not found, sorry.");
                }
                catch (IOException i) {
                    ErrorPopUp myError = new ErrorPopUp(i.toString());
                    myError.show();
                        System.out.println("Write failed");
                        
                }

        }

	private void handleClearHighScores() {
		try {
			File myScores = new File(getClass().getResource(
					"/resources/highscore/highscore.txt").getPath());
			BufferedWriter writer = new BufferedWriter(new FileWriter(myScores));
			writer.write("");

		} catch (IOException io) {
		    ErrorPopUp myError = new ErrorPopUp(io.toString());
                    myError.show();
		}
		scores.getChildren().clear();
	}



        
	
}