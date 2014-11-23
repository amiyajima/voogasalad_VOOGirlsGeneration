package gamePlayer;

import gamedata.gamecomponents.Game;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tests.JSONBobTester;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class InitialSceneController{

    public static final String INITIALSCENE_FXML = "initialScene.fxml";
    public static final String INITIALSCENE_TITLE = "VOOGASALAD!";
    public static final String GAME_LOCATION = "/src/resources";

    private Stage myStage;
    private DummyGame myGame;
    private VBox myRoot;
    private Game myTestGame;

    @FXML
    private MenuButton newGameButton;
    private List<File> myGames;


    public InitialSceneController(Stage s){

        myStage = s;
        myRoot = new VBox();
      //  myRoot = FXMLLoader.load(getClass().getResource(INITIALSCENE_FXML));
        myGame= new DummyGame("VOOGASALAD!!");
        myGames = new ArrayList<File>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(INITIALSCENE_FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(myRoot);
        
        try{
            fxmlLoader.load();
        }
        catch(IOException exception) {
            throw new RuntimeException(exception);
        }
        
        myStage.setScene(new Scene(myRoot));
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
          //  new ViewController(myStage);

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
        myGame = new DummyGame(f);

    }
    @FXML
    private void doSettings(){

    }
    
    @FXML
    private void testGame() {
//        JSONBobTester JSBTester = new JSONBobTester();
//        myTestGame = JSBTester.createNewGame();
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