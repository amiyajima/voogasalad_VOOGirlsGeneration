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
    public static final String PACKAGE_FILEPATH = "utilities.leapMotion.";
    public static final String GESTURE_BUNDLE = "Untitled";
    public static final String MOUSE_MOVE_FLAG = "mouse";


    public void onConnect (Controller controller){

        initializeRobot();
        myGestures = ResourceBundle.getBundle(PACKAGE_FILEPATH+GESTURE_BUNDLE);
        System.out.println("connected");
        enableGestures(controller);
    }

    private void enableGestures(Controller controller){
        for(String gestureName : myGestures.keySet()){
            // System.out.println(gestureName+" mapped to"+ myGestures.getString(gestureName));
            if(gestureName.equals(MOUSE_MOVE_FLAG)){
                System.out.println("STARTING REFLECTION");
                Class c = null;
                try {
                    c = Class.forName(PACKAGE_FILEPATH+"mouseControl."+myGestures.getString(gestureName));

                    myLeapMouse = (ILeapMouse) c.newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {

                }

            }
            for (Gesture.Type type: Gesture.Type.values()){
                if(type.toString().equals(gestureName)){
                    controller.enableGesture(type);
                    System.out.println(type.toString()+"  enabled");
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


