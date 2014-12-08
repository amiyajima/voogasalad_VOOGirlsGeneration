package utilities.leapMotion;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

public class LeapMotionListener extends Listener{
    private ResourceBundle myGestures;
    private Robot myRobot;
    private ILeapMouse myLeapMouse;
    public static final String GESTURES_FILEPATH = "";
    public static final String MOUSE_MOVE_FLAG = "mouse";
    
    
    public void onConnect (Controller controller){
    
        initializeRobot();
        myGestures = ResourceBundle.getBundle(GESTURES_FILEPATH);
        
        enableGestures(controller);
    }
   
    private void enableGestures(Controller controller){
        for(String gestureName : myGestures.keySet()){
            if(gestureName.equals(MOUSE_MOVE_FLAG)){
               try {
                Class c = Class.forName("utilities.leapMotion."+myGestures.getString(gestureName));
                myLeapMouse = (ILeapMouse) c.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                
            }
                
            }
            for (Gesture.Type type: Gesture.Type.values()){
                if(type.toString().equals(gestureName)){
                    controller.enableGesture(type);
                }
            }
           
        }
    }
    private void initializeRobot () {
        try {
            myRobot = new Robot();
        } catch (AWTException e) {
            
        }
    }
    public void onFrame (Controller controller) {
        Frame frame = controller.frame();
        myLeapMouse.moveMouse(frame, myRobot);
        for(Gesture gesture: frame.gestures()){
            for(String gestureName : myGestures.keySet()){
                if(gesture.type().toString().equals(gestureName)){
                    performAction(gestureName);
                }
            }
        }
    }
   
    private void performAction(String gestureName){
      //  int input = Integer.parseInt(myGestures.getString(gestureName));
       int input = gestureName.charAt(0);
        try {
            myRobot.mousePress(input);
        } catch (Exception e) {
            myRobot.keyPress(input);
        }
        
      
    }

}
