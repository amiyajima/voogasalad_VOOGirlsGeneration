package gamePlayer;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level; 
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

public class SquareGameGrid extends GameGrid{
    private Point2D myCurrentLocation;
    
    public static final String TEST_IMAGE= "/src/voogasalad_VOOGirlsGeneration/turtle.png";
    
    
    public SquareGameGrid(int row, int col){
       // will handle different size, stubbing it for now.
        super(row, col);
        
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

    
    /**
     * The method to initialize the Grid. it populates the grid with
     * stackpanes that allows for adding pieces and patches images.
     * 
     */
    @Override
    protected void initializeGrid () {
        myCurrentLocation = new Point2D(0,0);
        
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
         
                addTestKeyboardControl(r);
//                addKeyboardController(r);
                addLocationSelector(r);
            } 
        }
        
        
        
    }
    
    private void addLocationSelector(Rectangle r){
      r.setOnMousePressed(new EventHandler<MouseEvent> (){
      @Override
      public void handle (MouseEvent event) {
          myCurrentLocation = new Point2D(r.getX(), r.getY());
          System.out.println(myCurrentLocation);
      }
      });
    }
    
    private void addTestKeyboardControl(Rectangle r){
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent key) {
                   if (key.getCode() == KeyCode.M) {
                       myCurrentLocation = new Point2D(myCurrentLocation.getX() + 1,
                                                       myCurrentLocation.getY() + 0); 
                       System.out.println(myCurrentLocation);
                       System.out.println("-----");
                   }
            }
        });
        highlightCurrentLocation(r);
    }
    
    private void addKeyboardController(){
//        Game game = new Game();
        KeyboardController KBControl = new KeyboardController();
        KBControl.setActionKeyControl(this,game);
        KBControl.setMovementKeyControl(this, game);
    }
    
    private void highlightCurrentLocation(Rectangle r){
        myCurrentLocation = new Point2D(100,100);
        if (r.getX()==myCurrentLocation.getX() & r.getY()==myCurrentLocation.getY()){
            System.out.println(myCurrentLocation.getX());
            r.setFill(Color.RED);
        }
        }
    
    // TODO: implement the logic in View Controller class.
    

    /**
     * populates the grid according to the given the patch and piece maps. 
     * 
     */

    @Override
    protected void populateGrid (Map<Point2D, Patch> patches, Map<Point2D, Piece> pieces) {
        this.getChildren().forEach(node->{((StackPane)node).getChildren().clear();});
        patches.keySet().forEach(point->{this.add(patches.get(point).getImageView(), (int)point.getX(), (int)point.getY());});
        pieces.keySet().forEach(point->{this.add(pieces.get(point).getImageView(), (int)point.getX(), (int)point.getY());});
        
    }
}
