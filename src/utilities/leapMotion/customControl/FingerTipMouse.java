package utilities.leapMotion.customControl;

import java.awt.Dimension;
import java.awt.Robot;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Vector;

import java.awt.Toolkit;

import utilities.leapMotion.ICustomControl;

public class FingerTipMouse implements ICustomControl{
    
<<<<<<< HEAD:src/utilities/leapMotion/mouseControl/FingerTipMouse.java
=======
    public static final String FINGER_TIP_MOUSE = "Finger Tip Tracking";

   

>>>>>>> a0815b4e93ca0c0b1e799d46a1461c54670d2513:src/utilities/leapMotion/customControl/FingerTipMouse.java
    @Override
    public void control (Frame frame, Robot robot) {
        InteractionBox box = frame.interactionBox();
        for(Finger finger: frame.fingers()){
            if(finger.type() ==Finger.Type.TYPE_INDEX){
                Vector pos = finger.tipPosition();
                Vector boxPos = box.normalizePoint(pos);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                robot.mouseMove((int) (screen.getWidth()*boxPos.getX()), 
                        (int) (screen.getHeight()-boxPos.getY()*screen.getHeight()));
            }
        }
        
    }

}
