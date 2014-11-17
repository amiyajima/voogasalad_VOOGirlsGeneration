package authoring.concretefeatures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.UIspecs;

public class EventsWindow extends PopupWindow{

	public static final int HEIGHT = 400;
	public static final int WIDTH = 400;
	public static final String NAME = "Events";
	public static final int BUTTON_SPACING = 20;
	
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
                box.setPadding(UIspecs.allPadding);
                box.setSpacing(5);		
		
		Label eventsLabel = new Label("Events");
                eventsLabel.setPadding(UIspecs.topRightPadding);		
		events = FXCollections.observableArrayList("woo");
		eventsView = new ListView<String>(events);
				
		HBox buttons = new HBox(BUTTON_SPACING);
		Button newEvent = new Button("Add event");
		Button editEvent = new Button("Edit event");
		Button removeEvent = new Button("Remove event");
		buttons.getChildren().addAll(newEvent, editEvent, removeEvent);
		
		newEvent.setOnMouseClicked(event->newEvent());
		editEvent.setOnMouseClicked(event->editEvent());
		removeEvent.setOnMouseClicked(event->removeEvent());
		
		box.getChildren().addAll(eventsLabel, eventsView, buttons);
		
		setScene(new Scene(box));
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
