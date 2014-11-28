package authoring_environment;

import javafx.scene.control.Accordion;
import authoring.concretefeatures.ActionCreator;
import authoring.concretefeatures.EventEditor;
import authoring.concretefeatures.TerrainCreator;
import authoring.concretefeatures.TerrainEditor;
import authoring.concretefeatures.UnitCreator;
import authoring.concretefeatures.LibraryUnitEditor;
import authoring.concretefeatures.UnitEditor;
import authoring.data.ActionData;

public class EditThings extends Accordion {
    EventEditor myEventEditor;
    UnitEditor myUnitEditor;
    TerrainEditor myTerrainEditor;
    //ActionEditor myActionEditor;

    public EditThings(LibraryView libraryView, ActionData actions) {
        myEventEditor = new EventEditor();
        myUnitEditor = new UnitEditor();
        TerrainCreator terrainCreator = new TerrainCreator(libraryView);
        //TerrainEditor terrainEditor = new TerrainEditor();
        ActionCreator actionCreator = new ActionCreator(actions);
        getPanes().addAll(myEventEditor, myUnitEditor, terrainCreator, actionCreator);
        setExpandedPane(myEventEditor);
    }
    
    protected EventEditor getEventEditor() {
        return myEventEditor;
    }
    
    protected UnitEditor getUnitEditor() {
        return myUnitEditor;
    }
}
