package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

//TODO: pupulate Grid by actual pieces and patches;

//TODO: currently, the "testAction" button is set on action to show highlightActionRange, 
//      still needs to add highlighting effect to follow the hover;
//      and to make dropshadow effect more obvious!!!

public class ViewController extends BorderPane{

    public static final String GAMESPACE_FXML = "gameSpace.fxml";


    private Stage myStage;
    private DummyGame myModel;
    private GameGrid myGrid;
    // Temparary stub!! for testing purposes;;;
    private List<Action> myActions;
    private Map<String, Double> myStats;
    
    private Piece activePiece;
    private Action activeAction;

    @FXML
    protected VBox statsPane;
    @FXML
    private VBox controlPane;

    private GridState gridState;

    public ViewController(Stage s){
        myStage = s;
        myStage.setFullScreen(true);
        myModel = new DummyGame("VOOGASALAD!!");
        myGrid = new SquareGameGrid(8,8, null, null);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GAMESPACE_FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }
        catch(IOException exception) {
            throw new RuntimeException(exception);
        }
        this.setCenter(myGrid);
        myGrid.setAlignment(Pos.CENTER);
        myStage.setScene(new Scene(this));

    }

   
    @FXML
    protected void loadGame () {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        File f = fc.showOpenDialog(myStage);

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
        myModel.store(f);

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
        myStats = new HashMap<String, Double>();
        statsPane.getChildren().clear();
        ArrayList<Text> stats = new ArrayList<Text>();
        myStats.keySet().forEach(key->stats.add(new Text(key+ ":  "+ myStats.get(key))));
        statsPane.getChildren().addAll(stats);

    }

    //TODO: replace myActions with Game.get.get...
    /**
     * Update the action panel with actions from the selected piece
     * @param piece
     */
    protected void updateActions (Piece piece){
        myActions = new ArrayList<Action>();
        controlPane.getChildren().clear();
        ArrayList<Label> actions = new ArrayList<Label>();
        myActions.forEach(action->{Label l = new Label(action.toString()); 
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
       // highLightEffectRange();
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
        // gridState.onClick(getPiece(findPosition(x,y)));

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
        return new Point2D(xCor,yCor);
    }

    //    private Piece getPiece(Point2D loc){
    //       // return myModel.getCurrentLevel().getGrid().getPiece(loc);
    //        
    //    }
    protected GameGrid getGrid(){
        return myGrid;
    }

    protected DummyGame getGame(){
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
        myModel.getActionRange().forEach(point->{ Node n = get((int)point.getX(), (int)point.getY());
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
        myModel.getEffectRanges().forEach(point->{Node node = get(myGrid.getRowIndex(n)+ (int)point.getX(), myGrid.getColumnIndex(n)+ (int)point.getY());
           
           if(c ==Color.TRANSPARENT && node.getEffect()!=null){
               node.setEffect(null);
           }
           else{
               addDropShadow(node, c);
           }
           });
    }


    private Node get(int row, int col){
        Node result = null;
        ObservableList<Node> childrens = myGrid.getChildren();
        for(Node node : childrens) {
          // System.out.println("myGrid has a node at:" + myGrid.getRowIndex(node));
            if(myGrid.getRowIndex(node) == row && myGrid.getColumnIndex(node) == col) {
                result = node;
                break;
            }
        }
        return result;
    }

}
