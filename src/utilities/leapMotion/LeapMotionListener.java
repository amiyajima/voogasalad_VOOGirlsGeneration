package utilities.leapMotion;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ResourceBundle;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

public class LeapMotionListener extends Listener{
    private ResourceBundle myGestures;
    private Robot myRobot;
    private ICustomControl myLeapMouse;
    public static final String GESTURES_FILEPATH = "";
    
    
    public void onConnect (Controller controller){
    
        initializeRobot();
        myGestures = ResourceBundle.getBundle(GESTURES_FILEPATH);
        enableGestures();
    }
    private void enableGestures(){
        
    }
    private void initializeRobot () {
        try {
            myRobot = new Robot();
        } catch (AWTException e) {
            
        }
    }
    public void onFrame (Controller controller) {
        Frame frame = controller.frame();
        myLeapMouse.control(frame, myRobot);
        for(Gesture gesture: frame.gestures()){
            for(String gestureName : myGestures.keySet()){
                if(gesture.type()==Enum.valueOf(Gesture.Type.class, gestureName)){
                    performAction(gestureName);
                }
            }
        }
    }
   
    private void performAction(String gestureName){
        int input = Integer.parseInt(myGestures.getString(gestureName));
        if(input==InputEvent.BUTTON1_DOWN_MASK){
        myRobot.mousePress(input);
        }
        else{
            myRobot.keyPress(input);
        }
    }

}
