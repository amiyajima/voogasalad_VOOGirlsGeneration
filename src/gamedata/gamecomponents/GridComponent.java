package gamedata.gamecomponents;

import java.awt.geom.Point2D;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract superclass for pieces and patches,
 * which together constitute every element of a
 * level that can be directly interacted with on
 * a grid. This class contains methods that are
 * accessible to both of them.
 * 
 * @author Martin Tamayo
 */
public abstract class GridComponent implements IHasStats{
	
	protected String myID;
	protected String myName;
	protected Point2D.Double myLoc;
	protected String myImageLocation;
	protected transient ImageView myImageView;
	
	/**
	 * General constructor for instantiating a brand new
	 * piece or patch. Any instance variables specific to
	 * pieces or patches are dealt with in their own,
	 * separate constructors.
	 * 
	 * @param id : Unique string ID for the piece or patch.
	 * @param name : String name describing the piece or patch.
	 * @param imageLocation : String path to image on file.
	 * @param point : Point2D.Double location of piece or patch on grid.
	 */
	public GridComponent(String id, String name, String imageLocation, Point2D.Double point) {
		myID = id;
		myName = name;
		myLoc = point;
		myImageLocation = imageLocation;
		setImageView();
	}
	
	/**
	 * General constructor for cloning a piece or patch. Once a
	 * piece or patch is created, one will often want to create
	 * copies of them to place on the grid. This constructor is
	 * for handling such cases.
	 * 
	 * @param clone : Piece or patch class to copy over.
	 */
	public GridComponent(GridComponent clone, Point2D.Double placeHere){
		myID = clone.myID;
		myName = clone.myName;
		myLoc = new Point2D.Double(placeHere.getX(),placeHere.getY());
		myImageLocation = clone.myImageLocation;
		setImageView();
	}
	
	/**
	 * Getter method for the unique ID of the GridComponent.
	 * 
	 * @return : Unique string ID for the piece or patch.
	 */
	public String getID() {
		String myString = "Grid Compenent #" + myID + " " + myName;
		myString += "loc:(" + myLoc.getX() + " " + myLoc.getY() + ") ";
		myString += myImageLocation;
		return myString;
	}
	
	/**
	 * Setter method for the name of the GirdComponent.
	 * 
	 * @param name : String name describing the piece or patch.
	 */
	public void setName(String name) {
		this.myName = name;
	}
	
	/**
	 * Getter method for the location of the GridComponent
	 * on the grid.
	 * 
	 * @return myLoc : Point2D.Double location of piece or patch on grid.
	 */
    public Point2D.Double getLoc() {
        return myLoc;
    }
    
    /**
	 * Setter method for the location of the GridComponent
	 * on the grid.
	 * 
	 * @param loc : Point2D.Double location of piece or patch on grid.
	 */
	public void setLoc(Point2D.Double loc) {
		myLoc = loc;
	}
	
    /**
     * Getter method for the path to the image for the
     * GridComponent on file.
     * 
     * @return myImageLocation : String path to image on file.
     */
    public String getImageLocation() {
        return myImageLocation;
    }
    
	/**
	 * Setter method for the path to the image for the
	 * GridComponent on file.
	 * 
	 * @param imageLocation : String path to image on file.
	 */
	public void setImageLocation(String imageLocation) {
		myImageLocation = imageLocation;
		setImageView();
	}
	
	/**
	 * Getter method for the ImageView for the GridComponent
	 * for GUI display purposes.
	 * 
	 * @return myImageView : Image for the GridComponent.
	 */
    public ImageView getImageView() {
        return myImageView;
    }
    
    /**
	 * Method for creating an ImageView using the location of the
	 * image on file. This is for GUI display purposes. This method
	 * is used privately by the GridComponent constructor methods.
	 * 
	 * @param imageLocation : String path to image on file.
	 */
	private void setImageView() {
    	if(myImageLocation.startsWith("/")){
        	myImageView = new ImageView(new Image(getClass().getResourceAsStream(myImageLocation)));
        }
        else{
        	myImageView = new ImageView(new Image(myImageLocation));
        }
    }
}
