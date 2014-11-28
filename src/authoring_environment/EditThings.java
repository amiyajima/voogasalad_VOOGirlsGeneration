package authoring_environment;

import javafx.scene.control.Accordion;
import authoring.concretefeatures.ActionCreator;
import authoring.concretefeatures.EventEditor;
import authoring.concretefeatures.EventCreator;
import authoring.concretefeatures.TerrainCreator;
import authoring.concretefeatures.UnitCreator;
import authoring.data.ActionData;

public class EditThings extends Accordion {
    EventEditor myEventEditor;

    public EditThings(LibraryView libraryView, ActionData actions) {
        myEventEditor = new EventEditor();
        UnitCreator unitCreator = new UnitCreator(libraryView, actions);
        TerrainCreator terrainCreator = new TerrainCreator(libraryView);
        ActionCreator actionCreator = new ActionCreator(actions);
        getPanes().addAll(myEventEditor, unitCreator, terrainCreator, actionCreator);
        setExpandedPane(myEventEditor);
    }
    
    protected EventEditor getEventEditor() {
        return myEventEditor;
    }
}
