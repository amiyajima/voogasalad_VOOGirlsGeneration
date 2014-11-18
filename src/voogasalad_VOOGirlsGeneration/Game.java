package voogasalad_VOOGirlsGeneration;

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.SquareGrid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

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
    public List<Integer> getEffectRanges(){
        ArrayList<Integer> posList = new ArrayList<Integer>();
        posList.add(new Integer(1));
        posList.add(new Integer(3));
        return posList;
    }
    
    public List<Point2D> getActionRange(){
        ArrayList<Point2D> posList = new ArrayList<Point2D>();
        posList.add(new Point2D(2,3));
      //  posList.add(new Point2D(4,5));
        return posList;
    }

}
