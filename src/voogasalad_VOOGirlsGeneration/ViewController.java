package voogasalad_VOOGirlsGeneration;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ViewController extends BorderPane{

    private Stage myStage;
    private GameModel myModel;
    private GameGrid myGrid;
    
//    @FXML
//    private GridPane squareGrid;
//    
    @FXML
    private VBox statsPane;
    private GridState gridState;

    public ViewController(Stage s){
        myStage = s;
        myModel = new GameModel();
        myGrid = new SquareGameGrid(5,5);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameSpace.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }
        catch(IOException exception) {
            throw new RuntimeException(exception);
        }
        this.setCenter(myGrid);
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
//        myFileChooser.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
//        File f = myFileChooser.showOpenDialog(myStage);
        System.out.println("restarging game");

    }
    @FXML
    protected void exitGame () {


    }
    
    @FXML
    protected void saveGame () {
        
       

    }
    
    
    private void updateStats(){
        statsPane.getChildren().add(new Text(myModel.getStats()));
    }
    
    private void clickOnGrid(){
        gridState.onClick();
    }
    



}
