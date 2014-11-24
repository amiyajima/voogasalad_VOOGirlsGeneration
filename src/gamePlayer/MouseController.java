package gamePlayer;

import java.awt.geom.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MouseController {
    Point2D myPreviousLocation;
    Point2D myCurrentLocation;
    
    Node myPreviousNode;
    Node myCurrentNode;
    
    IGridState myGridState;
    
  
    
    public void setOnClick(ViewController vc, IGridState gridState, GameGrid grid){
        myGridState = gridState;
        grid.setOnMouseClicked(event->{ 
            performAction(vc, event.getX(), event.getY());});
    }
    
    /**
     * Perform the actions of a click at position (x,y) on game grid
     * @param x
     * @param y
     */
    public void performAction (ViewController vc, double x, double y) {
//        System.out.println("where error happens");
//        System.out.println("current mouse location:"+x +", "+y);
        myGridState.onClick(vc.getPiece(vc.findPosition(x,y)));
    }
    
    

    
    
    
    
    
    
    
    
//    public void selectCurrentLocation(ViewController vc, GameGrid grid) {
//        myPreviousLocation = new Point2D.Double(0,0);
//        myCurrentLocation = new Point2D.Double(0,0);
//        
//        List<Node> children = grid.getChildren();
//        myCurrentNode = childrens.get(0);
//        for(Node node : children) {
//            node.setOnMousePressed(new EventHandler<MouseEvent>() {
//          @Override
//          public void handle (MouseEvent arg0) {
//              myPreviousNode = myCurrentNode;
//              myCurrentNode = node;
//              
//              myPreviousLocation = new Point2D.Double(GridPane.getRowIndex(myPreviousNode),GridPane.getColumnIndex(myPreviousNode));
//              myCurrentLocation = new Point2D.Double(GridPane.getRowIndex(myCurrentNode), GridPane.getColumnIndex(myCurrentNode));
//              
//              vc.highlightCurrentLocation(Color.GREEN, myPreviousLocation, myCurrentLocation);
//          }
//      });
//        }
//    }
    
    
    
    
}
    
    
    
    
    
 
    

