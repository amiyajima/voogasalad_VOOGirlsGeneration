package authoring_environment;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import authoring.concretefeatures.EventsWindow;
import authoring.concretefeatures.TerrainCreator;
import authoring.concretefeatures.UnitCreator;
import authoring.data.ActionData;

public class Creator extends Accordion {
    private List<TitledPane> myTitlePanes = new ArrayList<TitledPane>();

    public Creator(LibraryView libraryView, ActionData actions) {
        EventsWindow eventsWindow = new EventsWindow();
        UnitCreator unitCreator = new UnitCreator(libraryView, actions);
        TerrainCreator terrainCreator = new TerrainCreator(libraryView);
        //getChildren().addAll(eventsWindow, unitCreator, terrainCreator);
        getPanes().addAll(eventsWindow, unitCreator, terrainCreator);
        setExpandedPane(eventsWindow);
    }
}
