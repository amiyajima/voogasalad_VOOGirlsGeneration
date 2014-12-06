package gamePlayer;

import com.leapmotion.leap.*;
import java.awt.Robot;





//import com.leapmotion.leap.Gesture.State;
public class LeapController {

class LeapListener extends Listener {
//    private ObjectProperty<Point2D> point = new SimpleObjectProperty<>();
    private Robot robot;
    public void onConnect (Controller c){
        c.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        c.enableGesture(Gesture.Type.TYPE_CIRCLE);
        
    }
    public void onFrame(Controller controller){
        Frame frame = controller.frame();
        InteractionBox box = frame.interactionBox();
        
        for(Finger finger : frame.fingers()){
            if(finger.type() ==Finger.Type.TYPE_INDEX){
                Vector pos = finger.tipPosition();
              //  robot.mouseMove(x, y);
            }
            
        }
        
    }
}

}
