package voogasalad_VOOGirlsGeneration;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SquareGameGrid extends GameGrid{
    
    
    public SquareGameGrid(int rwo, int col){
        super(rwo, col);
        
    }



    @Override
    protected void populateGrid () {
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                this.add(new Rectangle(500/r,500/c),i,j);
            }
            this.setGridLinesVisible(true);
        }
  //      this.add(new Circle(20), 3, 3);
        
  //      this.add(new Circle(20), 2, 2);
  //      this.setGridLinesVisible(true);
    }

}
