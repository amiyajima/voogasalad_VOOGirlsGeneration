package gamePlayer;

import gamedata.action.Action; 
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;



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
    public static final String CURSOR_ATTACK_TEST = "/gamePlayer/Cursor_attack.png";
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

  //  private Point2D myCurrentLocation;
    private Piece activePiece;
    private Action activeAction;
    
    //private MouseController myMouseController;
    
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

        //TODO:
        //uses JSON reader that takes in the file chosen by user and instantiate 
        // a new Game object. 

        loadFXML(GAMESPACE_FXML, myGameSpace);
        loadFXML(INITIALSCENE_FXML, myInitialScene);

        loadFXML(POPUP_FXML, myPopup);
        loadFXML(SCOREBOARD_FXML, myScoreBoard);
        scoreScene = new Scene(myScoreBoard);
        myPopupScene = new Scene(myPopup);

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

            myAudio = new AudioClip(new File(System.getProperty("user.dir")+AUDIO_TEST).toURI().toString());
            myAudio.play();

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


        myScene = new Scene(myGameSpace);
        myStage.setScene(myScene);
        initializeGrid();

    }

    private void initializeGrid(){
        JSONBobTester JSBTester = new JSONBobTester();
        myModel = JSBTester.createNewGame();
        

        myGrid= new SquareGameGrid(myModel.getCurrentLevel().getGrid().getRow(), myModel.getCurrentLevel().getGrid().getColumn());
        myGameSpace.setCenter(myGrid);
        myGrid.setAlignment(Pos.CENTER);
        myGrid.populateGrid(myModel.getCurrentLevel().getGrid().getPatches(), myModel.getCurrentLevel().getGrid().getPieces());
        myModel.getLevels().forEach(level->level.addObserver(this.myGrid));
        setOnClick();

        setGridState(new SelectState(this));
//        myMouseController.setCursorImage(myScene, myGrid, CURSOR_GLOVE_TEST);
//        myMouseController.setOnClick(this, gridState, myGrid);
        

        
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
        gameName.setText(gameName.getText()+myModel.toString());

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


        //        Stage stage = new Stage();
        //
        //        stage.setScene(myPopupScene);
        //        stage.show();
        //        stage.setAlwaysOnTop(true);
        initializeGrid();
        statsPane.getChildren().clear();
        controlPane.getChildren().clear();


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
    public void setGridState(IGridState state){
        gridState = state;
    }

    /**
     * Update the stats panel with stats from the selected piece
     * @param piece
     */
    protected void updateStats(Piece piece){

        statsPane.getChildren().clear();
        ArrayList<Text> stats = new ArrayList<Text>();

        piece.getStats().getStatNames().forEach(key->stats.
                                                add(new Text(key+ ":  "+ piece.getStats().getValue(key))));

        statsPane.getChildren().addAll(stats);

    }

    /**
     * Update the action panel with actions from the selected piece
     * @param piece
     */
    protected void updateActions (Piece piece){
        // setActivePiece(piece);

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
       // doThis();

        setGridState(new ApplyState(this));
    }


    private void setOnClick(){
        myGrid.setOnMouseClicked(event->{ 
            performAction(event.getX(), event.getY());});
    }


    /**
     * Perform the actions of a click at position (x,y) on game grid
     * @param x
     * @param y
     */
    private void performAction (double x, double y) {
        System.out.println("current mouse location:"+x +", "+y);
        gridState.onClick(getPiece(findPosition(x,y)));
        myGrid.clearEffect();
        addDropShadow(myGrid.get(((int)findPosition(x,y).getX()), ((int)findPosition(x,y).getY())), Color.PURPLE);
    }


    /**
     * Method to convert pixel coordinates into tile coordinates
     * @param x
     * @param y
     * @return a Point2D representing tile coordinates
     */
    public Point2D findPosition(double x, double y){
        double patchHeight = (double) myGrid.getHeight()/(double) myGrid.getCol();
        double patchWidth = (double) myGrid.getWidth()/(double) myGrid.getRow();
        int xCor = (int) (x/patchWidth);
        int yCor = (int) (y/patchHeight);

        return new Point2D.Double(yCor,xCor);
    }

    public Piece getPiece(Point2D loc){
        return myModel.getCurrentLevel().getGrid().getPiece(loc);
    }

    protected Scene getScene(){
        return myScene;
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
    protected void highLightActionRange(){
        System.out.println("activePiece at "+activePiece.getLoc());
        System.out.println("action range: "+ activeAction.getActionRange(activePiece.getLoc()));

        activeAction.getActionRange(activePiece.getLoc()).forEach(point->{

            if(point.getX()<myGrid.getRow() && point.getY()<myGrid.getCol() && point.getX()>0 && point.getY()>0){
                Node n = myGrid.get((int)point.getX(),(int)point.getY());
                addDropShadow(n, Color.YELLOW);

                }


        });
    }
    
//    private void doThis(){
//      Node test = myGrid.get(0, 0);
//      test.setOnMouseEntered(event->highLightEffectRange(test,Color.RED));
//        
////        for (Node n: activeNodes){
////            n.setOnMouseEntered(event->highLightEffectRange(n,Color.RED));
////        }
//    }
    
    
    private void addDropShadow(Node n, Color c){
        if(n != null){
            DropShadow ds = new DropShadow(); 
            ds.setRadius(30.0);
            ds.setOffsetX(0.0);
            ds.setOffsetY(0.0);
            ds.setColor(c);
            n.setEffect(ds); 

        }
    }


    /**
     * Highlight the effect range of an action if to be applied at a given position
     * @param n
     * @param red
     */

    protected void highLightEffectRange(MouseEvent me, Color c){
       
        activeAction.getActionRange(activePiece.getLoc()).forEach(point->{
            Point2D temp= findPosition(me.getSceneX(), me.getSceneY());
            if(temp.equals(point)){
                activeAction.getEffectRange().forEach(point2->{
                    Node n = myGrid.get((int)(temp.getX()+point2.getX()), (int)(temp.getY()+point2.getY()));
                    addDropShadow(n, c);
                });
                
                
            }
        });

        
    }

    
    public IGridState getGridState(){
        return gridState;
    }

    
    public void changeCursor(String filename){
        Image image = new Image(filename);
        myScene.setCursor(new ImageCursor(image, image.getWidth()/4,image.getWidth()/4));
    
    }
    

    public void highlightCurrentLocation(Color c, Point2D oldLocation, Point2D newLocation){
        Node oldNode = myGrid.get((int)oldLocation.getX(), (int)oldLocation.getY());
        Node newNode = myGrid.get((int)newLocation.getX(), (int)newLocation.getY());
        oldNode.setEffect(null);
        addDropShadow(newNode, c);
    }
}
