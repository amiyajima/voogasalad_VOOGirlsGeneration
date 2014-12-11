package gamedata.wrappers;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level;
import gameengine.player.Player;
import java.util.ArrayList;
import java.util.List;


public class GameData {
    private List<PlayerDataIndividual> myPlayers;
    private List<LevelDataIndividual> myLevels;
    //private LevelData myLevels;
    private LevelDataIndividual myCurrentLevel;
    private PlayerDataIndividual myCurrentPlayer;
    private int myGameWon;
    private int myNumPlayers;

    public GameData (List<PlayerDataIndividual> players,
                     List<LevelDataIndividual> levels,
                     LevelDataIndividual currentLevel,
                     PlayerDataIndividual currentplayer,
                     int gameWon,
                     int numPlayers) {
        myPlayers = players;
        myLevels = levels;
        myCurrentLevel = currentLevel;
        myCurrentPlayer = currentplayer;
        myGameWon = gameWon;
        myNumPlayers = numPlayers;
    }

    public List<PlayerDataIndividual> getMyPlayers () {
        return myPlayers;
    }

    public List<LevelDataIndividual> getMyLevels () {
        return myLevels;
    }

    public LevelDataIndividual getMyCurrentLevel () {
        return myCurrentLevel;
    }

    public PlayerDataIndividual getMyCurrentPlayer () {
        return myCurrentPlayer;
    }

    public int getMyGameWon () {
        return myGameWon;
    }

    public int getMyNumPlayers () {
        return myNumPlayers;
    }
    
    /**
     * This pulls a game from my class's data!
     * @return a Game!
     */
    public Game getGameFromData () {
        List<Level> myLevelsFromData = new ArrayList<Level>();
        for (LevelDataIndividual ldi : myLevels) {
            myLevelsFromData.add(ldi.getLevelFromData());
        }
        Game myGame = new Game(myNumPlayers, myLevelsFromData, 
                               myCurrentLevel.getLevelFromData());
        return myGame;
    }

    public String toString () {
        return "GAMEDATA: # players = " + myPlayers.size() + " # levels " +
               myLevels.size() + "\nmy first player's ID is " + myPlayers.get(0).getID() +
               "\nmy second player's ID is " + myPlayers.get(1).getID() + 
               " \n# pieces in grid = " + myLevels.get(0).getGrid().getPieces().getPieces().size() +
               "\n# patches in grid = " + myLevels.get(0).getGrid().getPatches().getPatches().size() +
               "\nmyGameWon = " + myGameWon + "\nmyNumPlayers";
    }
}
