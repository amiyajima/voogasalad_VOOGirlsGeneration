package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ViewController{

    public static final String GAMESPACE_FXML = "gameSpace.fxml";
    public static final String INITIALSCENE_FXML = "initialScene.fxml";
    public static final String SCOREBOARD_FXML = "scoreBoard.fxml";
    public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
    public static final String GAME_LOCATION = "/src/resources";


    private Stage myStage;
    private Game myModel;
    private GameGrid myGrid;
    private VBox myInitialScene;
    private BorderPane myGameSpace;
    private BorderPane myScoreBoard;
   
    private Piece activePiece;
    private Action activeAction;

    @FXML
    protected VBox statsPane;

    @FXML
    private VBox controlPane;
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
       myScoreBoard = new BorderPane();
      //  myModel = new Game();
       //TODO:
       //uses JSON reader that takes in the file chosen by user and instantiate 
       // a new Game object. 
        
        myGrid = new SquareGameGrid(8,8);
        
        
        loadFXML(GAMESPACE_FXML, myGameSpace);
        loadFXML(INITIALSCENE_FXML, myInitialScene);
   //     loadFXML(SCOREBOARD_FXML, myScoreBoard);
        
        myGameSpace.setCenter(myGrid);
        myGrid.setAlignment(Pos.CENTER);
        myStage.setScene(new Scene(myInitialScene));
       
        newGame();
        myStage.show();

    }


    /**
     * generates drop down menu that allow user to choose a new Game to play 
     * The Games are generated from the directory that stores all json files defined 
     * from authoring environment
     */
    protected void newGame () {
        List<File> games = getGames();
       
        games.forEach(file->{ MenuItem l = new MenuItem();
        l.setText(file.getName());
        l.setOnAction(event->{
           // myModel.initializeGame(file.getName());
            myStage.setScene(new Scene(myGameSpace));

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
    private void doSettings(){

    }
    
    /**
     * loads the players and their scores of the current game;
     * display the Highest score in the high score display at the bottom
     */
    @FXML
    protected void showScore(){
        Stage stage = new Stage();
        stage.setScene(new Scene(myScoreBoard));
        loadScores();
        stage.show();
        
    }
    
    protected void loadScores(){
        gameName.setText(myModel.toString());
        
        //TODO: add in scores
        myModel.getPlayers().forEach(player-> scores.getChildren().
                                     add(new Text(player.getID()+": ")));
        
        
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
    // TODO: IMPLEMENT POP-UP.
    @FXML
    protected void restartGame () {

        //System.out.println("restarting game");
       // myModel=new Game();
        
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
    private Point2D findPosition(double x, double y){
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

    private void addDropShadow(Node n, Color c){
        DropShadow ds = new DropShadow(); 
        ds.setRadius(10.0);
        ds.setOffsetX(3.0);
        ds.setOffsetY(3.0);
        ds.setColor(c);
        n.setEffect(ds); 
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


}
