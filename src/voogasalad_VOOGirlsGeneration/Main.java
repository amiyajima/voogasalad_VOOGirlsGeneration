package voogasalad_VOOGirlsGeneration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start (Stage arg0) throws Exception {
        
       new InitialSceneController(arg0);
       // arg0.show();
 
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
    

}
