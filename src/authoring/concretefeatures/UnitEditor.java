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

public class UnitEditor extends TitledPane {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 350;
    private static final String NAME = "Unit Editor";
    private  static final String EVENTS = "Units";
    private static final int BUTTON_SPACING = 10;
    private static final String EDIT_BUTTON = "Edit";
    private static final String REMOVE_BUTTON = "Remove";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private static final Insets MARGINS = new Insets(20, WIDTH/8, 20, WIDTH/8);
    private static final String LABEL_CSS = "-fx-font-size: 12pt;";
    
    private ObservableList<String> units;
    private ListView<String> unitsView;
                
    public UnitEditor(){
            setHeight(HEIGHT);
            setWidth(WIDTH);
            setText(NAME);
            initialize();
    }
    
    protected void initialize() {
                            
            VBox box = new VBox();
            box.setSpacing(10);
            box.setPadding(MARGINS);
            
            HBox labelBox = new HBox();
            labelBox.setSpacing(10);
            Label eventsLabel = new Label(EVENTS);
            eventsLabel.setStyle(LABEL_CSS);
            labelBox.getChildren().add(eventsLabel);
            
            units = FXCollections.observableArrayList("woo");
            unitsView = new ListView<String>(events);
            unitsView.setMaxWidth(WIDTH - (MARGINS.getRight()*2));
            unitsView.setMaxHeight(HEIGHT - (MARGINS.getBottom()*2));
            
            HBox buttons = new HBox(BUTTON_SPACING);
            Button editUnit = new Button(EDIT_BUTTON);
            Button removeUnit = new Button(REMOVE_BUTTON);
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
