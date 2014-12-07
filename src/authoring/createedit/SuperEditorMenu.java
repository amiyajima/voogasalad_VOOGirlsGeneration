package authoring.createedit;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SuperEditorMenu extends VBox {

    private static final int HEIGHT = 400;
    private static final int WIDTH = 350;
    private static final int BUTTON_SPACING = 10;
    private static final Insets MARGINS = new Insets(20, WIDTH/8, 20, WIDTH/8);
    private static final int BUTTON_SIZE = 50;
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private static final String LABEL_CSS = "-fx-font-size: 12pt;";
                    
    public SuperEditorMenu(String name, String nameOfList){
            setHeight(HEIGHT);
            setWidth(WIDTH);
            
            setSpacing(10);
            setPadding(MARGINS);
            
            Label label = new Label(nameOfList);
            label.setStyle(LABEL_CSS);
            getChildren().add(label);            
    }
    
    protected ListView<String> initializeLibrary(ObservableList<String> thingsInLibrary) {
        //TODO assuming that thingsInLibrary consists of Strings for now
        ListView<String> thingsView = new ListView<String>(thingsInLibrary);
        thingsView.setMaxWidth(WIDTH - (MARGINS.getRight()*2));
        thingsView.setMaxHeight(HEIGHT - (MARGINS.getBottom()*2));
        getChildren().add(thingsView);
        return thingsView;
    }
    
    protected void initializeButtons(Button editButton, Button removeButton, Button...args) {
            editButton.setPrefWidth(BUTTON_SIZE);
            removeButton.setPrefWidth(BUTTON_SIZE);

            HBox buttons = new HBox(BUTTON_SPACING);
            buttons.getChildren().addAll(editButton, removeButton);
            
            for (Button button : args) {
                button.setPrefWidth(BUTTON_SIZE);
                buttons.getChildren().add(button);
            }
            
            getChildren().addAll(buttons);
    }
}
