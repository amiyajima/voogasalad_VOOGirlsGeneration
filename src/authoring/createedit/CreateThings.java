package authoring.createedit;

import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.LibraryView;

public class CreateThings extends Accordion {

    public CreateThings(EditThings editAccordion, LibraryView libraryView, ActionData actions, PieceTypeData pieceTypeData) {
        EventCreator eventCreator = new EventCreator(editAccordion.getEventEditor().getEvents());
        UnitCreator unitCreator = new UnitCreator(libraryView, actions, editAccordion.getUnitEditor().getUnits(), pieceTypeData);
        TerrainCreator terrainCreator = new TerrainCreator(libraryView);
        ActionCreator actionCreator = new ActionCreator(actions);
        getPanes().addAll(eventCreator, unitCreator, terrainCreator, actionCreator);
        setExpandedPane(eventCreator);
    }
}
