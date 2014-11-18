package authoring_environment;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LibraryView extends TabPane{
	
	private final int HEIGHT = 300;
	private final int WIDTH = 580;
	private VBox[] libraryContents;
	public static ImageView currentlySelectedImage;
	
	public LibraryView(){
		this.setPrefSize(HEIGHT, WIDTH);
		String[] tabElements = {"Units", "Terrain"};
		libraryContents = new VBox[tabElements.length];
		
		for(int i = 0; i < tabElements.length; i++){
			Tab libraryTab = new Tab(tabElements[i]);
			libraryTab.setClosable(false);
			ScrollPane tabContent = new ScrollPane();
			libraryContents[i] = new VBox();
			tabContent.setContent(libraryContents[i]);
			libraryTab.setContent(tabContent);
			this.getTabs().add(libraryTab);
		}
	}
	
	public void addToLibrary(HBox content, int i){
		libraryContents[i].getChildren().add(content);
	}
}