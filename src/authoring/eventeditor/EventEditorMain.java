package authoring.eventeditor;


import java.io.IOException;
import java.util.List;

import gamedata.events.Event;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventEditorMain extends Application{

	private List<Event> myEvents;
	
	@Override
	public void start(Stage stage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EventEditor.fxml"));
		Parent root = loader.load();

		EventEditorController controller = loader.getController();
		controller.setMain(this);
		
		Scene scene = new Scene(root, 800, 512);

		stage.setTitle("Events");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
