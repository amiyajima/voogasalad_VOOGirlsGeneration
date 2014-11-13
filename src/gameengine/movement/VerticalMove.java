package gameengine.movement;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import gamedata.piece.Piece;

public class VerticalMove extends Movement {
    private double myIncrement;
    
    public VerticalMove(double amount) {
        myIncrement = amount;
    }
    
    public List<Point2D> getPath(Piece p) {
        List<Point2D> myPath = new ArrayList<Point2D>();
        double yLoc = p.getImageView().getY();
        for (int i = 0; i < myIncrement; i++) {
            myPath.add(new Point2D(p.getImageView().getX(), yLoc++));
        }
        return myPath;
    }
    
    public void move(Piece p) {
        p.getImageView().setY(p.getImageView().getY() + myIncrement);
    }    
}
