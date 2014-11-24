package authoring.concretefeatures;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;


public class ReceiverEditor extends PopupWindow {

    private List<String> myPieces;

    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String NAME = "Action Receivers";
    private String myActor;
    private String myAction;

    private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";

    public ReceiverEditor (List<String> pieces, String actor, String action) {

        myPieces = pieces;
        myActor = actor;
        myAction = action;

        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        initialize();

    }

    @Override
    protected void initialize () {
        List<String> myPosReceivers = getReceivers(myPieces, myActor);
        //System.out.println(myPosReceivers);
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);

        VBox mainVBox = new VBox();
        mainVBox.getStyleClass().add("vbox");
        mainVBox.setId("vbox-main");
        VBox actionNameVBox = new VBox();
        VBox actorVBox = new VBox();
        VBox ReceiverVBox = new VBox();

        Label actionName = new Label("Action:");
        Button action = new Button(myAction);
        actionNameVBox.getChildren().addAll(actionName, action);

        Label actorType = new Label("Actor:");
        Button actor = new Button(myActor);
        actorVBox.getChildren().addAll(actorType, actor);

        initCheckBoxes(ReceiverVBox, myPosReceivers);

        mainVBox.getChildren().addAll(actionNameVBox, new Separator(), actorVBox, new Separator(),
                                      ReceiverVBox);
        root.setContent(mainVBox);
        setScene(scene);

    }

    private void initCheckBoxes (VBox ReceiverVBox, List<String> myPosReceivers) {
        // TODO Auto-generated method stub
        Label title = new Label("Pieces that can recieve this type of action");
        ReceiverVBox.getChildren().add(title);
        for (String p : myPosReceivers) {
            ReceiverVBox.getChildren().add(new CheckBox(p));
        }
    }

    private List<String> getReceivers (List<String> myPieces, String actor) {
        List<String> receivers = new ArrayList<String>();
        for (String p : myPieces) {
            if (!p.equals(actor)) {
                receivers.add(p);
            }
        }
        return receivers;
    }

}
