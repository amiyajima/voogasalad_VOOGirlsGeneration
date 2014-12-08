package utilities.leapMotion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.leapmotion.leap.Controller;

public class TestLeapControl extends Application{
    
    public static void main(String[] args){
  
       launch();
        
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Controller leapController = new Controller();
        LeapMotionListener listener = new LeapMotionListener();
        leapController.addListener(listener);
        Scene s= new Scene(new TextField());
        arg0.setScene(s);
        arg0.show();
        
        
    }

}
