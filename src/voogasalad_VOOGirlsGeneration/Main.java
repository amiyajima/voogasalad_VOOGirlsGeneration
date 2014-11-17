package voogasalad_VOOGirlsGeneration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start (Stage arg0) throws Exception {
        
        ViewController vc = new ViewController(arg0);
       // arg0.setScene(new Scene(vc));
        arg0.show();
 
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
    

}
