package gamedata.wrappers;

import gamedata.gamecomponents.Level;
import gameengine.player.Player;
import java.util.List;


public class GameData {
    private List<PlayerDataIndividual> myPlayers;
    /**
     * Contains ordered list of levels that compose the game
     */
    private List<LevelDataIndividual> myLevels;
    private LevelDataIndividual myCurrentLevel;
    private PlayerDataIndividual myCurrentPlayer;
    private boolean myGameWon;

    public GameData (List<PlayerDataIndividual> players,
                     List<LevelDataIndividual> level,
                     LevelDataIndividual currentLevel,
                     PlayerDataIndividual currentplayer,
                     boolean gamewon) {
        myPlayers = players;
        myLevels = level;
        myCurrentLevel = currentLevel;
        myCurrentPlayer = currentplayer;
        myGameWon = gamewon;
    }

    public String toString () {
        return "GAMEDATA: # players = " + myPlayers.size() + " # levels " +
               myLevels.size() + " my first player's ID is " + myPlayers.get(0).getID() +
               " \n# pieces in grid = " + myLevels.get(0).getGrid().getPieces().size() +
               "\n# patches in grid = " + myLevels.get(0).getGrid().getPatches().size();
    }
}
