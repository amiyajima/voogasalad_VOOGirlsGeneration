package authoring.eventeditor;


import java.io.IOException;

import gamedata.events.Event;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventEditorMain extends Application{

	private Stage primaryStage;

	@Override
	public void start(Stage stage) throws IOException {

		primaryStage = stage;

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

	public void showNewConditionWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NewCondition.fxml"));
		Parent root = loader.load();

		Stage newConditionStage  = new Stage();
		newConditionStage.setTitle("New Condition");
		newConditionStage.initModality(Modality.WINDOW_MODAL);
		newConditionStage.initOwner(primaryStage);
		Scene scene = new Scene(root);
		newConditionStage.setScene(scene);

		NewConditionController controller = loader.getController();

		//		controller.setEvent(event);

		newConditionStage.showAndWait();
	}
	
	public void showNewActionWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NewAction.fxml"));
		Parent root = loader.load();

		Stage newConditionStage  = new Stage();
		newConditionStage.setTitle("New Action");
		newConditionStage.initModality(Modality.WINDOW_MODAL);
		newConditionStage.initOwner(primaryStage);
		Scene scene = new Scene(root);
		newConditionStage.setScene(scene);

		NewConditionController controller = loader.getController();

		//		controller.setEvent(event);

		newConditionStage.showAndWait();
	}
}
