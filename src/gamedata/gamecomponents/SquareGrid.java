package gamedata.gamecomponents;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.ReceiverToInventoryConclusion;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * creates square grid
 *
 */
public class SquareGrid extends Grid {
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
    }
}

