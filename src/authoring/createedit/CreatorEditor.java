package authoring.createedit;

import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.LibraryView;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CreatorEditor extends TabPane {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 350;
    private static final String CREATE = "Creator";
    private static final String EDIT = "Editor";

    public CreatorEditor(LibraryView myLibraryView, ActionData myActions, PieceTypeData pieceTypeData) {
        setMinWidth(WIDTH);
        setMinHeight(HEIGHT);
        
        EditThings editAccordion = new EditThings(myLibraryView, myActions, pieceTypeData);
        Tab editTab = new Tab();
        editTab.setClosable(false);
        editTab.setContent(editAccordion);
        editTab.setText(EDIT);
                
//        CreateThings createAccordion = new CreateThings(editAccordion, myLibraryView, myActions, pieceTypeData);
        Tab createTab = new Tab();
        createTab.setClosable(false);
//        createTab.setContent(createAccordion);
        createTab.setText(CREATE);

        
        getTabs().addAll(createTab, editTab);
    }

}
