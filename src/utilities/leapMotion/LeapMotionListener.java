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
import com.leapmotion.leap.SwipeGesture;

/**
 * LeapMotionListener is the class that configures and executes user-defined input actions from the leap motion controller.
 * It translates leap motion gestures into mouse and keyboard actions according to gesture/action mappings read from a properties file. 
 *
 */
public class LeapMotionListener extends Listener{
    
    private ResourceBundle myGestures;
    private Robot myRobot;
    private ILeapMouse myLeapMouse;
    public static final String PACKAGE_FILEPATH = "utilities.leapMotion.";
    public static final String GESTURE_BUNDLE = "Untitled";
    public static final String MOUSE_MOVE_FLAG = "mouse";
    public static final String MOUSE_FOLDER = "mouseControl.";
    public static final String DEFAULT_MOUSE = "TYPE_SCREEN_TAP";
    public static final String TAP_MIN_V = "Gesture.ScreenTap.MinForwardVelocity";
    public static final String TAP_HIST_TIME = "Gesture.ScreenTap.HistorySeconds";
    public static final String TAP_MIN_DIST = "Gesture.ScreenTap.MinDistance";
    
    public static final double MIN_FORWARD_VELOCITY = 30.0;
    public static final double HISTORY_SECONDS = .5;
    public static final double MIN_DISTANCE = 1.0;

    /* (non-Javadoc)
     * @see com.leapmotion.leap.Listener#onConnect(com.leapmotion.leap.Controller)
     */
    public void onConnect (Controller controller){

        initializeRobot();
        myGestures = ResourceBundle.getBundle(PACKAGE_FILEPATH+GESTURE_BUNDLE);
       
        enableGestures(controller);
    }

    /**
     * Enable all gestures that are specified in the properties file. Configure gestures when necessary.
     * @param controller
     */
    private void enableGestures(Controller controller){
        for(String gestureName : myGestures.keySet()){
         
            if(gestureName.equals(MOUSE_MOVE_FLAG)){
             
                Class c = null;
                try {
                    c = Class.forName(PACKAGE_FILEPATH + MOUSE_FOLDER+myGestures.getString(gestureName));

                    myLeapMouse = (ILeapMouse) c.newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {

                }

            }
            for (Gesture.Type type: Gesture.Type.values()){
                if(type.toString().equals(gestureName)){
                    
                    controller.enableGesture(type);
                    if(gestureName.equals(DEFAULT_MOUSE)){
                        
                        configScreenTap(controller);
                    }
                   
                    
                }
            }

        }
    }

    /**
     * Set parameters for the screen tap gesture to allow better performance.
     * @param controller
     */
    private void configScreenTap (Controller controller) {
        controller.config().setFloat(TAP_MIN_V, (float) MIN_FORWARD_VELOCITY);
            
        controller.config().setFloat(TAP_HIST_TIME, (float) HISTORY_SECONDS);
        controller.config().setFloat(TAP_MIN_DIST, (float) MIN_DISTANCE);
        controller.config().save();
    }
    /**
     * Create a robot to execute mouse and keyboard actions.
     */
    private void initializeRobot ()  {
    
            try {
                myRobot = new Robot();
            } catch (AWTException e) {
               
            }
        
    }
    /* (non-Javadoc)
     * @see com.leapmotion.leap.Listener#onFrame(com.leapmotion.leap.Controller)
     */
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

    /**
     * Perform the mouse or keyboard action that the gesture is mapped to.
     * @param gestureName
     */
    private void performAction(String gestureName){
        
        if(myGestures.getString(gestureName).length()>1){
            int mouseInput = Integer.parseInt(myGestures.getString(gestureName));
            myRobot.mousePress(mouseInput);
            myRobot.mouseRelease(mouseInput);
        }
        else{
            int keyInput = Character.toUpperCase(myGestures.getString(gestureName).charAt(0));


            myRobot.keyPress(keyInput);
        }
       
    }


}


