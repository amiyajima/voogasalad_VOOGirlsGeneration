package gamedata.gamecomponents;

import java.awt.geom.Point2D;

/**
 * creates square grid
 *
 */
/**
 * @author Sandy Lee
 *
 */
public class SquareGrid extends Grid {
    private static final int DEFAULT_PATCH_TYPE = 1;
    private static final String DEFAULT_PATCH_IMAGE_LOCATION = "/resources/images/rcd.png";
    /**
     * Default constructor for square grid
     */
    public SquareGrid () {
        super(5, 5);
    }

    /**
     * constructor of square grid
     * 
     * @param x
     *        number of rows
     * @param y
     *        number of columns
     */
    public SquareGrid (int x, int y) {
        super(x, y);

        setGrid();
    }

    /**
     * set up grid by initializing patches on it
     */
    public void setGrid () {
        for (int x = 0; x < super.getColumn(); x++) {
            for (int y = 0; y < super.getRow(); y++) {
                Patch patch = new SquarePatch(DEFAULT_PATCH_TYPE, DEFAULT_PATCH_IMAGE_LOCATION,
                                              new Point2D.Double(x, y));
                super.myPatches.put(new Point2D.Double(x, y), patch);
            }
        }
    }

}

