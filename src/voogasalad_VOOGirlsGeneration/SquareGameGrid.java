package voogasalad_VOOGirlsGeneration;

import gamePlayer.Patch;
import gamedata.gamecomponents.Piece;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SquareGameGrid extends GameGrid{
    
    
    public SquareGameGrid(int rwo, int col, Map <Point2D, Patch> patchMap, Map <Point2D,Piece> pieceMap){
        super(rwo, col, patchMap, pieceMap);

        
    }



    @Override
    protected void populateGrid () {
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                Rectangle r = new Rectangle(500/this.r,500/c);
                r.getStyleClass().add("rectangle");
                this.add(r, i, j);
                
                
                r.setOnMouseEntered(event->onHover(r));
                r.setOnMouseExited(event->r.setFill(Color.BLACK));
                
                //todo populate map!!
                
            }
          
        }

    }
    private void onHover(Rectangle rec){
        rec.setFill(Color.BURLYWOOD);
        
    }

}
