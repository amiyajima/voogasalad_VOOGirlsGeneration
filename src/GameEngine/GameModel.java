package GameEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GameModel extends Observable{
    
    public GameModel(){
        
    }
    
    
    //initialize Game based on string representation of the game?
    public void intializeGame(String gameName){
        
        
    }
    
    public void loadGame(File f){
        
    }
    
    public List<String> getCurrentGames(){
        ArrayList<String> games = new ArrayList<String>();
        games.add("Blah");
        games.add("blah");
        return games;
   
    }
    
    

}
