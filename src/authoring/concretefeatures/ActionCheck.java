package authoring.concretefeatures;

import gamedata.action.Action;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;


public class ActionCheck extends PopupWindow {

    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String ACTION = "Action";
    private final String NAME = "Available Actions for Units";
    private final String ACTION_TYPE = "Action Type";
    private LibraryView myLibrary;

    private List<Action> myActions;
    private List<Piece> myPieces;
    private List<Patch> myPatches;
    private Map<String,Map>  Conclusion;
    

    private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";

    /**
     * Constructor for ActionCheck popup window
     * 
     * @param actionLst List of all the actions
     * @param pieceLst List of pieces
     * @param patchLst List of patches
     */
    // public ActionCheck (List<Action> actionLst, List<Piece> pieceLst, List<Patch> patchLst) {
    //
    // myActions = actionLst;
    // myPieces = pieceLst;
    // myPatches = patchLst;
    //
    // setHeight(HEIGHT);
    // setWidth(WIDTH);
    // setTitle(NAME);
    // initialize();
    //
    // }

    public ActionCheck () {

        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        initialize();

    }

    @Override
    protected void initialize () {
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);
        
        VBox mainVBox = new VBox();
        mainVBox.getStyleClass().add("vbox");
        mainVBox.setId("vbox-main");
        VBox actionNameVBox = new VBox();
        VBox posPieceVBox = new VBox();
        
        ChoiceBox<String> actionTypes = new ChoiceBox<String>();
        initActionChooser(actionNameVBox, actionTypes );
        
        
        
        
        
        
        
        mainVBox.getChildren().addAll(actionNameVBox, new Separator() );
                      root.setContent(mainVBox);
                      setScene(scene);
        
    }

    private void initActionChooser (VBox nameVBox, ChoiceBox<String> actionTypes) {
        // TODO: actionTypes needs to get List<String> that contains names of all the action types
        // 
        Label targetLabel = new Label(ACTION_TYPE);
        actionTypes.getItems().addAll("Attack", "Heal", "AlltheRest");

        HBox targetAndStatHBox = new HBox();
        targetAndStatHBox.getChildren().addAll(actionTypes);
        nameVBox.getChildren().addAll(targetLabel, targetAndStatHBox);
    }
    
    private List<Piece> getReceivers(Piece actor){
        List<Piece> receivers = new ArrayList<Piece>();
        for(Piece p: myPieces){
            if(!p.equals(actor)){
                receivers.add(p);
            }
        }
        return receivers;
    }
    
}
