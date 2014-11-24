package gamePlayer;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.Map;
import java.util.Observable;
import java.awt.geom.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * The View of square game grid. It observes the (Level in) Game
 * and updates (re-populate the grid and update scores of players)
 * upon notified.
 *
 */
// TODO: maybe observing Game instead of Level.? (need access to player scores.)

public class SquareGameGrid extends GameGrid {

    public static final String TEST_IMAGE = "/src/voogasalad_VOOGirlsGeneration/turtle.png";

    public SquareGameGrid (int row, int col) {
        // will handle different size, stubbing it for now.
        super(row, col);

    }

    private void onHover (Rectangle rec) {
        rec.setFill(Color.BURLYWOOD);
    }

    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof Level) {
            System.out.println("updated!");
            populateGrid(((Level) o).getGrid().getPatches(), ((Level) o).getGrid().getPieces());

        }

    }

    /**
     * The method to initialize the Grid. it populates the grid with
     * stackpanes that allows for adding pieces and patches images.
     * 
     */
    @Override
    protected void initializeGrid () {

        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                StackPane sp = new StackPane();
                sp.setAlignment(Pos.CENTER);
                sp.setPrefHeight(500 / c);
                sp.setPrefWidth(500 / this.r);

                this.add(sp, i, j);
               
                

            }

        }
    }
    
    
    // TODO: implement the logic in View Controller class.

    /**
     * populates the grid according to the given the patch and piece maps.
     * 
     */

    @Override
    protected void populateGrid (Map<Point2D, Patch> patches, Map<Point2D, Piece> pieces) {
        this.getChildren().forEach(node -> {
            ((StackPane) node).getChildren().clear();
        });
        initializeGrid();
        patches.keySet().forEach(point -> {
           // this.add(patches.get(point).getImageView(), (int) point.getX(), (int) point.getY());
            Node n = get((int)point.getX(), (int)point.getY());
            ((StackPane)n).getChildren().add(patches.get(point).getImageView());
        
        });
        pieces.keySet().forEach(point -> {
            this.add(pieces.get(point).getImageView(), (int) point.getX(), (int) point.getY());
            
            
            Node n = get((int)point.getX(), (int)point.getY());
            ((StackPane)n).getChildren().add(pieces.get(point).getImageView());
        });
    }
    

    
}
