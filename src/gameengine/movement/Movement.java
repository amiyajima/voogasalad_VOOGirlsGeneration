package gameengine.movement;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.rules.Rule;
import java.util.List;


public class Movement {
    // movement should contain path
    private List<Patch> myPath;

    public Movement (List<Patch> path) {
        myPath = path;
    }

    public void showPath () {
        // show path to user in GUI
    }

    /**
     * 
     * @param piece
     */
    public void movePiece (Piece piece) {
        if (checkPathClear()) {

        }
    }

    /**
     * check if there are collisions in your path. if the whole path is clear, return true. else
     * return false.
     */
    private boolean checkPathClear () {
        for (Patch p : myPath) {
            // need to check if your piece can move onto that patch
        }
        return true;
    }

    /**
     * 
     * 
     * @param x
     * @param y
     */
    private void moveToPosition (int x, int y) {

    }

}
