package authoring.concretefeatures;

import gamedata.gamecomponents.Patch;
import authoring.abstractfeatures.LibraryEntry;
import authoring_environment.LibraryView;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TerrainEntry extends LibraryEntry {
	
	private Patch myTerrain;
	
	public TerrainEntry(ImageView image, Hyperlink link, Patch terrain){
		this.getChildren().addAll(image, link);
		myTerrain = terrain;
		
		this.setStyle("-fx-cursor: hand");
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				LibraryView.currentlySelectedTerrain = myTerrain;
				LibraryView.unitSelected = false;
				LibraryView.reset = false;
			}
		});
	}
	
	public Patch getTerrain(){
		return myTerrain;
	}
}