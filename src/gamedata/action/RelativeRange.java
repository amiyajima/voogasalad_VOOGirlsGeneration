// This entire file is part of my masterpiece.
// Jennie Ju

package gamedata.action;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import authoring.concretefeatures.RangeEditor;

/**
 * RelativeRange class to hold information about relative ranges.
 * Performs calculations for game play and can display itself for 
 * authoring environment editing purposes.
 * 
 * @author Jennie Ju
 *
 */
public class RelativeRange {

	private List<Point2D.Double> myRange;
	
	/**
	 * Constructor for a RelativeRange
	 * 
	 * @param range - List<Point2D.Double> listing
	 * relative coordinates indicating range of action
	 */
	public RelativeRange(List<Point2D.Double> range) {
		myRange = range;
	}
	
	/**
	 * Calculates the absolute range in a List<Point2D.Double>
	 * for the relative range based on the given
	 * current location
	 * 
	 * @return list of absolute locations in Point2D.Double
	 */
	public List<Point2D.Double> calculateAbsoluteRange(Point2D currLoc) {
		List<Point2D.Double> absoluteRange = new ArrayList<Point2D.Double>();
		for (Point2D relativeLoc : myRange) {
			double absX = currLoc.getX() + relativeLoc.getX();
			double absY = currLoc.getY() + relativeLoc.getY();
			absoluteRange.add(new Point2D.Double(absX, absY));
		}
		return absoluteRange;
	}
	
	/**
	 * Displays the current relative range on a RangeEditor for editing in
	 * the ActionEditor
	 * 
	 * @param consumer - Consumer lambda stating how data will be manipulated in
	 * the RangeEditor
	 * @param gridShape - String stating desired grid shape
	 * @return RangeEditor populated with highlighed tiles indicating myRange
	 */
	public RangeEditor displayOnRangeEditor(Consumer<List<Point2D.Double>> consumer,
			String gridShape) {
		RangeEditor rangeEditor = new RangeEditor(myRange, consumer, gridShape);
		return rangeEditor;
	}
	
}
