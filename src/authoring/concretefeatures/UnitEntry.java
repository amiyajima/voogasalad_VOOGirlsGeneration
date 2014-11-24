package authoring.concretefeatures;

import gamedata.gamecomponents.Piece;
import authoring.abstractfeatures.LibraryEntry;
import authoring_environment.UIspecs;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * @author Martin Tamayo
 * 
 * An HBox containing data for a unit piece. UnitEntries are
 * added to the LibraryView in the game authoring environment.
 */
public class UnitEntry extends LibraryEntry {
	
	private Piece myUnit;
	
	/**
	 * Constructs the UnitEntry, which is displayed as an HBox in the
	 * LibraryView in the game authoring environment. This class also
	 * contains the Piece class for the unit.
	 * 
	 * @param image : ImageView of the image of the unit.
	 * @param link : Name of unit. Links to UnitEditor.
	 * @param unit : Actual Piece class for the unit.
	 */
	public UnitEntry(Piece unit, ImageView image, Label name, Button editButton, Button delButton){
		this.getChildren().addAll(delButton, editButton, image, name);
		this.setPadding(UIspecs.allPadding);
        this.setSpacing(5);
		myUnit = unit;
	}
	
	/**
	 * Method to retrieve the Piece class contained within this UnitEntry class.
	 * 
	 * @return Piece associated with the UnitEntry.
	 */
	public Piece getUnit(){
		return myUnit;
	}
}