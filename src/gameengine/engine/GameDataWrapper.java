package gameengine.engine;

import gamedata.gamecomponents.Game;

public class GameDataWrapper {

    Game myGame;
    
    public GameDataWrapper(Game g){
        myGame = g;
    }
    
    public Game getGame(){
        System.out.println("getgame called for " + myGame);
        return myGame;
    }
    
    public String toString(){
        return myGame.toString();
    }
}
