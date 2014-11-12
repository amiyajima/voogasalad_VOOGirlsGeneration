package authoring.concretefeatures;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ModulesList extends VBox{
	public ModulesList(){
		Label modsLabel = new Label("Available modules");
		ObservableList<String> availableModules = FXCollections.observableArrayList(
				"Attack", "Movement", "Build", "Swim", "Fly", "Capture");
		ListView<String> mods = new ListView<String>(availableModules);
		
		Collections.sort(availableModules);
		
		Label addedModsLabel = new Label("Added modules");
		ObservableList<String> addedModules = FXCollections.observableArrayList();
		ListView<String> addedMods = new ListView<String>(addedModules);
		

		HBox addRemoveMods = new HBox(100);
		Button addModule = new Button("Add Module >>");
		addModule.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent click) {
				String s = mods.getSelectionModel().getSelectedItem();
				availableModules.remove(s);
				addedModules.add(s);
				Collections.sort(addedModules);
			}
		});
		
		Button removeModule = new Button("<< Remove Module");
		removeModule.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent click) {
				String s = addedMods.getSelectionModel().getSelectedItem();
				addedModules.remove(s);
				availableModules.add(s);
				Collections.sort(availableModules);
			}
		});
		addRemoveMods.getChildren().addAll(addModule, removeModule);
		
		getChildren().addAll(modsLabel, mods, addRemoveMods, addedModsLabel, addedMods);
	}
}
