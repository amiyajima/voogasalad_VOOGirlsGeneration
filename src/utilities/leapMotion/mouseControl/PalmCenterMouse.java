package utilities.leapMotion.mouseControl;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Vector;
import utilities.leapMotion.ILeapMouse;

public class PalmCenterMouse implements ILeapMouse{

    @Override
    public void moveMouse (Frame frame, Robot robot) {
        InteractionBox box = frame.interactionBox();
        
                Vector pos = frame.hand(0).palmPosition();
                Vector boxPos = box.normalizePoint(pos);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                robot.mouseMove((int) (screen.getWidth()*boxPos.getX()), 
                        (int) (screen.getHeight()-boxPos.getY()*screen.getHeight()));
           
       
        
    }
    

}
