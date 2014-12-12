package authoring_environment;

import java.awt.geom.Point2D;
import java.util.ArrayList; 
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The view of the grid especially for selecting the range.  
 * @author Mengen Huang
 *
 */
public class RangeGrid extends SuperGrid{

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

	private void addSelectAction() {
		for (List<SuperTile> row:rangeGrid){
			for (SuperTile tile:row){
				tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						swtichHighlight(tile);
					}
					
				});
			}
		}
	}

	private void swtichHighlight(SuperTile tile) {
		if (tile.ifSelected())
			tile.deselectTile();
		else
			tile.selectTile(DEFAULT_HIGHLIGHT_COLOR);
	}
	
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
	
	private SuperTile findTile(int row, int col) {
		SuperTile tile=rangeGrid.get(col).get(row);
		return tile;
	}


	private void addCenterImage(int rows,int columns) {
		SuperTile centerTile=findCenterTile(columns,rows);
		Image centerImage=new Image(getClass().getResourceAsStream(DEFAULT_CENTRAL_IMAGE));
		ImageView centerPatrick=new ImageView(centerImage);
		centerTile.setPatchImage(centerPatrick);

	}
 
	private SuperTile findCenterTile(int rows,int columns) {
		SuperTile centerTile=findTile(centerX,centerY);
		return centerTile;
	}


	/**
	 * Collect all the coordination of selected tiles relative to the center tile
	 * as Point2D in a list.
	 * @return The list of relative coordination relative to the center tile. 
	 */
	public List<Point2D.Double> rangeSelectedList(){
		List<Point2D.Double> selectedList=new ArrayList<Point2D.Double>();
		
		for (int i=0;i<myHeight;i++) {
			for (int j=0;j<myWidth;j++) {
				if(rangeGrid.get(i).get(j).ifSelected()){
					selectedList.add(new Point2D.Double(j-centerX,centerY-i));
//					System.out.println((i-centerX)+","+(centerY-j));
				}
			}
		}
		myRange=selectedList;

		return myRange;
	}
	

	public void rangeColumn(int column,boolean toChoose){
		if ((centerX+column>=0) && (centerX+column<myWidth)){
			for (int i=0;i<myHeight;i++) {
				if (toChoose)
					findTile(centerX+column, i).selectTile(DEFAULT_HIGHLIGHT_COLOR);
				else
					findTile(centerX+column, i).deselectTile();
			}
		}
	}
	
	public void rangeRow(int row, boolean toChoose){
		if ((centerY-row>=0) && (centerY-row<myHeight)){
			for (int i=0;i<myWidth;i++) {
				if (toChoose)
					findTile(i, centerY-row).selectTile(DEFAULT_HIGHLIGHT_COLOR);
				else
					findTile(i, centerY-row).deselectTile();
			}
		} 
	}

	public void rangeRadius(int radius,boolean toChoose){
		if (centerX-radius<0) {
			rangeAll(toChoose);
		}else{
			for (int i=(centerX-radius);i<=(centerX+radius);i++){
				for (int j=(centerY-radius);j<=(centerY+radius);j++){
					if (toChoose){
						findTile(i, j).selectTile(DEFAULT_HIGHLIGHT_COLOR);
					}else{
						findTile(i, j).deselectTile();
					}
						
				}
			}
		}
	}
	
	public void rangeAll(boolean toChoose) {
		for (int i=0;i<myWidth;i++) {
			for (int j=0;j<myHeight;j++) {
				if (toChoose)
					findTile(i, j).selectTile(DEFAULT_HIGHLIGHT_COLOR);
				else
					findTile(i, j).deselectTile();
			}
		}
	}

	
	public List<Point2D.Double> getRange(){
		return myRange;
	}

	
}
