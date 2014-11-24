package authoring_environment;

import javafx.scene.control.ScrollPane;

public class SandyGridView extends ScrollPane {
	
	public SandyGridView(SandyGrid grid, int viewWidth, int viewHeight) {
		this.setPrefSize(viewWidth, viewHeight);
		this.setMaxSize(viewWidth, viewHeight);
		this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		super.setContent(grid);
	}
}