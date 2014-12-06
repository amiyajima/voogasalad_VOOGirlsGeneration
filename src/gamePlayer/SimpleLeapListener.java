package gamePlayer;

import java.io.IOException;
import java.lang.Math;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;


/**
 * For LeapMotion
 * @author 
 */


//public class Sample{
//    
//    class SimpleListener extends Listener {
//        public void onInit(Controller controller) {
//            System.out.println("Initialized");
//        }
//
//        public void onConnect(Controller controller) {
//            System.out.println("Connected");
//            controller.enableGesture(Gesture.Type.TYPE_SWIPE);
//            controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
//            controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
//            controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
//        }
//
//        public void onDisconnect(Controller controller) {
//            //Note: not dispatched when running in a debugger.
//            System.out.println("Disconnected");
//        }
//
//        public void onExit(Controller controller) {
//            System.out.println("Exited");
//        }
//
//        public void onFrame(Controller controller) {
//            // Get the most recent frame and report some basic information
//            Frame frame = controller.frame();
//            System.out.println("Frame id: " + frame.id()
//                               + ", timestamp: " + frame.timestamp()
//                               + ", hands: " + frame.hands().count()
//                               + ", fingers: " + frame.fingers().count()
//                               + ", tools: " + frame.tools().count()
//                               + ", gestures " + frame.gestures().count());
//
//
//
//
//
//            GestureList gestures = frame.gestures();
//            for (int i = 0; i < gestures.count(); i++) {
//                Gesture gesture = gestures.get(i);
//
//                switch (gesture.type()) {
//                    case TYPE_CIRCLE:
//                        CircleGesture circle = new CircleGesture(gesture);
//
//                        // Calculate clock direction using the angle between circle normal and pointable
//                        String clockwiseness;
//                        if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2) {
//                            // Clockwise if angle is less than 90 degrees
//                            clockwiseness = "clockwise";
//                        } else {
//                            clockwiseness = "counterclockwise";
//                        }
//
//                        // Calculate angle swept since last frame
//                        double sweptAngle = 0;
//                        if (circle.state() != State.STATE_START) {
//                            CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
//                            sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
//                        }
//
//                        System.out.println("  Circle id: " + circle.id()
//                                           + ", " + circle.state()
//                                           + ", progress: " + circle.progress()
//                                           + ", radius: " + circle.radius()
//                                           + ", angle: " + Math.toDegrees(sweptAngle)
//                                           + ", " + clockwiseness);
//                        break;
//                    case TYPE_SWIPE:
//                        SwipeGesture swipe = new SwipeGesture(gesture);
//                        System.out.println("  Swipe id: " + swipe.id()
//                                           + ", " + swipe.state()
//                                           + ", position: " + swipe.position()
//                                           + ", direction: " + swipe.direction()
//                                           + ", speed: " + swipe.speed());
//                        break;
//                    case TYPE_SCREEN_TAP:
//                        ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
//                        System.out.println("  Screen Tap id: " + screenTap.id()
//                                           + ", " + screenTap.state()
//                                           + ", position: " + screenTap.position()
//                                           + ", direction: " + screenTap.direction());
//                        break;
//                    case TYPE_KEY_TAP:
//                        KeyTapGesture keyTap = new KeyTapGesture(gesture);
//                        System.out.println("  Key Tap id: " + keyTap.id()
//                                           + ", " + keyTap.state()
//                                           + ", position: " + keyTap.position()
//                                           + ", direction: " + keyTap.direction());
//                        break;
//                    default:
//                        System.out.println("Unknown gesture type.");
//                        break;
//                }
//            }
//
//            if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
//                System.out.println();
//            }
//        }
//    }
//
//
//
//    private AnchorPane root = new AnchorPane();
//    private Circle circle=new Circle(50,Color.DEEPSKYBLUE);
//
//
//    public static void main(String[] args) {
//        // Create a sample listener and controller
//        SimpleListener listener = new SimpleListener();
//        Controller controller = new Controller();
//
//        // Have the sample listener receive events from the controller
//        // controller.addListener(listener);
//
//        // Keep this process running until Enter is pressed
//        System.out.println("Press Enter to quit...");
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//    } 
//
//}


public class SimpleLeapListener extends Listener {

    private ObjectProperty<Point2D> point=new SimpleObjectProperty<>();

    public ObservableValue<Point2D> pointProperty(){ return point; }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        if (!frame.hands().isEmpty()) {
            Screen screen = controller.locatedScreens().get(0);
            if (screen != null && screen.isValid()){
                Hand hand = frame.hands().get(0);


                if(hand.isValid()){
                    Vector intersect = screen.intersect(hand.palmPosition(),hand.direction(), true);
                    point.setValue(new Point2D(screen.widthPixels()*Math.min(1d,Math.max(0d,intersect.getX())),
                                               screen.heightPixels()*Math.min(1d,Math.max(0d,(1d-intersect.getY())))));
                }

            }
        }
    }
}

