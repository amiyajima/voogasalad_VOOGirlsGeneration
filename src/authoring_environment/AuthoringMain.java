package authoring_environment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthoringMain extends Application{
	
	private final int HEIGHT = 600;
	private final int WIDTH = 1000;

	@Override
	public void start(Stage primaryStage) throws Exception {
		VoogaView root = new VoogaView();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Girls GenerEditor");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}