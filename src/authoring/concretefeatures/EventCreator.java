package authoring.concretefeatures;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring_environment.UIspecs;

public class EventCreator extends TitledPane {

        //private static final int HEIGHT = 350;
	private static final int WIDTH = 350;
	private static final String NAME = "Event Creator";
	private static final String NEW_EVENT_BUTTON = "+ Event";
	private String EVENT_NAME_LABEL = "Event Name: ";
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private static final Insets MARGINS = new Insets(20, WIDTH/8, 20, WIDTH/8);
	private static final String LABEL_CSS = "-fx-font-size: 12pt;";
	
	private ObservableList<String> events;
			
	public EventCreator(ObservableList<String> events2){
	    events = events2;
		//setHeight(HEIGHT);
		//setWidth(WIDTH);
		setText(NAME);
		initialize();
	}
	
    protected void initialize() {
				
    	        VBox box = new VBox();
                box.setSpacing(10);
                box.setPadding(MARGINS);
                
                HBox name = new HBox();
                Label nameLabel = new Label(EVENT_NAME_LABEL );
                nameLabel.setPadding(UIspecs.topRightPadding);
                TextField eventName = new TextField();
                eventName.setMinWidth(WIDTH/2 + 20);
                name.setSpacing(10);
                name.getChildren().addAll(nameLabel, eventName);

                Button newEvent = new Button(NEW_EVENT_BUTTON);
                newEvent.setOnMouseClicked(event->newEvent(eventName.getText()));
                
		box.getChildren().addAll(name, newEvent);
		setContent(box);
		setText(NAME);
	}
	
	private void newEvent(String eventName){
	    events.add(eventName);
	}
}
