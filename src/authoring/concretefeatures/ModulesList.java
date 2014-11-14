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

/**
 * GUI element that holds lists of available/added modules for a given Unit/Terrain. Used
 * to select modules when creating new templates of those units/terrain.
 * @author Mike Zhu
 *
 */
public class ModulesList extends VBox{
	public static final int BUTTON_SPACING = 150;
	
	public ModulesList(){
		Label modsLabel = new Label("Available modules");
		ObservableList<String> availableModules = FXCollections.observableArrayList(
				"Attack", "Move", "Build", "Swim", "Fly", "Capture");
		ListView<String> mods = new ListView<String>(availableModules);
		
		Collections.sort(availableModules);
		
		Label addedModsLabel = new Label("Added modules");
		ObservableList<String> addedModules = FXCollections.observableArrayList();
		ListView<String> addedMods = new ListView<String>(addedModules);
		

		HBox addRemoveMods = new HBox(BUTTON_SPACING);
		Button addModule = new Button("Add Module >>");
		addModule.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent click) {
				String s = mods.getSelectionModel().getSelectedItem();
				if(availableModules.contains(s)){
					availableModules.remove(s);
					addedModules.add(s);
				}
				Collections.sort(addedModules);
			}
		});
		
		Button removeModule = new Button("<< Remove Module");
		removeModule.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent click) {
				String s = addedMods.getSelectionModel().getSelectedItem();
				if(addedModules.contains(s)){
					addedModules.remove(s);
					availableModules.add(s);
				}
				Collections.sort(availableModules);
			}
		});
		addRemoveMods.getChildren().addAll(addModule, removeModule);
		
		getChildren().addAll(modsLabel, mods, addRemoveMods, addedModsLabel, addedMods);
	}
}
