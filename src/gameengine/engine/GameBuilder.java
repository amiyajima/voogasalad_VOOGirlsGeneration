package gameengine.engine;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.util.List;
import java.util.Map;

public class GameBuilder {
    private Parser myParser;
    private List<Player> myPlayers;
    private List<Level> myLevels;
    private List<Patch> myPatches;
    private List<Piece> myPieces;
    
    public void getJSONFile(){
        
    }
    
    /**
     * fills in myPlayers, myLevels, myPatches, and myPieces using a map, which contains
     * all the components of the chosen game, parsed by a Parser
     */
    public void initiateComponents(Map<String, String> components){
        
    }
}
