package gamePlayer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start (Stage arg0) throws Exception {
        
       new ViewController(arg0);
   
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}