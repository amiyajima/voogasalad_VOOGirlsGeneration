package gamePlayer;

import java.awt.geom.Point2D;
import javafx.scene.Node;
import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameGridEffect {
    GameGrid myGrid;
    Piece activePiece;
    Action activeAction;
    
    
    /**
     * Highlight the tiles that represent the possible range of the action
     * selected
     */
    // @FXML
    protected void highLightActionRange () {

        myGrid.clearEffect();
        if (activePiece != null && activeAction != null) {

            activeAction.getActionRange(activePiece.getLoc()).forEach(point -> {
                     if (point.getX() < myGrid.getRow() && point.getY() < myGrid.getCol()
                             && point.getX() > 0 && point.getY() > 0) {
                         Node n = myGrid.get((int) point.getX(), (int) point.getY());
                         addDropShadow(n, Color.YELLOW);
                     }
                 });
        }
    }
 
    private void addDropShadow (Node n, Color c) {
    if (n != null) {
       DropShadow ds = new DropShadow();
       ds.setRadius(30.0);
       ds.setOffsetX(0.0);
       ds.setOffsetY(0.0);
       ds.setColor(c);
       n.setEffect(ds);
    }
    }
    
      /**
      * Highlight the effect range of an action if to be applied at a given
      * position
      * 
      * @param n
      * @param red
      */
     protected void highLightEffectRange (MouseEvent me, Color c) {
    
         if (activePiece != null && activeAction != null) {
             activeAction
                     .getActionRange(activePiece.getLoc())
                     .forEach(point -> {
                         Point2D temp = findPosition(me.getSceneX(), me.getSceneY());
                         if (temp.equals(point)) {
                             activeAction
                                     .getEffectRange()
                                     .forEach(point2 -> {
                                         Node n =
                                                 myGrid.get((int) (temp.getX() + point2
                                                         .getX()),
                                                            (int) (temp.getY() + point2
                                                                    .getY()));
    
                                         addDropShadow(n, c);
                                     });
    
                         }
                     });
    
         }
     }
     
       public void highlightCurrent (Point2D loc, Color c) {
           addDropShadow(myGrid.get((int) loc.getX(), (int) loc.getY()), c);
       }
    
       public void unhighlight (Point2D loc) {
           Node n = myGrid.get((int) loc.getX(), (int) loc.getY());
           if (n != null) {
               n.setEffect(null);
           }
       }

       
       
       
       
       
}
