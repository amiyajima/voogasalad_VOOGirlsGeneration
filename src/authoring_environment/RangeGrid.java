package authoring_environment;

import java.awt.geom.Point2D;
import java.util.ArrayList; 
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	private List<List<SuperTile>> rangeGrid;
	private int myWidth;
	private int myHeight;
	private int centerX;
	private int centerY;
	private List<Point2D> myRange;

	public RangeGrid(int columns, int rows, int tileSize, String shape, List<Point2D> range) {
		super(columns, rows, tileSize, shape);	
		myRange=range;
		myWidth=columns;
		myHeight=rows;
		
		centerX=myWidth/2;
		centerY=myHeight/2;
		
		initGridTiles(shape);
		rangeGrid=super.myGrid;
		
		addCenterImage(columns, rows);
		
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
			tile.selectTile();
	}
	
	private void highlightRange(List<Point2D> range) {
		if ((range!=null) && (range.size()>0)){
			for (Point2D loc: range){
				int col=(int) (loc.getX()+centerX);
				int row=(int) (centerY-loc.getY());
				if ((col>=0) && (col<=myWidth) && (row>=0) && (row<=myHeight)){
					SuperTile tile=findTile(row,col);
					tile.selectTile();
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
		centerTile.addPieceImage(centerPatrick);
		centerTile.myPieceImage.setVisible(true);

	}

	
	private SuperTile findCenterTile(int rows,int columns) {
		SuperTile centerTile=findTile(centerX,centerY);
		return centerTile;
	}



//	private void showSelectedRange() {
//		for (Point2D position:myRange){
//			int x=(int) (position.getX()+centerX);
//			int y=(int) (centerY-position.getY());
////			System.out.println(x);
////			System.out.println(y);
//			if ((x<=centerX*2) && (y<=centerY*2)){
//				sampleGrid.getTile(x,y).selecteTile(true);
//			}
//		}
//	}
//
//
//	
//	/**
//	 * Collect all the coordination of selected tiles relative to the center tile
//	 * as Point2D in a list.
//	 * @return The list of relative coordination relative to the center tile. 
//	 */
//	public List<Point2D> rangeSelectedList(){
//		List<Point2D> selectedList=new ArrayList<Point2D>();
//		
//		for (int i=0;i<sampleGrid.getGridWidth();i++) {
//			for (int j=0;j<sampleGrid.getGridHeight();j++) {
//				if(sampleGrid.getGridTiles()[i][j].getSelected()){
//					selectedList.add(new Point2D.Double(i-centerX,centerY-j));
////					System.out.println((i-centerX)+","+(centerY-j));
//				}
//			}
//		}
//		myRange=selectedList;
////		test=4;
////		System.out.println("t="+ test);
//		return selectedList;
//
//	}
//	
//	public void rangeColumn(int column,boolean toChoose){
//		for (int i=0;i<sampleGrid.getGridHeight();i++) {
//			sampleGrid.getTile(centerX+column, i).selecteTile(toChoose);
//		}
//	}
//	
//	public void rangeRow(int row,boolean toChoose){
//		for (int i=0;i<sampleGrid.getGridWidth();i++) {
//			sampleGrid.getTile(i, centerY-row).selecteTile(toChoose);
//		}
//	} 
//	
//	public void rangeRadius(int radius,boolean toChoose){
//		for (int i=(centerX-radius);i<=(centerX+radius);i++){
//			for (int j=(centerY-radius);j<=(centerY+radius);j++){
//				sampleGrid.getTile(i, j).selecteTile(toChoose);
//			}
//		}
//	}
//	
//	public void rangeAll(boolean toChoose) {
//		for (int i=0;i<sampleGrid.getGridWidth();i++) {
//			for (int j=0;j<sampleGrid.getGridHeight();j++) {
//				sampleGrid.getTile(i, j).selecteTile(toChoose);
//			}
//		}
//	}
//	
//	private void sampleSelected() {
//		for (Tile[] line : sampleGrid.getGridTiles()) {
//			for (Tile tile : line) {
//				tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
//					@Override
//					public void handle(MouseEvent event) {
//						tile.switchSelected();
//					}
//				});
//			}
//		}
//	}
	
	public void setRange(List<Point2D> range){
		myRange=range;
	}
	
	public List<Point2D> getRange(){
		return myRange;
	}


	
}
