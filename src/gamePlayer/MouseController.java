package gamePlayer;

import java.awt.geom.Point2D;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MouseController {
    Point2D myPreviousLocation;
    Point2D myCurrentLocation;
    
    Node myPreviousNode;
    Node myCurrentNode;
    
    public void selectCurrentLocation(ViewController vc, GameGrid grid) {
        myPreviousLocation = new Point2D.Double(0,0);
        myCurrentLocation = new Point2D.Double(0,0);
        
        List<Node> childrens = grid.getChildren();
        myCurrentNode = childrens.get(0);
        for(Node node : childrens) {
            node.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override
          public void handle (MouseEvent arg0) {
              myPreviousNode = myCurrentNode;
              myCurrentNode = node;
              
              myPreviousLocation = new Point2D.Double(GridPane.getRowIndex(myPreviousNode),GridPane.getColumnIndex(myPreviousNode));
              myCurrentLocation = new Point2D.Double(GridPane.getRowIndex(myCurrentNode), GridPane.getColumnIndex(myCurrentNode));
              
              vc.highlightCurrentLocation(Color.GREEN, myPreviousLocation, myCurrentLocation);
          }
      });
        }
    }
    
    
    
    
}
    
    
    
    
    
 
    

