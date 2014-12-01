package authoring.createedit;

import javafx.scene.control.Accordion;
import authoring.concretefeatures.LibraryUnitEditor;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.LibraryView;

public class EditThings extends Accordion {
    EventEditor myEventEditor;
    UnitEditor myUnitEditor;
    TerrainEditor myTerrainEditor;
    //ActionEditor myActionEditor;

    public EditThings(LibraryView libraryView, ActionData actions, PieceTypeData pieceTypeData) {
        myEventEditor = new EventEditor();
        myUnitEditor = new UnitEditor(actions, pieceTypeData);
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
