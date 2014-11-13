package gameengine.movement;

import gamedata.piece.Piece;

public class Turn extends Movement {
    private double myDegree;
    
    public Turn(double degree) {
        myDegree = degree;
    }
    
    public void turn(Piece p) {
        p.getImageView().setRotate(myDegree);
    }
    
    public double getTurn() {
        return myDegree;
    }

}
