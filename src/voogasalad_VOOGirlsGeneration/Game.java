package voogasalad_VOOGirlsGeneration;

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.SquareGrid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
    
    public Game(File json){
        
        
    }
    
    public Game(String s){
        
    }
    
    public String getStats(){
        return "hi!";
    }
    public List<String> getGames(){
        ArrayList<String> games = new ArrayList<String>();
        games.add("Blah");
        games.add("blah");
        return games;
    }
    public void initializeGame(String s){
        
    }
    public Grid getCurrentLevel(){
        return new SquareGrid();
    }
    public void store(File f){
        
    }

}
