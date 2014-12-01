package authoring.createedit;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
public class ModulesList extends VBox {
	public static final int BUTTON_SPACING = 150;
	public static final String AVAILABLE_MODULES_LABEL = "Available Actions";
	public static final String ADDED_MODULES_LABEL = "Added Actions";
	public static final String ADD_MODULES_BUTTON = "Add Action >>";
	public static final String REMOVE_MODULES_BUTTON = "<< Remove Action";
	
	ObservableList<String> addedModules;
	
	public ModulesList(ObservableList<String> availableActions, ObservableList<String> addedActions){
            VBox availableModulesVBox = new VBox();
            VBox addedModulesVBox = new VBox();
        
        /**
         * To ensure that there are no duplicates
         */
        availableActions.removeAll(addedActions);
        

		Label availableModsLabel = new Label(AVAILABLE_MODULES_LABEL);
		ObservableList<String> availableModules = availableActions;
		availableModules.addAll("Blah", "one", "lsdkfjdsf");
		ListView<String> mods = new ListView<String>(availableModules);
		mods.setMaxHeight(400);
		Collections.sort(availableModules);

		Label addedModsLabel = new Label(ADDED_MODULES_LABEL);
		addedModules = addedActions;
		ListView<String> addedMods = new ListView<String>(addedModules);
		mods.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent click) {
			    if(click.getClickCount() == 2) {
			        String s = mods.getSelectionModel().getSelectedItem();
			        availableModules.remove(s);
			        addedModules.add(s);
			        //TODO removed this check because unnecessary, is that okay?
			        //if(availableModules.contains(s)){
			    }
			    Collections.sort(addedModules);
			}
		});
		
		addedMods.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent click) {
				if(click.getClickCount() == 2){
				    String s = addedMods.getSelectionModel().getSelectedItem();
				    addedModules.remove(s);
				    availableModules.add(s);
				}
				Collections.sort(availableModules);
			}
		});		
		addedMods.setMaxHeight(400);
                availableModulesVBox.getChildren().addAll(availableModsLabel, mods);
                availableModulesVBox.setSpacing(5);
                addedModulesVBox.getChildren().addAll(addedModsLabel, addedMods);
                addedModulesVBox.setSpacing(5);
                setSpacing(5);
		getChildren().addAll(availableModulesVBox, addedModulesVBox);
	}

	public ObservableList<String> getSelectedActions(){
		return addedModules;
	}
}
