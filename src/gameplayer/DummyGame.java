<<<<<<< Updated upstream
package gamePlayer;
=======
package gameplayer;
>>>>>>> Stashed changes

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.SquareGrid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class DummyGame {
    
    public DummyGame(File json){
        
        
    }
    
    public DummyGame(String s){
        
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
    public List<Point2D> getEffectRanges(){
        ArrayList<Point2D> posList = new ArrayList<Point2D>();
        posList.add(new Point2D(1,0));
        posList.add(new Point2D(1,1));
        return posList;
    }
    
    public List<Point2D> getActionRange(){
        ArrayList<Point2D> posList = new ArrayList<Point2D>();
        posList.add(new Point2D(2,3));
        posList.add(new Point2D(1,2));
        return posList;
    }

}
