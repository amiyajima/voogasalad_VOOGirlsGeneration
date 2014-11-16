package gameengine.player;

import gamedata.gamecomponents.Level;


public class Player {

    private int myNumMovesPlayed;

    public Player () {
        myNumMovesPlayed = 0;
    }

    /**
     * While you're playing the level, check after each turn to see if the turn has been won.
     * If it has been won, tell the game to move to the next level.
     * 
     * @param level
     */
    public boolean levelWon (Level level) {
        while (!level.checkTurnEnd(myNumMovesPlayed)) {
            if (level.levelCompleted()) { return true; }
            myNumMovesPlayed++;
        }
        return false;
    }

}
