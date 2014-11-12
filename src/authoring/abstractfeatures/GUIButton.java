package authoring.abstractfeatures;

import javafx.scene.Node;
import javafx.scene.control.Button;

public abstract class GUIButton extends GUIFeature{

	private String myName;
	
	@Override
	public Node makeTool() {
		Button b = new Button(myName);
		
		b.setOnMouseClicked(event -> action());
		return b;
	}

}
