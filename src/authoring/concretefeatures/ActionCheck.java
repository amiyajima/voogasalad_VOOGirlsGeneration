package authoring.concretefeatures;

import gamedata.action.Action;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private final String ACTOR = "Actor";
    private final String RECEIVER = "Receiver";
    private LibraryView myLibrary;

    private List<Action> myActions;
    private List<String> myPieces = new ArrayList<String>();
    private List<Patch> myPatches;
    private Map<String, Map> Conclusion;

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
        myPieces.add("Piece A");
        myPieces.add("Piece B");
        myPieces.add("Piece C");
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);

        VBox mainVBox = new VBox();
        mainVBox.getStyleClass().add("vbox");
        mainVBox.setId("vbox-main");
        VBox actionNameVBox = new VBox();
        VBox posActorVBox = new VBox();
        VBox posReceiverVBox = new VBox();

        ChoiceBox<String> actionTypes = new ChoiceBox<String>();
        initActionChooser(actionNameVBox, actionTypes);

        ChoiceBox<String> posActors = new ChoiceBox<String>();
        initActorChooser(posActorVBox, posActors);

        initReceiverChooser(posReceiverVBox, posActors.getValue(), actionTypes);
        
        
        
        
        mainVBox.getChildren().addAll(actionNameVBox, new Separator(), posActorVBox,
                                      new Separator(), posReceiverVBox);
        root.setContent(mainVBox);
        setScene(scene);

    }

    private void initReceiverChooser (VBox posReceiverVBox, String actor, ChoiceBox<String> actionTypes) {
        // TODO Auto-generated method stub
        Label posReceiverLabel = new Label(RECEIVER);
        Button posReceiversbtn = new Button("Possible Receivers");
        List<String> posReceivers = getReceivers(actor);

        posReceiversbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                PopupWindow receiversChooser = new ReceiverEditor(posReceivers, actor, actionTypes.getValue().toString());
                receiversChooser.show();
                // TODO: set myRange in here somewhere (within RangeEditor?)
            }
        });
        
        posReceiverVBox.getChildren().addAll(posReceiverLabel,posReceiversbtn);

    }

    private void initActorChooser (VBox posActorVBox, ChoiceBox<String> posActors) {
        // TODO Auto-generated method stub
        Label actorLabel = new Label(ACTOR);
        posActors.getItems().addAll("Piece A", "Piece B", "Piece C");

        HBox actorsHbox = new HBox();
        actorsHbox.getChildren().addAll(posActors);
        posActorVBox.getChildren().addAll(actorLabel, actorsHbox);

    }

    private void initActionChooser (VBox nameVBox, ChoiceBox<String> actionTypes) {
        // TODO: actionTypes needs to get List<String> that contains names of all the action types
        //
        Label targetLabel = new Label(ACTION_TYPE);
        actionTypes.getItems().addAll("Attack", "Heal", "AlltheRest");
        System.out.println(actionTypes.getItems().toString());

        HBox actionsHBox = new HBox();
        actionsHBox.getChildren().addAll(actionTypes);
        nameVBox.getChildren().addAll(targetLabel, actionsHBox);
    }

    private List<String> getReceivers (String actor) {
        List<String> receivers = new ArrayList<String>();
        for (String p : myPieces) {
            if (!p.equals(actor)) {
                receivers.add(p);
            }
        }
        return receivers;
    }

}
