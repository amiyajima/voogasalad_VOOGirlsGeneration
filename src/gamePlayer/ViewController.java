package gamePlayer;

import gamedata.action.Action; 
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


//TODO: need valid grid and Game constructor from back end. 


public class ViewController{

    public static final String GAMESPACE_FXML = "gameSpace.fxml";
    public static final String INITIALSCENE_FXML = "initialScene.fxml";
    public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
    public static final String GAME_LOCATION = "/src/resources";


    private Stage myStage;
    private Game myModel;
    private GameGrid myGrid;
    private VBox myInitialScene;
    private BorderPane myGameSpace;
   
    private Piece activePiece;
    private Action activeAction;
    private List<File> myGames;

    @FXML 
    private VBox statsPane;
    @FXML
    private VBox controlPane;
    @FXML
    private MenuButton newGameButton;

    private GridState gridState;

    public ViewController(Stage s){
        myStage = s;
       myInitialScene = new VBox();
       myGameSpace = new BorderPane();
        myModel = new Game();
        myGames = new ArrayList<File>();
       // myGrid = new SquareGameGrid(8,8);
        FXMLLoader fxmlLoaderGame = new FXMLLoader(getClass().getResource(GAMESPACE_FXML));
        fxmlLoaderGame.setController(this);
        fxmlLoaderGame.setRoot(myGameSpace);
        
        FXMLLoader fxmlLoaderInit = new FXMLLoader(getClass().getResource(INITIALSCENE_FXML));
        fxmlLoaderInit.setController(this);
        fxmlLoaderInit.setRoot(myInitialScene);
        

        try{
            fxmlLoaderGame.load();
            fxmlLoaderInit.load();
        }
        catch(IOException exception) {
            throw new RuntimeException(exception);
        }
        myGameSpace.setCenter(myGrid);
        myGrid.setAlignment(Pos.CENTER);
        myStage.setScene(new Scene(myInitialScene));
        newGame();
        myStage.show();

    }


    private void newGame () {
        getGames();
        System.out.println(myGames.size());
        myGames.forEach(file->{ MenuItem l = new MenuItem();
        l.setText(file.getName());
        l.setOnAction(event->{
           // myModel.initializeGame(file.getName());
            myStage.setScene(new Scene(myGameSpace));

        });
        l.getStyleClass().add("button");
            newGameButton.getItems().add(l);

        });
        
        
    }


    @FXML
    protected void loadGame () {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        File f = fc.showOpenDialog(myStage);

    }
    
    @FXML
    private void doSettings(){

    }
    
    private void getGames(){
        
        File files = new File(System.getProperty("user.dir")+GAME_LOCATION);

            for (File f: files.listFiles()){

                if(f.getName().endsWith(".json")){
                    
                    myGames.add(f);
                }
            }
    }
    
    @FXML
    protected void restartGame () {

        System.out.println("restarting game");

    }
    @FXML
    protected void exitGame () {
        myStage.close();

    }

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
     * @param state
     */
    protected void setGridState(GridState state){
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
     * Highlight the effect range of an action applied at a given position
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
