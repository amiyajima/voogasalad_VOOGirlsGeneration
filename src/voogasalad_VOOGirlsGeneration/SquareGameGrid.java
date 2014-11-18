package voogasalad_VOOGirlsGeneration;

import gamePlayer.Patch;
import gamedata.gamecomponents.Piece;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

//TODO: fix stackpane size to make drop shadow effect more obvious!


public class SquareGameGrid extends GameGrid{
    
    public static final String TEST_IMAGE= "/src/voogasalad_VOOGirlsGeneration/turtle.png";
    
    
    public SquareGameGrid(int rwo, int col, Map <Point2D, Patch> patchMap, Map <Point2D,Piece> pieceMap){
        super(rwo, col, 600, 800, patchMap, pieceMap);
        
    }



    @Override
    protected void populateGrid () {
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                StackPane sp = new StackPane();
                sp.setAlignment(Pos.CENTER);
                sp.setPrefHeight(500/c-20);
                sp.setPrefWidth(500/this.r-20);
                Rectangle r = new Rectangle(500/this.r-20,500/c-20);
              //  r.getStyleClass().add("rectangle");
                this.add(r, i, j);
               
//                Image im= new Image("turtle.png");
//                ImageView iv = new ImageView();
//                iv.setImage(im);
                Circle c = new Circle(10);
                
                c.setFill(Color.BLUE);
                sp.getChildren().add(r);
                sp.getChildren().add(c);
                this.add(sp, i, j);
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
