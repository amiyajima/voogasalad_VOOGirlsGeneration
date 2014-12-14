package fxml_main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIcontainerMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Voogirls_GUIcontainer.fxml"));
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("Girls GenerEditor");
        primaryStage.setScene(scene);
        primaryStage.show();      
	}
	    
	public static void main (String[] args) {
        launch(args);
    }

}
