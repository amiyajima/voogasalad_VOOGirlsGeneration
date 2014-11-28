package authoring.concretefeatures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SuperEditorMenu extends TitledPane {

    private static final int HEIGHT = 400;
    private static final int WIDTH = 350;
    private static final int BUTTON_SPACING = 10;
    
    private static final String EDIT_BUTTON = "Edit";
    private static final String REMOVE_BUTTON = "Remove";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private static final Insets MARGINS = new Insets(20, WIDTH/8, 20, WIDTH/8);
    private static final String LABEL_CSS = "-fx-font-size: 12pt;";
    
    protected String name = "Everything Editor";
    protected String listLabel = "Things";

    private static final String EDIT_EVENT_BUTTON = "Edit Event";
    private static final String REMOVE_EVENT_BUTTON = "Remove Event";
    
    private ObservableList<?> things;
    private ListView<String> thingsView;
                
    public SuperEditorMenu(String name, String nameOfList){
            setHeight(HEIGHT);
            setWidth(WIDTH);
            
            this.name = name;
            listLabel = nameOfList;
           
            VBox box = new VBox();
            box.setSpacing(10);
            box.setPadding(MARGINS);
            
            HBox labelBox = new HBox();
            labelBox.setSpacing(10);
            Label eventsLabel = new Label(nameOfList);
            eventsLabel.setStyle(LABEL_CSS);
            labelBox.getChildren().add(eventsLabel);            
            
            initialize();
            initializeButtons();
            setText(name);
    }
    
    protected void initialize() {


    }
    
    protected void initialize() {
            things = FXCollections.observableArrayList("woo");
            thingsView = new ListView<String>(things);
            thingsView.setMaxWidth(WIDTH - (MARGINS.getRight()*2));
            thingsView.setMaxHeight(HEIGHT - (MARGINS.getBottom()*2));
            
            HBox buttons = new HBox(BUTTON_SPACING);
            Button editEvent = new Button(EDIT_EVENT_BUTTON);
            Button removeEvent = new Button(REMOVE_EVENT_BUTTON);
            editEvent.setMaxWidth(WIDTH - (MARGINS.getRight()*2) - BUTTON_SPACING);
            removeEvent.setMaxWidth(WIDTH - (MARGINS.getRight()*2) - BUTTON_SPACING);
            buttons.setSpacing(BUTTON_SPACING);
            buttons.getChildren().addAll(editEvent, removeEvent);
            editEvent.setOnMouseClicked(event->editEvent());
            removeEvent.setOnMouseClicked(event->removeEvent());
            
            box.getChildren().addAll(labelBox, eventsView, buttons);
            setContent(box);
            setText(NAME);
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
