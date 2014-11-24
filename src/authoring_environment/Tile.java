package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


/**
 * 
 * @author huangmengen
 *
 */
public class Tile extends Pane {

    private static final Image HIDDEN_DEFAULT_IMAGE =
            new Image("authoring/concretefeatures/images/images.jpeg");

    private int mySize;
    private Rectangle myDefault;
    protected Patch myTerrain;
    protected Piece myUnit;
    private Rectangle selected;
    public Rectangle surfaceImage;
    protected ImageView unitImage;
    protected ImageView terrainImage;
    private boolean mySelected;
    private int xCoord;
    private int yCoord;

    public Tile (int x, int y, int size) {

        mySize = size;
        mySelected = false;
        myDefault = new Rectangle(mySize, mySize);
        surfaceImage = new Rectangle(mySize, mySize);
        surfaceImage.setVisible(false);
        selected = new Rectangle(mySize, mySize);
        selected.setFill(Color.web("#0000FF", 0.3));
        selected.setVisible(false);

        unitImage = new ImageView(HIDDEN_DEFAULT_IMAGE);
        terrainImage = new ImageView(HIDDEN_DEFAULT_IMAGE);
        unitImage.setFitHeight(mySize);
        unitImage.setFitWidth(mySize);
        terrainImage.setFitHeight(mySize);
        terrainImage.setFitWidth(mySize);
        unitImage.setVisible(false);
        terrainImage.setVisible(false);

        xCoord = x;
        yCoord = y;

        if (((x % 2 == 0) && (y % 2 == 0)) || ((x % 2 == 1) && (y % 2 == 1))) {
            myDefault.setFill(Color.WHITE);
        }
        else {
            myDefault.setFill(Color.WHITESMOKE);
        }

        this.setLayoutX(x * mySize);
        this.setLayoutY(y * mySize);
        this.getChildren().addAll(myDefault, terrainImage, unitImage, surfaceImage, selected);
    }

    public int getX () {
        return xCoord;
    }

    public int getY () {
        return yCoord;
    }

    public int getSize () {
        return mySize;
    }

    public void setSize (int newSize) {
        mySize = newSize;
    }

    public void switchSelected () {
        if (getSelected()) {
        	selecteTile(false);
        }
        else {
        	selecteTile(true);
        }
    }

    public void selecteTile(boolean choose){
    	setSelected(choose);
    }
    
//    public void deselecteTile(boolean choose){
//    	setSelected(false);
//    }
    
    public void setSelected(boolean state){
    	 selected.setVisible(state);
         mySelected = state;
    }
    
    public boolean getSelected () {
        return mySelected;
    }

    public void addSurfaceImage (Image image) {
        surfaceImage.setFill(new ImagePattern(image));
        // this.getChildren().add(surfaceImage);
        surfaceImage.setVisible(true);

    }
}
