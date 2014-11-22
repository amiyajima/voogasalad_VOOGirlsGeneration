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
public class ModulesList extends HBox{
	public static final int BUTTON_SPACING = 150;
	public static final String AVAILABLE_MODULES_LABEL = "Available Modules";
	public static final String ADDED_MODULES_LABEL = "Added Modules";
	public static final String ADD_MODULES_BUTTON = "Add Module >>";
	public static final String REMOVE_MODULES_BUTTON = "<< Remove Module";
	
	public ModulesList(){
            VBox availableModulesVBox = new VBox();
            VBox addedModulesVBox = new VBox();

		Label availableModsLabel = new Label(AVAILABLE_MODULES_LABEL);
		ObservableList<String> availableModules = FXCollections.observableArrayList(
				"Attack", "Move", "Build", "Swim", "Fly", "Capture");
		ListView<String> mods = new ListView<String>(availableModules);
		Collections.sort(availableModules);

		Label addedModsLabel = new Label(ADDED_MODULES_LABEL);
		ObservableList<String> addedModules = FXCollections.observableArrayList();
		ListView<String> addedMods = new ListView<String>(addedModules);
		Button addModule = new Button(ADD_MODULES_BUTTON);
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
		
		Button removeModule = new Button(REMOVE_MODULES_BUTTON);
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
		
                availableModulesVBox.getChildren().addAll(availableModsLabel, mods, addModule);
                availableModulesVBox.setSpacing(5);
                addedModulesVBox.getChildren().addAll(addedModsLabel, addedMods, removeModule);
                addedModulesVBox.setSpacing(5);
                setSpacing(5);
		getChildren().addAll(availableModulesVBox, addedModulesVBox);
	}
}
