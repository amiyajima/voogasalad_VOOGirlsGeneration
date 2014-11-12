package authoring.concretefeatures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import authoring.abstractfeatures.CreatorPane;

public class UnitCreator extends CreatorPane{
	public UnitCreator(){
		initialize();
	}
	
	private void initialize(){
		HBox names = new HBox();
		HBox images = new HBox();
		
		Label nameLabel = new Label("Name");
		TextField unitName = new TextField();
		names.getChildren().addAll(nameLabel, unitName);
		
		Label loadLabel = new Label("Unit image");
		Button loadImage = new Button("Load image");
		images.getChildren().addAll(loadLabel, loadImage);
		
		Label modsLabel = new Label("Available modules");
		ObservableList<String> availableModules = FXCollections.observableArrayList(
				"Test1", "Test2", "Test3", "Test4", "Test5", "Test6");
		ListView<String> mods = new ListView<String>(availableModules);
		
		
		Label addedModsLabel = new Label("Added modules");
		ObservableList<String> addedModules = FXCollections.observableArrayList("TestA", "TestB");
		ListView<String> addedMods = new ListView<String>(addedModules);
		

		HBox addRemoveMods = new HBox(100);
		Button addModule = new Button("Add Module >>");
		addModule.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent click) {
				String s = availableModules.get(0);
				availableModules.remove(0);
				addedModules.add(s);
			}
		});
		
		Button removeModule = new Button("<< Remove Module");
		removeModule.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent click) {
				String s = addedModules.get(0);
				addedModules.remove(0);
				availableModules.add(s);
			}
		});
		addRemoveMods.getChildren().addAll(addModule, removeModule);
		
		Button goButton = new Button("Create new unit template");
		goButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				System.out.println(unitName.getText());
			}
			
		});
		getChildren().addAll(names, loadImage, modsLabel, mods, addRemoveMods, addedModsLabel, addedMods, goButton);
	}
}
