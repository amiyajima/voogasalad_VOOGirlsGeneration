package gamePlayer;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.Map;
import java.util.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//TODO: fix stackpane size to make drop shadow effect more obvious!


public class SquareGameGrid extends GameGrid{
    
    public static final String TEST_IMAGE= "/src/voogasalad_VOOGirlsGeneration/turtle.png";
    
    
    public SquareGameGrid(int rwo, int col){
       // will handle different size, stubbing it for now.
        super(rwo, col);
        
    }


    private void onHover(Rectangle rec){
        rec.setFill(Color.BURLYWOOD);
        
    }



    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof Level){
           populateGrid(((Level) o).getGrid().getPatches(), ((Level) o).getGrid().getPieces());
           
        }
        
    }



    @Override
    protected void initializeGrid () {
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                StackPane sp = new StackPane();
                sp.setAlignment(Pos.CENTER);
                sp.setPrefHeight(500/c);
                sp.setPrefWidth(500/this.r);
                Rectangle r = new Rectangle(500/this.r-10,500/c-10);
                r.setFill(Color.BLACK);

                sp.getChildren().add(r);

                this.add(sp, i, j);
                r.setOnMouseEntered(event->onHover(r));
                r.setOnMouseExited(event->r.setFill(Color.BLACK));

                
            }
          
        }
        
    }



    @Override
    protected void populateGrid (Map<Point2D, Patch> patches, Map<Point2D, Piece> pieces) {
        this.getChildren().forEach(node->{((StackPane)node).getChildren().clear();});
        patches.keySet().forEach(point->{this.add(patches.get(point).getImageView(), (int)point.getX(), (int)point.getY());});
        pieces.keySet().forEach(point->{this.add(pieces.get(point).getImageView(), (int)point.getX(), (int)point.getY());});
        
    }
    


}
