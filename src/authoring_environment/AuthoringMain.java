package authoring_environment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthoringMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		VoogaView root=new VoogaView();
		Scene scene=new Scene(root, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Girls GenerEditor");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}