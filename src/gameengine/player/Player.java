package gameengine.player;

import gamedata.gamecomponents.Level;


public class Player {

    private int myNumMoves;
    private int myNumMovesPlayed;

    public Player () {
        this(0);
    }

    public Player (int num) {
        myNumMovesPlayed = 0;
        myNumMoves = num;
    }

    /**
     * Until you run out of moves, play the level.
     * Return true if the level was won.
     * Otherwise, play the next move until your turn is over.
     * Return false if your turn is over and the level was not won
     * 
     * @param level
     */
    public boolean levelWon (Level level) {
        while (!level.checkTurnEnd(myNumMovesPlayed)) {
            // play a move
            if (level.levelCompleted()) { return true; }
            myNumMovesPlayed++;
        }
        return false;
    }

}
