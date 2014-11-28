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
    private  static final String UNITS_LIST_NAME = "Units";
    private static final int BUTTON_SPACING = 10;
    private static final String EDIT_BUTTON = "Edit";
    private static final String REMOVE_BUTTON = "Remove";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    private static final Insets MARGINS = new Insets(20, WIDTH/8, 20, WIDTH/8);
    private static final String LABEL_CSS = "-fx-font-size: 12pt;";
    
    private ObservableList<String> units;
    private ListView<String> unitsView;
    
    private SuperEditorMenu myEditor;
                
    public UnitEditor(){
        myEditor = new SuperEditorMenu(NAME, UNITS_LIST_NAME);
        units = FXCollections.observableArrayList("woo", "blah", "choochoo");
        myEditor.initializeLibrary(units);
        
        setText(NAME);
        setContent(myEditor);
    }
    
    public ObservableList<String> getUnits() {
        return units;
    }


}
