package gamedata.gamecomponents;

import gameengine.movement.Movement;
import java.util.List;
import javafx.scene.image.ImageView;

public class Piece {
	private int myTypeID;
	private int myUniqueID;
	private ImageView myImageView;
	private List<Movement> myPath;

	public Piece(ImageView i, List<Movement> m) {
		myImageView = i;
	}

	public ImageView getImageView() {
		return myImageView;
	}

	public int getTypeID() {
		return myTypeID;
	}

	public int getUniqueID() {
		return myUniqueID;
	}

}
