package gamePlayer;

import gamedata.action.Action; 
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;
import gamedata.gamecomponents.SquareGrid;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

import javafx.event.EventHandler;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;



public class ViewController{
    
    public static final String GAMESPACE_FXML = "gameSpace.fxml";
    public static final String INITIALSCENE_FXML = "initialScene.fxml";
    public static final String SCOREBOARD_FXML = "scoreBoard.fxml";
    public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
    public static final String GAME_LOCATION = "/src/resources/json";

    public static final String POPUP_FXML = "popup.fxml";
    public static final String ENGLISH = "English";
    public static final String Chinese = "Chinese";
    
    public static final String AUDIO_TEST = "/src/gamePlayer/audioTest.mp3";
    public static final String CURSOR_ATTACK_TEST = "/src/gamePlayer/Cursor_attack.png";
    public static final String CURSOR_GLOVE_TEST = "/gamePlayer/pointer-glove.png";

    private ResourceBundle myLanguages;
    private Stage myStage;
    private Game myModel;
    private GameGrid myGrid;
    private VBox myInitialScene;
    private BorderPane myGameSpace;
    private VBox myScoreBoard;
    private BorderPane myPopup;
    private Scene scoreScene;
    private Scene myPopupScene;
    private Scene myScene;
    
    private Point2D myCurrentLocation;
    private Piece activePiece;
    private Action activeAction;
 //   private Audio backGroundMusic;
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
    

    private IGridState gridState;

    public ViewController(Stage s){
        myStage = s;
       myInitialScene = new VBox();
       myGameSpace = new BorderPane();
       myScoreBoard = new VBox();
       myPopup = new BorderPane();
      //  myModel = new Game();
       //TODO:
       //uses JSON reader that takes in the file chosen by user and instantiate 
       // a new Game object. 
       //initialize audio
        
       // myGrid = new SquareGameGrid(8,8);
        
        loadFXML(GAMESPACE_FXML, myGameSpace);
        loadFXML(INITIALSCENE_FXML, myInitialScene);

        loadFXML(POPUP_FXML, myPopup);
        loadFXML(SCOREBOARD_FXML, myScoreBoard);
        scoreScene = new Scene(myScoreBoard);
        myPopupScene = new Scene(myPopup);
       // myPopup = FXMLLoader.load(getClass().getResource(POPUP_FXML));

        
      //  myGameSpace.setCenter(myGrid);
     //   myGrid.setAlignment(Pos.CENTER);
        myStage.setScene(new Scene(myInitialScene));

            try {
                newGame();
            }
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            }

        myStage.show();

    }


    /**
     * generates drop down menu that allow user to choose a new Game to play 
     * The Games are generated from the directory that stores all json files defined 
     * from authoring environment
     * @throws LineUnavailableException 
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    protected void newGame () throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        List<File> games = getGames();
       
        games.forEach(file->{ MenuItem l = new MenuItem();
        l.setText(file.getName().substring(0, file.getName().length()-5));
        l.setOnAction(event->{
           // myModel.initializeGame(file.getName());
            
           
           
            myAudio = new AudioClip(new File(System.getProperty("user.dir")+AUDIO_TEST).toURI().toString());
            myAudio.play();
//              try {
//                backGroundMusic = new Audio (System.getProperty("user.dir")+AUDIO_TEST);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                //e.printStackTrace();
//            }
//              backGroundMusic.play();
        });
        l.getStyleClass().add("button");
            newGameButton.getItems().add(l);

        });
   
      
    }
    
    private void loadFXML(String url, Node n){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(n);
        try{
            fxmlLoader.load();
        }
        catch(IOException exception) {
            throw new RuntimeException(exception);
        }
        
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
        File f = fc.showOpenDialog(myStage);
        
        // uses JSON reader to generate an instance of the game

    }

    @FXML
    private void testGame() {
        JSONBobTester JSBTester = new JSONBobTester();
        myModel = JSBTester.createNewGame();
        myGrid= new SquareGameGrid(myModel.getCurrentLevel().getGrid().getRow(), myModel.getCurrentLevel().getGrid().getColumn());
        myGameSpace.setCenter(myGrid);
        myGrid.populateGrid(myModel.getCurrentLevel().getGrid().getPatches(), myModel.getCurrentLevel().getGrid().getPieces());

        myScene = new Scene(myGameSpace);
        myStage.setScene(myScene);

        setGridState(new SelectState(this));
        changeCursor(CURSOR_GLOVE_TEST);
        getGrid().setOnMouseExited(event->{changeCursor(CURSOR_GLOVE_TEST);});
        myGrid.requestFocus();
        addKeyboardController();
//        addLocationSelector();
//        
    }

    
    @FXML
    private void doSettings(){

    }
    
    /**
     * loads the players and their scores of the current game;
     * display the Highest score in the high score display at the bottom
     */
    @FXML
    protected void showScore(){
        Stage stage = new Stage();
        stage.setScene(scoreScene);
        loadScores();
        stage.show();
        
    }
    
    @FXML
    protected void cancelPopup(){
        
    }
    
    protected void loadScores(){
        gameName.setText(gameName.getText()+"stubName");
        
        //TODO: add in scores
      //  myModel.getPlayers().forEach(player-> scores.getChildren().
       //                              add(new Text(player.getID()+": ")));
        
        
    }
    
    @FXML
    private void save(){
        
    }
    
    
    /**
     * The method to get all json files from the resources directory that 
     * stores all the games user has defined from the authoring environment
     */
    private List<File> getGames(){
        
       List<File> gameList =  new ArrayList<File>();
        
        File files = new File(System.getProperty("user.dir")+GAME_LOCATION);

            for (File f: files.listFiles()){

                if(f.getName().endsWith(".json")){
                    
                    gameList.add(f);
                }
            }
            return gameList;
    }
    /**
     * the method to restart the game; it asks the use whether to save the current game
     * 
     */
    @FXML
    protected void restartGame () {

        //System.out.println("restarting game");
       // myModel=new Game();
        
        Stage stage = new Stage();
       
        stage.setScene(myPopupScene);
        stage.show();
        stage.setAlwaysOnTop(true);
       // stage.addEventHandler(arg0, arg1);
        // Generate a new Game Object.

    }
    @FXML
    protected void exitGame () {
        myStage.close();

    }

    /**
     * to save the current game (state and settings) to a json file which could be later loaded in
     */
    @FXML
    protected void saveGame () {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showSaveDialog(myStage);
        // JSONWriter.write(f);

    }
    /**
     * Method to switch the state of the game grid between select mode 
     * and apply mode
     * 
     * @param state the current state of the Grid, select/ apply action Mode
     */
    protected void setGridState(IGridState state){
        gridState = state;
    }

    /**
     * Update the stats panel with stats from the selected piece
     * @param piece
     */
    protected void updateStats(Piece piece){
  
        statsPane.getChildren().clear();
        ArrayList<Text> stats = new ArrayList<Text>();
        piece.getStats().getStatsMap().keySet().forEach(key->stats.
                                                        add(new Text(key+ ":  "+ piece.getStats().
                                                         getStatsMap().get(key))));
        statsPane.getChildren().addAll(stats);

    }

    /**
     * Update the action panel with actions from the selected piece
     * @param piece
     */
    protected void updateActions (Piece piece){
        
        controlPane.getChildren().clear();
        ArrayList<Label> actions = new ArrayList<Label>();
        piece.getActions().forEach(action->{Label l = new Label(action.toString()); 
        l.setOnMouseClicked(event->bindAction(action));
        actions.add(l);});

        controlPane.getChildren().addAll(actions);

    }

    /**
     * Method called when user clicks an action button 
     * @param action
     */
    private void bindAction(Action action){
        setActiveAction(action);
        highLightActionRange();

        setGridState(new ApplyState(this));
    }

    private void setOnClick(){
        myGrid.setOnMouseClicked(event->{performAction(event.getX(), event.getY());});
    }

    /**
     * Perform the actions of a click at position (x,y) on game grid
     * @param x
     * @param y
     */
    private void performAction (double x, double y) {
         gridState.onClick(getPiece(findPosition(x,y)));

    }

    /**
     * Method to convert pixel coordinates into tile coordinates
     * @param x
     * @param y
     * @return a Point2D representing tile coordinates
     */
    protected Point2D findPosition(double x, double y){
        double patchHeight = (double) myGrid.getHeight()/(double) myGrid.getCol();
        double patchWidth = (double) myGrid.getWidth()/(double) myGrid.getRow();
        int xCor = (int) (x/patchWidth);
        int yCor = (int) (y/patchHeight);
        return new Point2D.Double(xCor,yCor);
    }

        private Piece getPiece(Point2D loc){
            return myModel.getCurrentLevel().getGrid().getPiece(loc);
            
        }
    protected GameGrid getGrid(){
        return myGrid;
    }

    protected Game getGame(){
        return myModel;
    }
       
    protected void setActivePiece(Piece piece){
        activePiece = piece;
    }
    protected Piece getActivePiece(){
        return activePiece;
    }

    protected void setActiveAction(Action action){
        activeAction = action;
    }
    protected Action getActiveAction(){
        return activeAction;
    }

    /**
     * Highlight the tiles that represent the possible range of the action
     * selected
     */
    @FXML
    private void highLightActionRange(){
        //TODO: getActionRange()shouldn't need a location. action is contained in piece, which knows its own location.
        //  activeAction.getActionRange(activePiece.getLoc());


        //temparary stub for testing highlight;
        activeAction.getActionRange(activePiece.getLoc()).forEach(point->{ Node n = myGrid.get((int)point.getX(), (int)point.getY());
        addDropShadow(n, Color.YELLOW);
        n.setOnMouseEntered(event->highLightEffectRange(n, Color.RED));
        n.setOnMouseExited(event->highLightEffectRange(n, Color.TRANSPARENT));

        });





    }

    protected void changeCursor(String string){
        Image image = new Image(string);
        myScene.setCursor(new ImageCursor(image, image.getWidth()/4,image.getWidth()/4));
    }
    private void addDropShadow(Node n, Color c){
        DropShadow ds = new DropShadow(); 
        ds.setRadius(30.0);
        ds.setOffsetX(0.0);
        ds.setOffsetY(0.0);
        ds.setColor(c);
        n.setEffect(ds); 
        System.out.println("drop shadow");
    }
    

    /**
     * Highlight the effect range of an action if to be applied at a given position
     * @param n
     * @param c
     */
    private void highLightEffectRange(Node n, Color c){

        //  activeAction.getEffectRange();
        activeAction.getEffectRange().forEach(point->{Node node = myGrid.get(myGrid.getRowIndex(n)+ (int)point.getX(), myGrid.getColumnIndex(n)+ (int)point.getY());

        if(c ==Color.TRANSPARENT && node.getEffect()!=null){
            node.setEffect(null);
        }
        else{
            addDropShadow(node, c);
        }


        });
    }
    
//    private void addLocationSelector () {
//        myGrid.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle (MouseEvent event) {
////                myCurrentLocation = new Point2D.Double(r.getX(), r.getY());
//                System.out.println("hi");
//            }
//        });
//    }
    
    private void addKeyboardController(){
        KeyboardController KBControl = new KeyboardController();
//        KBControl.setActionKeyControl(myGrid, myModel);
        KBControl.setMovementKeyControl(this, myGrid, myModel);
    }
    
    public void highlightCurrentLocation(Color c, Point2D oldLocation, Point2D newLocation){
        Node oldNode = myGrid.get((int)oldLocation.getX(), (int)oldLocation.getY());
        Node newNode = myGrid.get((int)newLocation.getX(), (int)newLocation.getY());
        oldNode.setEffect(null);
        addDropShadow(newNode, c);
    }

   
}
