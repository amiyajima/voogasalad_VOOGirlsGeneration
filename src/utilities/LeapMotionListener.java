package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ResourceBundle;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

public class LeapMotionListener extends Listener{
    ResourceBundle myGestureEffect;
    Robot myRobot;
    ILeapMouse myLeapMouse;
    public static final String GESTURE_FILEPATH = "";
    
    public void onConnect (Controller controller){
    
        initializeRobot();
        myGestureEffect = ResourceBundle.getBundle(baseName)
        enableGestures();
    }
    public void enableGestures(){
        
    }
    private void initializeRobot () {
        try {
            myRobot = new Robot();
        } catch (AWTException e) {
            
        }
    }
    public void onFrame (Controller controller) {
        Frame frame = controller.frame();
        myLeapMouse.mouseMove(frame, myRobot);
        
    }
    

}
