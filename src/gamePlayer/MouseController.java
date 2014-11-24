package gamePlayer;

import java.awt.geom.Point2D;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MouseController {
    Point2D myPreviousLocation;
    Point2D myCurrentLocation;
    
    Node myPreviousNode;
    Node myCurrentNode;
    
    
    
    
    public void changeCursorImage(Scene scene, GameGrid grid, String filename){
        grid.setOnMouseExited(event->{applyCursorImage(scene, filename);});
    }
    
    public void applyCursorImage(Scene scene, String filename){
        Image image = new Image(filename);
        scene.setCursor(new ImageCursor(image, image.getWidth()/4,image.getWidth()/4));
    }
    
    
    
    
    
    
    
    
    
    
    
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
    
    
    
    
    
 
    

