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

    public CreateThings(ObservableList<String> events, EditThings editAccordion, LibraryView libraryView, ActionData actions) {
        EventCreator eventsWindow = new EventCreator(events);
        UnitCreator unitCreator = new UnitCreator(libraryView, actions);
        TerrainCreator terrainCreator = new TerrainCreator(libraryView);
        ActionCreator actionCreator = new ActionCreator(actions);
        getPanes().addAll(eventsWindow, unitCreator, terrainCreator, actionCreator);
        setExpandedPane(eventsWindow);
    }
}
