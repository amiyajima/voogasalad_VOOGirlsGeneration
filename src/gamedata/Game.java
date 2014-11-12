package gamedata;

import java.util.ArrayList;
import java.util.List;
import gameengine.player.*;
import gamedata.grid.*;


public class Game {

    private List<Player> myPlayers;
    private List<Level> myLevels;

    public Game () {
        myPlayers = new ArrayList<Player>();
        myLevels = new ArrayList<Level>();
    }

    /**
     * Method called every iteration of the game loop
     */
    public void playRound () {
        for (Player p : myPlayers) {
            p.play();
        }
    }

}
