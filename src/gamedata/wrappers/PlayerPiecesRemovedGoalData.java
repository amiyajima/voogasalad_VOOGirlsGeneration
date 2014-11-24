package gamedata.wrappers;

import gameengine.player.Player;


public class PlayerPiecesRemovedGoalData {
    private int myID;

    public PlayerPiecesRemovedGoalData (int ID) {
        myID = ID;
    }
    
    public String toString(){
        return "PlayerPiecesRemovedGoalData: player ID = " + myID;
    }
}
