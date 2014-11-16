package GameEngine;


// this class is entirely for purposes of making ui runnable and testing....


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;

public class GameModel extends Observable implements Observer{
    
    private double myScore;
    
    public GameModel(){
        
    }
    
    
    //initialize Game based on string representation of the game?
    public void intializeGame(String gameName){
        
        setChanged();
        notifyObservers(this);
    }
    
    public double getScore(){
        return myScore;
    }
    
    public void setScore(double d){
        myScore = d;
        setChanged();
        notifyObservers();
    }
    
    public void loadGame(File f){
        
    }
    
    public List<String> getCurrentGames(){
        ArrayList<String> games = new ArrayList<String>();
        games.add("Blah");
        games.add("blah");
        return games;
   
    }
    
    public List<String> getPlayers(){
        ArrayList<String> players = new ArrayList<String>();
        players.add("A");
        players.add("B");
        return players;
    }

    
    @Override
    public void update (Observable o, Object arg) {
        
    }
    
    //Assuming that we get these methods...
    public List<Integer> getPossibleMoves() {
        List<Integer> tempIndices = new ArrayList<Integer>();
        tempIndices.add(1);
        tempIndices.add(2);
        return tempIndices;
    }
   
    public void save() {
        System.out.println("Saving");
    }
    
    public void load() {
        System.out.println("Loading");
    }
    
    public void restart() {
        System.out.println("Restarting");
    }
}
