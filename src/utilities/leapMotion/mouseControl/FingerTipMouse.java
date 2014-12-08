package utilities.leapMotion.mouseControl;

import java.awt.Dimension;
import java.awt.Robot;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Vector;

import java.awt.Toolkit;

import utilities.leapMotion.ILeapMouse;

public class FingerTipMouse implements ILeapMouse{
    
    public static final String FINGER_TIP_MOUSE = "Finger Tip Tracking";

   

    @Override
    public void moveMouse (Frame frame, Robot robot) {
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
