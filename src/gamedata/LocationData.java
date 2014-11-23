package gamedata;

import java.util.List;
import javafx.geometry.Point2D;

/**
 * Location data wrapper
 * @author Rica
 *
 */
public class LocationData {
    private List<Point2D> myLocations;
    
    public LocationData(List<Point2D> locations) {
        myLocations = locations;
    }
    
    public List<Point2D> getLocations() {
        return myLocations;
    }

}
