// This entire file is part of my masterpiece.
// Eric Chen
package utilities.leapMotion;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ResourceBundle;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

import fxml_main.ErrorPopUp;

/**
 * LeapMotionListener is the class that configures and executes user-defined
 * input actions from the leap motion controller. It translates leap motion
 * gestures into mouse and keyboard actions according to gesture/action mappings
 * read from a properties file.
 *
 */
public class LeapMotionListener extends Listener {

    private ResourceBundle myGestures;
    private Robot myRobot;
    private ILeapMouse myLeapMouse;
    public static final String PACKAGE_FILEPATH = "utilities.leapMotion.";
    public static final String GESTURE_BUNDLE = "Untitled";
    public static final String MOUSE_MOVE_FLAG = "mouse";
    public static final String MOUSE_FOLDER = "mouseControl.";
    public static final String DEFAULT_MOUSE = "TYPE_SCREEN_TAP";
    public static final int MOUSE_INPUT_THRESHOLD = 1;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.leapmotion.leap.Listener#onConnect(com.leapmotion.leap.Controller)
     */
    public void onConnect (Controller controller) {

        initializeRobot();
        myGestures = ResourceBundle.getBundle(PACKAGE_FILEPATH + GESTURE_BUNDLE);
        enableGestures(controller);
    }

    /**
     * Enable all gestures that are specified in the properties file. Configure
     * gestures when necessary.
     * 
     * @param controller
     */
    private void enableGestures (Controller controller) {
        myGestures.keySet().forEach(
                gestureName -> {
                    if (gestureName.equals(MOUSE_MOVE_FLAG)) {

                        Class<?> c = null;
                        try {
                            c = Class.forName(PACKAGE_FILEPATH + MOUSE_FOLDER
                                    + myGestures.getString(gestureName));

                            myLeapMouse = (ILeapMouse) c.newInstance();
                        } catch (ClassNotFoundException | InstantiationException
                                | IllegalAccessException | IllegalArgumentException
                                | SecurityException e) {
                            ErrorPopUp myError = new ErrorPopUp(e.toString());
                            myError.show();
                        }

                    }

                    for (Gesture.Type type : Gesture.Type.values()) {
                        if (type.toString().equals(gestureName)) {

                            controller.enableGesture(type);

                        }
                    }
                    ;
                });

    }

    /**
     * Create a robot to execute mouse and keyboard actions.
     */
    private void initializeRobot () {

        try {
            myRobot = new Robot();
        } catch (AWTException e) {
            ErrorPopUp myError = new ErrorPopUp(e.toString());
            myError.show();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.leapmotion.leap.Listener#onFrame(com.leapmotion.leap.Controller)
     */
    public void onFrame (Controller controller) {
        Frame frame = controller.frame();
        myLeapMouse.moveMouse(frame, myRobot);
        frame.gestures().forEach(gesture -> {
            myGestures.keySet().forEach(gestureName -> {
                if (gesture.type().toString().equals(gestureName)) {
                    performAction(gestureName);

                }
            });

        });
    }

    /**
     * Perform the mouse or keyboard action that the gesture is mapped to.
     * 
     * @param gestureName
     */
    private void performAction (String gestureName) {

        if (myGestures.getString(gestureName).length() > MOUSE_INPUT_THRESHOLD) {
            int mouseInput = Integer.parseInt(myGestures.getString(gestureName));
            myRobot.mousePress(mouseInput);
            myRobot.mouseRelease(mouseInput);
        } else {
            int keyInput = Character.toUpperCase(myGestures.getString(gestureName).charAt(0));

            myRobot.keyPress(keyInput);
        }

    }

}
