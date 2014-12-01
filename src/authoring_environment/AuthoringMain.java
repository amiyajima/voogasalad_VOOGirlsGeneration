package authoring_environment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthoringMain extends Application{
	
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private final String MAIN_TITLE = "Girls GenerEditor";
	private final int HEIGHT = 600;
	private final int WIDTH = 1000;

	@Override
	public void start(Stage primaryStage) throws Exception {
		VoogaView root = new VoogaView();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add(STYLESHEET);
		primaryStage.setScene(scene);
		primaryStage.setTitle(MAIN_TITLE);
		primaryStage.setX(0);
		primaryStage.setY(0);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}