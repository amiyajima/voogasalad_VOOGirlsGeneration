package gamePlayer;

import gamedata.JSON.JSONManager;
import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import gameengine.player.SimpleAIPlayer;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import tests.TestGameCreator;
// import com.leapmotion.leap.Controller;
import authoring_environment.GUIGrid;
import authoring_environment.SuperTile;


public class ViewController {

    private static final String SIMPLE_AI_PLAYER = "Simple AI player";
    private static final String HUMAN_PLAYER = "Human Player";
    public static final String GAMESPACE_FXML = "gameSpace.fxml";
    public static final String INITIALSCENE_FXML = "initialScene.fxml";
    public static final String SCOREBOARD_FXML = "scoreBoard.fxml";
    public static final String PLAYER_FXML = "playerEditorScene.fxml";
    public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
    public static final String GAME_LOCATION = "/src/resources/json";
    public static final String POPUP_FXML = "popup.fxml";
    public static final String SETTINGS_FXML = "settings.fxml";

    private Stage myStage;
    private BorderPane myGameSpace;
    private BorderPane myPopup;
    private VBox mySettings;
    private VBox myInitialScene;
    private VBox myScoreBoard;
    private VBox myPlayerEditor;
    private Scene mySettingsScene;
    private Scene scoreScene;
    private Scene myPopupScene;
    private Scene myPlayerScene;
    private Scene myScene;
    private Game myModel;
    private GUIGrid myGrid;
    private Scene mySplashScreen;

    private Player myCurrentPlayer;

    // private SampleListener myLeapListener;

    private Boolean keyControlOn;
    private Boolean clickSoundOn;
    private Boolean backgroundMusicOn;

    private KeyboardAction myKeyboardAction;
    private KeyboardMovement myKeyboardMovement;

    private Piece activePiece;
    private Action activeAction;

    private Audio myAudio;
    private ResourceBundle messages;

    private List<Player> myPlayerList;

    @FXML
    protected VBox statsPane;
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
    private Button playerEditButton;
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

    private ScrollPane myGridPane;
    private Point2D.Double currentClick;
    private IGridState gridState;
    private JSONManager myJSONManager;
    private GameGridEffect myGameGridEffect;
    private SuperTile keySelectedTile;
    private int tempMoveCount = 0;
    private Languages myLanguages;

    public ViewController (Stage s) throws UnsupportedAudioFileException,
        IOException, LineUnavailableException {
        myPlayerList = new ArrayList<Player>();
        // Test
        myPlayerList.add(new HumanPlayer(1));
        myPlayerList.add(new HumanPlayer(3));

        myStage = s;
        openInitialMenu();
        try {
            newGame();
        }
        catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
        }
        myStage.show();
    }

    /**
     * Sets up and opens the initial scene
     * 
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    @FXML
    protected void openInitialMenu () throws UnsupportedAudioFileException,
                                     IOException, LineUnavailableException {
        myInitialScene = new VBox();
        myGameSpace = new BorderPane();
        myScoreBoard = new VBox();
        scores = new VBox();

        mySplashScreen = new SplashScreen().getScene();

        myPopup = new BorderPane();
        mySettings = new VBox();
        myPlayerEditor = new VBox();

        myJSONManager = new JSONManager();
        // myLeapController = new Controller();
        loadFXML(GAMESPACE_FXML, myGameSpace);
        loadFXML(INITIALSCENE_FXML, myInitialScene);
        loadFXML(POPUP_FXML, myPopup);
        loadFXML(SCOREBOARD_FXML, myScoreBoard);
        loadFXML(SETTINGS_FXML, mySettings);
        loadFXML(PLAYER_FXML, myPlayerEditor);

        scoreScene = new Scene(myScoreBoard);
        myPopupScene = new Scene(myPopup);
        mySettingsScene = new Scene(mySettings);
        myPlayerScene = new Scene(myPlayerEditor);

        myStage.setScene(new Scene(myInitialScene));

        myAudio = new Audio();
        myAudio.playBackground();

        myLanguages = new Languages(languagesPane, tabPane, gameMenu, showScoreButton);
    }

    /**
     * the method allows user to load the previously saved json representation
     * of the game and uses JSON reader from Game Data to generate an instance
     * of Game.
     */

    @FXML
    protected void loadGame () {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        fc.setInitialDirectory(new File("src/resources/json"));
        File f = fc.showOpenDialog(myStage);

        try {
            System.out.println("VC: loading game... ");
            JSONManager myJM = new JSONManager();
            myStage.setScene(mySplashScreen);
            TestGameCreator JSBTester = new TestGameCreator();
            testPlayGame(myJM.readFromJSONFile(f.getAbsolutePath()));
            System.out.println("VC: game loaded... ");
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("Could not find the file at - " + f.getAbsolutePath());
        }
        catch (Exception e) {
            System.out.println("Other exception occured.");
            e.printStackTrace();
        }

    }

    @FXML
    protected void openSettings () {
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
    protected void restartGame () {
        initializeGrid();
        statsPane.getChildren().clear();
        controlPane.getChildren().clear();
    }

    @FXML
    protected void exitGame () {
        myStage.close();
    }

    /**
     * to save the current game (state and settings) to a json file which could
     * be later loaded in
     */
    @FXML
    protected void saveGame () {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showSaveDialog(myStage);
        String basePath = System.getProperty("user.dir");
        String absolutePath = f.getPath();
        String relativePath = "";
        if (absolutePath.startsWith(basePath)) {
            relativePath = absolutePath.substring(basePath.length() + 1);
        }
        myJSONManager.writeToJSON(myModel, f.getPath() + ".json");

    }

    /**
     * Initiates a Button for a Test Game
     */
    @FXML
    private void testGame () {
        myStage.setScene(mySplashScreen);
        TestGameCreator JSBTester = new TestGameCreator();
        testPlayGame(JSBTester.createNewGame());
    }

    /**
     * Updates the current language of the game.
     */
    @FXML
    public void updateLanguage () {
        myLanguages.findCurrentLanguage();
    }

    /**
     * loads the players and their scores of the current game; display the
     * Highest score in the high score display at the bottom
     */
    @FXML
    protected void showScore () {
        Stage stage = new Stage();
        stage.setScene(scoreScene);
        loadScores();
        stage.show();

    }

    @FXML
    protected void cancelPopup () {
        System.out.println("cancel popup");
    }

    @FXML
    protected void editPlayers () {
        Stage stage = new Stage();
        stage.setScene(myPlayerScene);
        stage.show();
        players.getItems().clear();
        playerType.getItems().clear();

        for (int i = 0; i < myPlayerList.size(); i++) {
            players.getItems().add("Player" + myPlayerList.get(i).getID());
        }
        playerType.getItems().addAll(HUMAN_PLAYER, SIMPLE_AI_PLAYER);
    }

    @FXML
    protected void updatePlayers () {
        String player = players.getValue().toString();
        // Start from 0
        int playerID = Integer.parseInt(player.substring(player.length() - 1));
        // Currently name is not used.
        String name = playerName.getText();
        String type = playerType.getValue().toString();

        removePreviousPlayer(playerID);

        // ID start from 1
        if (type.equals(HUMAN_PLAYER)) {
            myPlayerList.add(new HumanPlayer(playerID));
        }
        else {
            myPlayerList.add(new SimpleAIPlayer(playerID));
        }

    }

    private void removePreviousPlayer (int playerID) {
        int romoveIndex = -1;
        for (int i = 0; i < myPlayerList.size(); i++) {
            if (myPlayerList.get(i).getID() == playerID) {
                romoveIndex = i;
            }
        }
        if (romoveIndex > -1) myPlayerList.remove(romoveIndex);
    }

    /**
     * Loads an FXML file
     * 
     * @param url
     * @param n
     */
    private void loadFXML (String url, Node n) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(n);
        try {
            fxmlLoader.load();

        }
        catch (IOException exception) {

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
    protected void newGame () throws UnsupportedAudioFileException, IOException,
                             LineUnavailableException {

        List<File> games = getResourceGames();

        games.forEach(file -> {
            MenuItem l = new MenuItem();
            l.setText(file.getName().substring(0, file.getName().length() - 5));
            l.getStyleClass().add("button");
            newGameMenu.getItems().add(l);
            l.setOnAction(event -> {
                try {
                    System.out.println("VC: loading game... ");
                    JSONManager myJM = new JSONManager();
                    myStage.setScene(mySplashScreen);
                    String gameLoc = GAME_LOCATION + l.getText() + ".json";
                    testPlayGame(myJM.readFromJSONFile(gameLoc));
                    System.out.println("VC: game loaded... ");
                }
                catch (FileNotFoundException e) {
                    String gameLoc = GAME_LOCATION + "/" + l.getText() + ".json";
                    System.out.println("Could not find file: " + gameLoc);
                }
            });
        });

    }

    /**
     * Initializes grid and its effects manager (gamegrideffect)
     */
    private void initializeGrid () {
        System.out.println("initialize grid");

        myAudio.playSelection();

        myGridPane = new ScrollPane();
        Level currentLevel = myModel.getCurrentLevel();
        myGrid = currentLevel.getGrid();
        // System.out.println("myGrid: " + myGrid);
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
     * Loads the Score from a Player for Display
     */
    protected void loadScores () {
        List<Integer> scoreList = new ArrayList<Integer>();
        gameName.setText(myModel.toString());
        scores.getChildren().clear();
        for (Player p : myModel.getPlayers()) {
            int score = 0; // 0 for now. will get from Player later!!!!!
            scoreList.add(score);
            Text playerScore = new Text("Player " + p.getID() + ": "
                                        + String.valueOf(score));
            playerScore.setFill(Color.WHITE);
            scores.getChildren().add(playerScore);
        }
        highestScore.setText(String.valueOf(Collections.max(scoreList)));

    }

    /**
     * The method to get all json files from the resources directory that stores
     * all the games user has defined from the authoring environment
     */
    private List<File> getResourceGames () {

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
    protected void updateStats (Piece piece) {

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
    protected void updateActions (Piece piece) {
        // System.out.println("UPDATE ACTIONS");
        // myAudio.playSelection();
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
    public void clearActions () {
        controlPane.getChildren().clear();
    }

    /**
     * Updates the list of actions displayed
     * 
     * @param actions
     */
    public void updateActionList (ArrayList<Label> actions) {

        controlPane.getChildren().clear();
        controlPane.getChildren().addAll(actions);
    }

    /**
     * Method called when user clicks an action button
     * 
     * @param action
     */
    protected void bindAction (Action action) {
        System.out.println("BIND ACTION");
        if (clickSoundOn) {
            myAudio.playSelection();
        }

        if (activePiece == null)
            return;
        setActiveAction(action);
        myGameGridEffect.highlightActionRange();
        setGridState(new ApplyState(this));
        if (keyControlOn) {

            // after i click action button, i need to go back to
            // KeyboardMovement
            myKeyboardAction = null;
            myKeyboardMovement = new KeyboardMovement();
            myKeyboardMovement.setMovementKeyControl(this);
        }
    }

    private void setOnClick () {
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
    public void performAction (Point2D.Double myCurrentLocation) {
        System.out.println("PERFORM ACTION");

        if (clickSoundOn) {
            myAudio.playSelection();
        }

        gridState.onClick(myModel.getCurrentLevel().getGrid().getPiece(myCurrentLocation));

        while (myCurrentPlayer.getType().equals("AI")) {
            myCurrentPlayer.play();
            this.checkEndActions();
        }

        if (keyControlOn) {

            if (myKeyboardMovement != null) {
                myKeyboardMovement = null;
                myKeyboardAction = new KeyboardAction();
                myKeyboardAction.setActionKeyControl(this);
            }

            else {
                myKeyboardAction = null;
                myKeyboardMovement = new KeyboardMovement();
                myKeyboardMovement.setMovementKeyControl(this);
            }
        }

    }

    /**
     * Select state tells VC to highlight the selected piece
     * 
     * @param p
     */
    public void highlightSelected (Piece p) {
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
    public Point2D.Double findPosition (double x, double y) {
        double patchHeight = myGrid.getTileHeight();
        double patchWidth = myGrid.getTileHeight();
        int xCor = (int) (x / patchWidth);
        int yCor = (int) (y / patchHeight);
        currentClick = new Point2D.Double(xCor, yCor);
        return currentClick;
    }

    public void toggleClickSound () {
        if (clickSoundOn) {
            clickSoundOn = false;
        }
        else {
            clickSoundOn = true;
        }
    }

    public void toggleBackgroundMusic () throws UnsupportedAudioFileException,
                                        IOException, LineUnavailableException {
        if (backgroundMusicOn) {
            myAudio.muteDefault();
            backgroundMusicOn = false;
            System.out.println("BGMusic Off");
        }
        else {
            myAudio.playBackground();
            backgroundMusicOn = true;
            System.out.println("BGMusic On");
        }
    }

    /**
     * Toggles whether the Keyboard Controls are active or inactive
     */
    public void toggleKeyboardControl () {
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
        }
        else {

            // dehighlighting the tile the mouse click is currently highlighting
            if (currentClick != null) {
                SuperTile selectedTile = myGrid.findTile(currentClick);
                selectedTile.deselectTile();
            }

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
    public Point2D.Double getCurrentClick () {
        return currentClick;
    }

    /**
     * Getter to get the GridState
     * 
     * @return
     */
    public IGridState getGridState () {
        return gridState;
    }

    /**
     * Getter for the Active Action
     * 
     * @return
     */
    protected Action getActiveAction () {
        return activeAction;
    }

    /**
     * Getter to get the Scene
     * 
     * @return
     */
    protected Scene getScene () {
        return myScene;
    }

    /**
     * Getter to get the GameGrid
     * 
     * @return
     */

    protected ScrollPane getGridPane () {
        return myGridPane;
    }

    /**
     * Getter to get the GUIGrid
     * 
     * @return
     */

    protected GUIGrid getGrid () {
        return myGrid;
    }

    /**
     * Getter to get the Game
     * 
     * @return
     */
    protected Game getGame () {
        return myModel;
    }

    /**
     * Getter to get the Active Piece
     * 
     * @return
     */
    protected Piece getActivePiece () {
        return activePiece;
    }

    /**
     * Setter to set the Active Piece
     * 
     * @param piece
     */
    protected void setActivePiece (Piece piece) {
        activePiece = piece;
    }

    /**
     * Setter to set the Active Action
     * 
     * @param action
     */
    protected void setActiveAction (Action action) {
        activeAction = action;
    }

    /**
     * Method to switch the state of the game grid between select mode and apply
     * mode Method to switch the state of the game grid between select mode and
     * apply mode
     * 
     * @param state
     *        the current state of the Grid, select/ apply action Mode
     */
    public void setGridState (IGridState state) {
        tempMoveCount++;

        if (myModel.getCurrentLevel().getGameWon() || tempMoveCount == 10) {
            // TODO assuming that the most recent currentPlayer won
            String highScorer = "Bob";
            Random randy = new Random();
            int highScore = randy.nextInt(100000);
            for (Player p : myModel.getPlayers()) {
                /*
                 * if (p.getScore() > highScore) { highScorer = p.getID();
                 * highScore = p.getScore(); }
                 */
            }
           // showHighScoreInfo(highScorer, highScore);       // commenting this out for now!!!

        }

        myCurrentPlayer = myModel.getCurrentPlayer();
        setPlayerTurnDisplay();
        gridState = state;
    }

    /**
     * Changes the player turn label to show current turn
     */
    void setPlayerTurnDisplay () {
        playerTurn.setText("Player " + myCurrentPlayer.getID() + "'s Move");
    }

    private void showHighScoreInfo (String highScorer, int highScore) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(myStage);

        Text congrats = new Text("Congratulations, Player " + highScorer
                                 + " achieved a high score");
        Text score = new Text(String.valueOf(highScore));
        congrats.getStyleClass().add("plaintext");
        score.getStyleClass().add("plaintext");
        TextField nickname = new TextField();
        nickname.setTranslateY(5);
        nickname.setMaxWidth(150);
        nickname.setText("Nickname");
        Button go = new Button("Enter the Hall of Fame");
        go.setOnMouseClicked(event -> addEntryToHallOfFame(dialog,
                                                           nickname.getText(), score.getText()));
        nickname.setId("nickname");
        go.setId("highscorebutton");

        VBox dialogVbox = new VBox(10);
        dialogVbox.setPadding(new Insets(10, 10, 10, 10));
        dialogVbox.getChildren().addAll(congrats, score, nickname, go);
        Scene dialogScene = new Scene(dialogVbox, 600, 250);
        dialogScene.getStylesheets().add("/resources/stylesheets/stylesheet.css");
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void addEntryToHallOfFame (Stage stage, String nickname, String score) {
        try {
            File myScores2 =
                    new File(getClass().getResource("/resources/highscore/highscore.txt").getPath());
            Scanner in = new Scanner(myScores2);
            String content = "";
            ArrayList<List<String>> highScores = new ArrayList<List<String>>();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                content += line + "\n";
                List<String> listLine = Arrays.asList(line.split("\\s*: \\s*"));
                System.out.println(listLine.get(0) + listLine.get(1));
                highScores.add(listLine);
            }
            in.close();

            File myScores =
                    new File(getClass().getResource("/resources/highscore/highscore.txt").getPath());
            BufferedWriter writer = new BufferedWriter(new FileWriter(myScores));
            // writer.write(content);
            writer.write(nickname + ": " + score + "\n");
            writer.flush();
            writer.close();
            List<String> currentScore = new ArrayList<String>();
            currentScore.add(nickname);
            currentScore.add(score);
            highScores.add(currentScore);
            /*
             * Collections.sort(highScores, new Comparator<List<String>> () {
             * 
             * @Override
             * public int compare(List<String> a, List<String> b) {
             * return String.valueOf(a.get(1)).compareTo(String.valueOf(b.get(1)));
             * }
             * });
             */
            for (List<String> each : highScores) {
                myScoreBoard.getChildren().add(1, new Label(each.get(0) + ": " + each.get(1)));
            }
            stage.setScene(scoreScene);
            stage.show();
        }
        catch (FileNotFoundException f) {
            System.out.println("High scores file not found, sorry.");
        }
        catch (IOException i) {
            System.out.println("Write failed");
        }

    }

    /**
     * returns the GameGridEffect
     * 
     * @return
     */
    protected GameGridEffect getGameGridEffect () {
        return myGameGridEffect;
    }

    public VBox getcontrolPane () {
        return controlPane;
    }

    protected void endAction () {
        System.out.println("Ending Action");
        this.checkEndActions();
        this.getGrid().repopulateGrid();
    }

    public void checkEndActions () {
        Level currentLevel = myModel.getCurrentLevel();
        myModel.getCurrentPlayer().playTurn();
        currentLevel.runGameEvents();
        if (currentLevel.getGameWon()) {
            // GAMEWON
        }
        if (currentLevel.getGameLost()) {
            // GAMELOST
        }
        if (currentLevel.getTurnOver()) {
            currentLevel.setTurnFalse();
            myModel.nextPlayer();
            myCurrentPlayer = myModel.getCurrentPlayer();
            setPlayerTurnDisplay();
        }

        if (currentLevel.getNextLevelID() != null) {
            System.out.println("NEXT LEVEL");
            // myController.getGame().changeLevel(currentLevel.getNextLevelID());
            myModel.changeLevel("Level 2");
            // myController.getGame().getCurrentLevel().getGrid().displayPane(myController.getGridPane());
            initializeGrid();
        }
        if (myModel.getCurrentPlayer().getNumMovesPlayed() > 2) {
            System.out.println("NEXT PLAYER HARD CODE");
            myModel.nextPlayer();
            myCurrentPlayer = myModel.getCurrentPlayer();
            setPlayerTurnDisplay();
        }
    }

    private void handleClearHighScores () {
        try {
            File myScores =
                    new File(getClass().getResource(
                                                    "/resources/highscore/highscore.txt").getPath());
            BufferedWriter writer = new BufferedWriter(new FileWriter(myScores));
            writer.write("");

        }
        catch (IOException io) {
            System.out.println("IO Excpetion Occured in high scores");

        }
        scores.getChildren().clear();
    }

    /**
     * Test Method for backend
     * 
     * @param testGame
     */
    public void testPlayGame (Game testGame) {
        myModel = testGame;
        TestGameCreator tgc = new TestGameCreator();
        tgc.createNewGame();
        tgc.createNewGame();
        tgc.createNewGame();
        System.out.println("model found in viewcontroller: " + myModel);
        initializeGrid();
        myScene = new Scene(myGameSpace);
        myStage.setScene(myScene);

        // System.out.println("VC: Current Level: " + myModel.getCurrentLevel().getId());
        // System.out.println(myModel.getCurrentLevel().getGrid().toString());
        myModel.getCurrentLevel().getGrid().repopulateGrid();

        // System.out.println("VC: Current Level - " + myModel.getCurrentLevel().getId() + " " +
        // "Current Player" + myCurrentPlayer.getID());
        /*
         * for (Level l : myModel.getLevels()) {
         * System.out.println("Level #" + l.getId());
         * System.out.println(l.toString());
         * }
         */
        // System.out.println("VC: Current Level: " + myModel.getCurrentLevel().getId());
    }

}
