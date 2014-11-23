package gamePlayer;

import java.awt.geom.Point2D;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MouseController {
    Point2D myPreviousLocation;
    Point2D myCurrentLocation;
    
    public void selectCurrentLocation(ViewController vc, GameGrid grid) {
        myPreviousLocation = new Point2D.Double(0,0);
        myCurrentLocation = new Point2D.Double(0,0);
        
        for(int i=0; i<grid.r; i++){
            for(int j=0; j<grid.c; j++){
                Node node = grid.get(i,j);
                double i_d = i*1.0;
                double j_d = j*1.0;
                node.requestFocus();
                
                node.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent arg0) {
                        myPreviousLocation = myCurrentLocation;
                        Point2D newCurrentLocation = new Point2D.Double(i_d,j_d);
                        myCurrentLocation = newCurrentLocation;
                        
                        System.out.println("clicked");
                        System.out.println(myCurrentLocation);
                        
                        vc.highlightCurrentLocation(Color.GREEN, myPreviousLocation, myCurrentLocation);
                    }
                });
            }
        } 
    }
    
 
    
}
