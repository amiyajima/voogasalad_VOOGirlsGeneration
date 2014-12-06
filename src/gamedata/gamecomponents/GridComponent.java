package gamedata.gamecomponents;

import java.awt.geom.Point2D;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GridComponent implements IHasStats{
	
	protected String myName;
	protected Point2D myLoc;
	protected String myImageLocation;
	protected transient ImageView myImageView;
	
	public GridComponent(String name, String imageLocation, Point2D p) {
		myName = name;
		myImageLocation = imageLocation;
		setImageView(imageLocation);
		myLoc = p;
	}
	
	public GridComponent(GridComponent clone){
		myImageLocation = clone.myImageLocation;
		setImageView(myImageLocation);
		myLoc = new Point2D.Double(clone.myLoc.getX(),clone.myLoc.getY());
		myName = clone.myName;
	}
	
	private void setImageView(String imageLocation) {
    	if(myImageLocation.startsWith("/")){
        	myImageView = new ImageView(new Image(getClass().getResourceAsStream(imageLocation)));
        }
        else{
        	myImageView = new ImageView(new Image(imageLocation));
        }
    }
	
	/**
	 * Getter for patch's name
	 * 
	 * @return String - patch name
	 */
	public String getName() {
		return myName;
	}
	
	/**
     * Returns the Point2D indicating the piece's coordinates
     */
    public Point2D getLoc () {
        return myLoc;
    }
    
    /**
	 * sets patch's coordinate location
	 * 
	 * @param coord
	 */
	public void setLoc(Point2D coord) {
		myLoc = coord;
	}
}