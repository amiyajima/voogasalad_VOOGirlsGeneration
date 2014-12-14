package authoring.eventeditor;

import gamedata.events.Condition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring.data.EventsDataWrapper;
import utilities.ClassGrabber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;

public class NewConditionController implements Initializable{

	//TODO: How to pass in the list of all Conditions?
	
	@FXML
	private ChoiceBox<String> conditionChoiceBox;
	@FXML
	private ScrollPane editorScrollPane;
	
	private List<Class> conditionsList;
	
	private Consumer<Condition> myDoneLambda;
	private EventsDataWrapper myData;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		//TODO: Remove print stack traces. Add in error windows
		conditionsList = new ArrayList<>();
		try {
			conditionsList = Arrays.asList(ClassGrabber.getClasses("gamedata.events.conditions"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		List<String> displayList = new ArrayList<>();
		for(Class<?> c: conditionsList){
			displayList.add(c.toString());
		}
		displayList = trimClassList(displayList);
		
		conditionChoiceBox.getItems().addAll(displayList);
		
		conditionChoiceBox.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedType) -> showConditionEditorPane());
	}

	private List<String> trimClassList(List<String> conditionsList) {
		List<String> displayList = new ArrayList<>();
		for(String s: conditionsList){
			String trimmed = trimClassPaths(s);
			displayList.add(trimmed);
		}
		return displayList;
	}
	
	private void showConditionEditorPane(){
		int idx = conditionChoiceBox.getSelectionModel().getSelectedIndex();
		Class<?> c = conditionsList.get(idx);
			
		editorScrollPane.setContent(new ConditionEditorPane(myDoneLambda, myData, c));
	}
	
	/**
	 * Removes the classpath prefixes for each Condition name
	 * @param s
	 */
	private String trimClassPaths(String s){
		int idx = 0;
		for(int i=s.length()-1; i>=0; i--){
			if(s.charAt(i)=='.'){
				idx = i;
				break;
			}
		}
		String trimmed = s.substring(idx+1);
		return trimmed;
	}

	public void loadLambda(Consumer<Condition> okLambda) {
		myDoneLambda = okLambda;
	}
	
	public void loadData(EventsDataWrapper data) {
		myData = data;
	}
}
