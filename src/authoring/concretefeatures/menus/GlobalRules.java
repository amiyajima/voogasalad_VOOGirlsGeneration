package authoring.concretefeatures.menus;

import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.ActionCheck;
import authoring.concretefeatures.RuleCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class GlobalRules extends Menu {

    private static final String NAME = "Global Rules";
    private static final String ITEM1 = "Edit Rules";

    public GlobalRules () {
        super(NAME);
        MenuItem creator = new MenuItem(ITEM1);

        setAction(creator);
        getItems().addAll(creator);
    }

    private void setAction (MenuItem item) {
        item.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent t) {
                PopupWindow p = new ActionCheck();
                p.show();
            }
        });
    }
}
