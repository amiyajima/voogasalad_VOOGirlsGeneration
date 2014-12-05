package authoring.createedit;

import authoring.concretefeatures.LibraryUnitEditor;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class UnitEditor extends TitledPane {
    private static final String NAME = "Unit Editor";
    private static final String UNITS_LIST_NAME = "Units";
    private static final String EDIT_BUTTON = "Edit Unit";
    private static final String REMOVE_BUTTON = "Remove Unit";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
    
    private ObservableList<String> units;
    private ListView<String> unitsView;
    
    private SuperEditorMenu myEditor;
    private ActionData availableActions;
    private PieceTypeData availablePieces;
                
    public UnitEditor(ActionData availableActions, PieceTypeData availablePieces){
        this.availableActions = availableActions;
        this.availablePieces = availablePieces;
        
        myEditor = new SuperEditorMenu(NAME, UNITS_LIST_NAME);
        units = FXCollections.observableArrayList("woo", "blah", "choochoo");
        unitsView = myEditor.initializeLibrary(units);
        
        Button editEvent = new Button(EDIT_BUTTON);
        Button removeEvent = new Button(REMOVE_BUTTON);
        editEvent.setOnMouseClicked(event->editEvent());
        removeEvent.setOnMouseClicked(event->removeEvent());
        myEditor.initializeButtons(editEvent, removeEvent);
        
        setText(NAME);
        setStyle(STYLESHEET);
        setContent(myEditor);
    }
    
    private void editEvent(){
        String s = unitsView.getSelectionModel().getSelectedItem();
        LibraryUnitEditor individualUnitEditor = new LibraryUnitEditor(availablePieces.getPiece(s), availableActions);
        // individualUnitEditor.show();
    }

    private void removeEvent(){
            String s = unitsView.getSelectionModel().getSelectedItem();
            units.remove(s);
    }

    public ObservableList<String> getUnits() {
        return units;
    }
}
