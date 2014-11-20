package authoring.concretefeatures;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SingleMultiplierBox extends HBox{
	
	public static final int MULTIPLIER_BOX_WIDTH = 120;
	public static final int STATS_BOX_WIDTH = 350;
	
	public SingleMultiplierBox(){
		
		Label x = new Label("X");
		
		ChoiceBox<String> statsRef = new ChoiceBox<>();
		statsRef.getItems().addAll("Self", "Other", "Constant");
		
		TextField scale = new TextField();
		TextField stats = new TextField();

		scale.setPrefWidth(MULTIPLIER_BOX_WIDTH);
		stats.setPrefWidth(STATS_BOX_WIDTH);
		
		scale.setPromptText("Multiplier");
		stats.setPromptText("Reference stat or constant");
		
		getChildren().addAll(scale, x, statsRef, stats);
	}

}
