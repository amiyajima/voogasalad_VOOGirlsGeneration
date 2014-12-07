package authoring.createedit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class EventEditor extends TitledPane {

    private static final String NAME = "Event Editor";
    private  static final String EVENTS_LIST_NAME = "Events";
    private static final String EDIT_EVENT_BUTTON = "Edit Event";
    private static final String REMOVE_EVENT_BUTTON = "Remove Event";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    
    private ObservableList<String> events;
    private ListView<String> eventsView;
    
    private SuperEditorMenu myEditor;
    
    public EventEditor() {
    	myEditor = new SuperEditorMenu(NAME, EVENTS_LIST_NAME);
    	events = FXCollections.observableArrayList("Thing 1", "Thing 2");
    	eventsView = myEditor.initializeLibrary(events);

    	Button editEvent = new Button(EDIT_EVENT_BUTTON);
    	Button removeEvent = new Button(REMOVE_EVENT_BUTTON);
    	editEvent.setOnMouseClicked(event->editEvent());
    	removeEvent.setOnMouseClicked(event->removeEvent());
    	myEditor.initializeButtons(editEvent, removeEvent);

    	setText(NAME);
    	setStyle(STYLESHEET);
    	setContent(myEditor);
    }
    
    private void editEvent(){
        String s = eventsView.getSelectionModel().getSelectedItem();
    }
    
    private void removeEvent(){
    	String s = eventsView.getSelectionModel().getSelectedItem();
    	events.remove(s);
    }
    
    public ObservableList<String> getEvents() {
        return events;
    }
}
