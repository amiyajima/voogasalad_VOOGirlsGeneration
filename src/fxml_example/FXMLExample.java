package fxml_example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLExample extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		Stage anotherStage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));

		Scene scene = new Scene(root, 1000, 800);

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
		
        Parent anotherRoot = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
        Scene anotherScene = new Scene(anotherRoot);
        anotherStage.setScene(anotherScene);
        anotherStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
