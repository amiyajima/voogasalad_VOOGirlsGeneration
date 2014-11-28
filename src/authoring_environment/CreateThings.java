package authoring_environment;

import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import authoring.concretefeatures.ActionCreator;
import authoring.concretefeatures.EventCreator;
import authoring.concretefeatures.TerrainCreator;
import authoring.concretefeatures.UnitCreator;
import authoring.data.ActionData;

public class CreateThings extends Accordion {

    public CreateThings(EditThings editAccordion, LibraryView libraryView, ActionData actions) {
        EventCreator eventCreator = new EventCreator(editAccordion.getEventEditor().getEvents());
        UnitCreator unitCreator = new UnitCreator(libraryView, actions, editAccordion.getUnitEditor().getUnits());
        TerrainCreator terrainCreator = new TerrainCreator(libraryView);
        ActionCreator actionCreator = new ActionCreator(actions);
        getPanes().addAll(eventCreator, unitCreator, terrainCreator, actionCreator);
        setExpandedPane(eventCreator);
    }
}
