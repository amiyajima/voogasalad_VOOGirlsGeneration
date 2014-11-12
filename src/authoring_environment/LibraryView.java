package authoring_environment;

import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

public class LibraryView extends ScrollPane{
	public LibraryView(){
		this.setPrefSize(300, 580);
		Text text=new Text("Libarary");
		this.setContent(text);
	}

}
