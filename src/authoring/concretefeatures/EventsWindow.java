package authoring.concretefeatures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.UIspecs;

public class EventsWindow extends PopupWindow{

	public static final int HEIGHT = 400;
	public static final int WIDTH = 400;
	public static final String NAME = "Events";
	public static final int BUTTON_SPACING = 20;
	public static final String NEW_EVENT_BUTTON = "Add Event";
	public static final String EDIT_EVENT_BUTTON = "Edit Event";
	public static final String REMOVE_EVENT_BUTTON = "Remove Event";
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private static final Insets MARGINS = new Insets(20, WIDTH/8, 20, WIDTH/8 - 10);
	private static final Insets LABEL_MARGINS = new Insets(10, WIDTH/4, 10, WIDTH/4 - 10);
	private static final String LABEL_CSS = "-fx-font-size: 25pt;";
	private static final String BUTTON_CSS = "-fx-padding: 10;";
	
	private ObservableList<String> events;
	private ListView<String> eventsView;
		
	public EventsWindow(){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		initialize();
	}
	
	@Override
	protected void initialize() {
				
    	        VBox box = new VBox();
                box.setPadding(MARGINS);
                box.setSpacing(10);
                
                HBox labelBox = new HBox();
                labelBox.setPadding(LABEL_MARGINS);
		Label eventsLabel = new Label(NAME);
		labelBox.getChildren().add(eventsLabel);
                
                
		eventsLabel.setStyle(LABEL_CSS);
		events = FXCollections.observableArrayList("woo");
		eventsView = new ListView<String>(events);
				
		HBox buttons = new HBox(BUTTON_SPACING);
		Button newEvent = new Button(NEW_EVENT_BUTTON);
		Button editEvent = new Button(EDIT_EVENT_BUTTON);
		Button removeEvent = new Button(REMOVE_EVENT_BUTTON);
		newEvent.setStyle(BUTTON_CSS);
		newEvent.setMaxWidth((WIDTH - WIDTH/8)/3 - BUTTON_SPACING/3);
		editEvent.setStyle(BUTTON_CSS);
		editEvent.setMaxWidth((WIDTH - WIDTH/8)/3 - BUTTON_SPACING/3);
		removeEvent.setStyle(BUTTON_CSS);             
		removeEvent.setMaxWidth((WIDTH - WIDTH/8)/3 - BUTTON_SPACING/3);
		buttons.setSpacing(BUTTON_SPACING);
		buttons.getChildren().addAll(newEvent, editEvent, removeEvent);
		
		newEvent.setOnMouseClicked(event->newEvent());
		editEvent.setOnMouseClicked(event->editEvent());
		removeEvent.setOnMouseClicked(event->removeEvent());
		
		box.getChildren().addAll(labelBox, eventsView, buttons);
		
		Scene scene = new Scene(box);
		scene.getStylesheets().add(STYLESHEET);
		setScene(scene);
	}
	
	private void newEvent(){
		PopupWindow p = new EventCreator();
		p.show();
	}

	private void editEvent(){
		String s = eventsView.getSelectionModel().getSelectedItem();
		PopupWindow p = new EventEditor(s);
		p.show();
	}
	
	private void removeEvent(){
		String s = eventsView.getSelectionModel().getSelectedItem();
		events.remove(s);
	}
}
