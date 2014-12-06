package gamedata.gamecomponents;

/**
 * DEPRICATED
 * Creates square grid
 * @author Sandy Lee
 *
 */
public class SquareGrid extends Grid {
    /**
     * Default constructor makes a 5x5 square grid 
     */
    public SquareGrid () {
        this(8, 8);
    }

    /**
     * Constructor of square grid
     * @param x number of rows
     * @param y number of columns
     */
    public SquareGrid (int x, int y) {
        super(x, y);
//      setGrid();
    }

    /*
    public void setGrid () {
        for (int x = 0; x < super.getColumn(); x++) {
            for (int y = 0; y < super.getRow(); y++) {
                Patch patch = new SquarePatch(DEFAULT_PATCH_TYPE, DEFAULT_PATCH_IMAGE_LOCATION,
                                              new Point2D.Double(x, y));
                super.myPatches.put(new Point2D.Double(x, y), patch);
            }
        }
    }
    */

}

