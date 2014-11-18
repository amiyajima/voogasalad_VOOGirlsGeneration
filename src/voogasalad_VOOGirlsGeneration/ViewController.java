package voogasalad_VOOGirlsGeneration;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ViewController extends BorderPane{
    
    public static final String GAMESPACE_FXML = "gameSpace.fxml";
    

    private Stage myStage;
    private Game myModel;
    private GameGrid myGrid;
    
   
    @FXML
    private VBox statsPane;
    private GridState gridState;

    public ViewController(Stage s){
        myStage = s;
        myModel = new Game("VOOGASALAD!!");
      //  myGrid = new SquareGameGrid(5,5);
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
    
    
    private void updateStats(){
        statsPane.getChildren().clear();
        statsPane.getChildren().add(new Text(myModel.getStats()));
    }
    
    private void clickOnGrid(){
        gridState.onClick();
    }
    
    protected GameGrid getGrid(){
        return myGrid;
    }
    
    protected Game getGame(){
        return myModel;
    }

}
