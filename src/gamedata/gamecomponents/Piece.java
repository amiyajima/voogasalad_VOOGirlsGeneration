package gamedata.gamecomponents;

import gameengine.movement.Movement;
import java.util.List;
import javafx.scene.image.ImageView;


public class Piece {
    private ImageView myImageView;
    private Movement myMovement;
    private Attack myAttack;
    private List<Piece> myItems;

    public Piece (ImageView i) {
        myImageView = i;
    }

    public ImageView getImageView () {
        return myImageView;
    }

    public void move () {

    }
}
