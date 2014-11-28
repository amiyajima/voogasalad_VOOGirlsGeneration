package authoring_environment;

import javafx.scene.control.Accordion;
import authoring.concretefeatures.ActionCreator;
import authoring.concretefeatures.EventEditor;
import authoring.concretefeatures.TerrainCreator;
import authoring.concretefeatures.TerrainEditor;
import authoring.concretefeatures.UnitCreator;
import authoring.concretefeatures.LibraryUnitEditor;
import authoring.data.ActionData;

public class EditThings extends Accordion {
    EventEditor myEventEditor;
    LibraryUnitEditor myUnitEditor;
    TerrainEditor myTerrainEditor;
    //ActionEditor myActionEditor;

    public EditThings(LibraryView libraryView, ActionData actions) {
        myEventEditor = new EventEditor();
        UnitCreator unitCreator = new UnitCreator(libraryView, actions);
        myUnitEditor = new LibraryUnitEditor();
        TerrainCreator terrainCreator = new TerrainCreator(libraryView);
        //TerrainEditor terrainEditor = new TerrainEditor();
        ActionCreator actionCreator = new ActionCreator(actions);
        getPanes().addAll(myEventEditor, unitCreator, terrainCreator, actionCreator);
        setExpandedPane(myEventEditor);
    }
    
    protected EventEditor getEventEditor() {
        return myEventEditor;
    }
    
    protected LibraryUnitEditor getUnitEditor() {
        return myUnitEditor;
    }
}
