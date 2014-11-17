package voogasalad_VOOGirlsGeneration;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class InitialSceneController extends VBox{
    
    public static final String INITIALSCENE_FXML = "initialScene.fxml";
    public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
    
    private Stage myStage;
    private Game myGame;

    
    public InitialSceneController(Stage s){
        myStage = s;
        myGame= new Game("VOOGASALAD!!");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(INITIALSCENE_FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }
        catch(IOException exception) {
            throw new RuntimeException(exception);
        }
        myStage.setScene(new Scene(this));

        myStage.show();
        
        
    }
    
    @FXML
    private void newGame(){
        
        Stage stage = new Stage();
        VBox rt = new VBox();
        myGame.getGames().forEach(string->{ Button l = new Button(string);
        l.setOnAction(event->{
            myGame.initializeGame(string);
            stage.close();
            new ViewController(myStage);

        });
        rt.getChildren().add(l);
        
    });
        Scene s = new Scene(rt, 400, 300);
        stage.setScene(s);
        s.getStylesheets().add("stylesheet.css");
        stage.show();
       
    }
    
    @FXML
    private void loadGame(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        File f = fc.showOpenDialog(myStage);
 
    }
    @FXML
    private void doSettings(){
        
    }
    

   

}
