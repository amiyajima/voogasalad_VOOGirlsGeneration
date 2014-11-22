package gamedata.gamecomponents;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;


/**
 * creates square grid
 *
 */
public class SquareGrid extends Grid {
    private static final int DEFAULT_PATCH_STATE = 1;
    private static final int DEFAULT_PATCH_ID = 1;
    private static final String DEFAULT_PATCH_IMAGE_LOCATION = "";

    /**
     * Default constructor for square grid
     */
    public SquareGrid () {
        super();
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
                Patch patch = new SquarePatch(DEFAULT_PATCH_STATE,
                                              DEFAULT_PATCH_ID, DEFAULT_PATCH_IMAGE_LOCATION,
                                              new Point2D(x, y));
                super.myPatches.put(new Point2D(x, y), patch);
            }
        }
    }

}
