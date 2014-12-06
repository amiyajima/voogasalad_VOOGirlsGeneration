package gamePlayer;
import java.awt.geom.Point2D;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

/**
 * For LeapMotion
 * @author 
 *
 */
public class LeapController {

class leapListener extends Listener {
    
    
    private ObjectProperty<Point2D> point = new SimpleObjectProperty<>();
    public void onFrame(Controller controller){
        Frame frame = controller.frame();
        
    }
}

}
