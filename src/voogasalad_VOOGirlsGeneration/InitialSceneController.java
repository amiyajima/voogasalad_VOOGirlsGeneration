package voogasalad_VOOGirlsGeneration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class InitialSceneController extends VBox{

    public static final String INITIALSCENE_FXML = "initialScene.fxml";
    public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
    public static final String GAME_LOCATION = "/src/resources";

    private Stage myStage;
    private Game myGame;

    @FXML
    private MenuButton newGameButton;
    private List<File> myGames;


    public InitialSceneController(Stage s){

        myStage = s;
        myGame= new Game("VOOGASALAD!!");
        myGames = new ArrayList<File>();
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
        newGame();

        myStage.show();


    }

    
    private void newGame(){

        getGames();
        System.out.println(myGames.size());
        myGames.forEach(file->{ MenuItem l = new MenuItem();
        l.setText(file.getName());
        l.setOnAction(event->{
            myGame.initializeGame(file.getName());
            new ViewController(myStage);

        });
        l.getStyleClass().add("button");
        newGameButton.getItems().add(l);

        });
    }

    @FXML
    private void loadGame(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        File f = fc.showOpenDialog(myStage);
        myGame = new Game(f);

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




}
