package voogasalad_VOOGirlsGeneration;

import gamedata.gamecomponents.Piece;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ViewController extends BorderPane{

    public static final String GAMESPACE_FXML = "gameSpace.fxml";


    private Stage myStage;
    private Game myModel;
    private GameGrid myGrid;
    private Map<String, Double> myStats;
    private Piece activePiece;

    @FXML
    private VBox statsPane;
    @FXML
    private VBox 
    private GridState gridState;

    public ViewController(Stage s){
        myStage = s;
        myModel = new Game("VOOGASALAD!!");
        myGrid = new SquareGameGrid(5,5, null, null);
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
        s.setScene(new Scene(this));

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


    protected void updateStats(Piece piece){
//        statsPane.getChildren().clear();
//        statsPane.getChildren().add(new Text(myModel.getStats()));
    statsPane.getChildren().clear();
    ArrayList<Text> stats = new ArrayList<Text>();
    myStats.keySet().forEach(key->stats.add(new Text(key+ ":  "+ myStats.get(key))));
    statsPane.getChildren().addAll(stats);
    
    }
    protected void updateActions (Piece piece){
        
    }




    private void setOnClick(){
        myGrid.setOnMouseClicked(event->{performAction(event.getX(), event.getY());});
    }

    private void performAction (double x, double y) {
        gridState.onClick(getPiece(findPosition(x,y)));

    }
    private Point2D findPosition(double x, double y){
        double patchHeight = (double) myGrid.getHeight()/(double) myGrid.getCol();
        double patchWidth = (double) myGrid.getWidth()/(double) myGrid.getRow();
        int xCor = (int) (x/patchWidth);
        int yCor = (int) (y/patchHeight);
        return new Point2D(xCor,yCor);
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

}
