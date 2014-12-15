// This entire file is my masterpiece.
// MENGEN HUANG
package authoring_environment;

import java.awt.geom.Point2D;
import java.lang.reflect.Method;
import java.util.ArrayList; 
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The grid is made especially for selecting the range. It is shown inside the 
 * RangeGridView inside RangeEditor. This class deals with the logic of user's 
 * selection and gives back a list of point2D which represents the relative coordinates
 * to the center tile of the grid.
 * 
 * @author Mengen Huang
 *
 */
public class RangeGrid extends SuperGrid{

	private static final String ERROR_INFOMATION = "Ops!";
	private static final String RANGE = "range";
	private static final String DEFAULT_CENTRAL_IMAGE="/resources/images/Patrick.jpeg";
	private static final String DEFAULT_HIGHLIGHT_COLOR = "#0000FF";

	private List<List<SuperTile>> rangeGrid;
	private int myWidth;
	private int myHeight;
	private int centerX;
	private int centerY;
	private List<Point2D.Double> myRange;

	public RangeGrid(int width, int height, double tileSize, String shape, List<Point2D.Double> range) {
		super(width, height, tileSize, shape);	
		myRange=range;
		myWidth=width;
		myHeight=height;

		centerX=myWidth/2;
		centerY=myHeight/2;

		initGridTiles(shape);
		rangeGrid=super.myGrid;

		addCenterImage(width, height);

		highlightRange(range);

		addSelectAction();

	}

	/**
	 * Apply the action to all the tiles in the grid that
	 * clicking on the tile will switch the highlight on or off.
	 */
	private void addSelectAction(){
		for (List<SuperTile> row: rangeGrid){
			for (SuperTile tile:row){
				tile.setOnMouseClicked(event->switchHighlight(tile));
			}
		}
	}

	/**
	 * Switch the highlight on or off with a specified color.
	 * @param tile: The tile to switch the highlight.
	 */
	private void switchHighlight(SuperTile tile) {
		boolean selected = (tile.isSelected()) ? 
				tile.deselectTile():tile.selectTile(DEFAULT_HIGHLIGHT_COLOR);
	}

	/**
	 * Highlight all the tiles currently in the visible grid.
	 * The tiles are represented by grid location in range.
	 * @param range: The list of tiles represented by relative grid location 
	 * 					to the center tile.
	 */
	private void highlightRange(List<Point2D.Double> range) {
		if ((range!=null) && (range.size()>0)){
			for (Point2D loc: range){
				int col=(int) (loc.getX()+centerX);
				int row=(int) (centerY-loc.getY());
				if ((col>=0) && (col<=myWidth) && (row>=0) && (row<=myHeight)){
					SuperTile tile=findTile(col,row);
					tile.selectTile(DEFAULT_HIGHLIGHT_COLOR);
				}
			}
		}
	}

	/**
	 * Get the tile by its grid location.
	 * @param row: The y axis grid location.
	 * @param column: The x axis grid location.
	 * @return The tile on the indicated grid location.
	 */
	private SuperTile findTile(int row, int column) {
		SuperTile tile=rangeGrid.get(column).get(row);
		return tile;
	}


	/**
	 * Add the image "Patrick" at the center of the grid.
	 * @param row: Number of rows in the grid.
	 * @param column: Number of columns in the grid.
	 */
	private void addCenterImage(int row,int column) {
		SuperTile centerTile=findTile(centerX,centerY);
		Image centerImage=new Image(getClass().getResourceAsStream(DEFAULT_CENTRAL_IMAGE));
		ImageView centerPatrick=new ImageView(centerImage);
		centerTile.setPatchImage(centerPatrick);
	}


	/**
	 * Collect all the coordination of selected tiles relative to the center tile
	 * as Point2D in a list.
	 * @return The list of relative coordination relative to the center tile. 
	 */
	public List<Point2D.Double> selectedRangeList(){
		List<Point2D.Double> selectedList=new ArrayList<Point2D.Double>();
		for (int i=0;i<myWidth;i++) {
			for (int j=0;j<myHeight;j++) {
				boolean selected=findTile(i,j).isSelected()? 
						selectedList.add(new Point2D.Double(i-centerX,centerY-j)):
							false;
			}
		}
		myRange=selectedList;
		return myRange;
	}



	/**
	 * Select or deselect the Column of the specified one by highlighting the tile.
	 * @param column: The number of the column.
	 * @param toChoose: true to be select, false to be deselect.
	 */
	private void rangeColumn(int column,boolean toChoose){
		if ((centerX+column>=0) && (centerX+column<myWidth)){
			for (int i=0;i<myHeight;i++) {
				processHighlight(toChoose,centerX+column, i);
			}
		}
	}


	/**
	 * Select or deselect the row of the specified one by highlighting the tile.
	 * @param column: The number of the row.
	 * @param toChoose: true to be select, false to be deselect.
	 */
	private void rangeRow(int row, boolean toChoose){
		if ((centerY-row>=0) && (centerY-row<myHeight)){
			for (int i=0;i<myWidth;i++) {
				processHighlight(toChoose,i,centerY-row);
			}
		} 
	}

	/**
	 * Select or deselect the tiles radius away from the center by highlighting the tile.
	 * @param column: The radius of the highlighted tiles from the center.
	 * @param toChoose: true to be select, false to be deselect.
	 */
	private void rangeRadius(int radius,boolean toChoose){
		if (centerX-radius<0) {
			rangeAll(0,toChoose);
		}else{
			for (int i=(centerX-radius);i<=(centerX+radius);i++){
				for (int j=(centerY-radius);j<=(centerY+radius);j++){
					processHighlight(toChoose,i,j);			
				}
			}
		}
	}

	/**
	 * Select or deselect all the tiles in the range.
	 * @param toChoose: true to be select, false to be deselect.
	 */
	private void rangeAll(int ii, boolean toChoose) {
		for (int i=0;i<myWidth;i++) {
			for (int j=0;j<myHeight;j++) {				
				processHighlight(toChoose,i,j);
			}

		}
	}

	/**
	 * Process if the tile at a certain location should be selected or deselected
	 * accroding to the information passed in.
	 * @param toChoose: True as to select and false as deselect.
	 * @param column: The x coordinate of the tile.
	 * @param row: The y coordinate of the tile.
	 * @return
	 */
	private boolean processHighlight(boolean toChoose, int column, int row){
		return toChoose? findTile(column, row).selectTile(DEFAULT_HIGHLIGHT_COLOR):
			findTile(column, row).deselectTile();
	}


	/**
	 * Reflection method that get the correspondent method according to the String
	 * passed in.
	 * @param chosen: The string passed in that represents column/row/radius/all.
	 * @param parameter: The integer passed in as the parameter of the method.
	 * 					It specifies which row/column or what is the radius of the 
	 * 					select range. The parameter is set 0 when the user choose all.
	 * @param toChoose: To select or deselect the tile.
	 */
	public void processChoice(String chosen, int parameter, boolean toChoose) {
		Class[] paramTypes = {Integer.TYPE, Boolean.TYPE};	
		try{
			Method method= this.getClass().getDeclaredMethod(RANGE+chosen, paramTypes);
			method.invoke(this,parameter,toChoose);
		}catch(Exception e){
			log.error(ERROR_INFOMATION, e);
		}

	}


}
