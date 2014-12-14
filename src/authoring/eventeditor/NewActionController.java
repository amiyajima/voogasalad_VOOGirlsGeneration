package authoring.eventeditor;

import gamedata.action.StatsTotalLogic;
import gamedata.action.TotalLogicBox;
import gamedata.events.GameStateGlobalAction;
import gamedata.events.GlobalAction;
import gamedata.events.globalaction.ChangePlayerStat;
import gamedata.events.globalaction.MakePieceAtLocation;
import gamedata.events.globalaction.DeletePieceAtLocation;
import gamedata.events.globalaction.LevelChange;
import gamedata.events.globalaction.EndTurn;
import gamedata.gamecomponents.IChangeGameState;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import authoring.data.EventsDataWrapper;
import utilities.ClassGrabber;
import utilities.reflection.Reflection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class NewActionController implements Initializable {

    // TODO: How to pass in the list of all Conditions?

    @FXML
    private TextField myYField;
    @FXML
    private HBox myUnitActionsBox;
    @FXML
    private ChoiceBox<String> myRefTypeBox;
    @FXML
    private ChoiceBox<String> myTypeChoiceBox;
    @FXML
    private TextField myNextLevelField;
    @FXML
    private TextField myXField;
    @FXML
    private TextField myPlayerIDField;
    @FXML
    private Button myDoneButton;
    @FXML
    private ChoiceBox<IHasStats> myRefNameBox;
    @FXML
    private HBox myStatsHBox;
    private TotalLogicBox myTotalLogic;

    private List<Class> actionList;

    private Consumer<GlobalAction> myDoneLambda;
    private EventsDataWrapper myData;
    private IChangeGameState myState;

    @Override
    public void initialize (URL fxmlFileLocation, ResourceBundle resources) {

        // TODO: Remove print stack traces. Add in error windows
        actionList = new ArrayList<>();
        try {
            actionList = Arrays.asList(ClassGrabber.getClasses("gamedata.events.globalaction"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        List<String> displayList = new ArrayList<>();
        for (Class<?> c : actionList) {
            displayList.add(c.toString());
        }
        displayList = trimClassList(displayList);

        myTypeChoiceBox.getItems().addAll(displayList);

        myTypeChoiceBox.getSelectionModel().selectedItemProperty()
                .addListener(
                             (observable, oldValue, selectedType) -> showActionEditorPane());
        StatsTotalLogic stl = new StatsTotalLogic();
        TotalLogicBox statsLogic = new TotalLogicBox(stl);
        myStatsHBox.getChildren().add(statsLogic);
    }

    // TODO: Refactor this to be less gross. SO MUCH REPEATED CODE
    @FXML
    private void handleDoneButton () {
        int idx = myTypeChoiceBox.getSelectionModel().getSelectedIndex();
        Class<?> c = actionList.get(idx);

        if (c.equals(MakePieceAtLocation.class)) {
            Piece piece = (Piece) myRefNameBox.getSelectionModel().getSelectedItem();
            double x = Double.parseDouble(myXField.getText());
            double y = Double.parseDouble(myYField.getText());
            Point2D.Double point = new Point2D.Double(x, y);
            int playerID = Integer.parseInt(myPlayerIDField.getText());
            GlobalAction action = new MakePieceAtLocation(piece, point, playerID);
            myDoneLambda.accept(action);
        }
        else if (c.equals(DeletePieceAtLocation.class)) {
            double x = Double.parseDouble(myXField.getText());
            double y = Double.parseDouble(myYField.getText());
            Point2D.Double point = new Point2D.Double(x, y);
            GlobalAction action = new DeletePieceAtLocation(point);
            myDoneLambda.accept(action);
        }
        else if (c.equals(LevelChange.class)) {
            GlobalAction action = new LevelChange(myState, myNextLevelField.getText());
            myDoneLambda.accept(action);
        }
        else if (c.equals(ChangePlayerStat.class)) {
            // list<statstotallogic>, actor, receiver
            myTotalLogic.getStatsLogic();
            // GlobalAction action = new ChangePlayerStat();
            // myDoneLambda.accept(action);
        }

        String classPath = c.toString();
        classPath = classPath.substring(6);

        System.out.println(myState);
        GlobalAction action = (GlobalAction) Reflection.createInstance(classPath, myState);
        myDoneLambda.accept(action);
    }

    private List<String> trimClassList (List<String> actionList) {
        List<String> displayList = new ArrayList<>();
        for (String s : actionList) {
            String trimmed = trimClassPaths(s);
            displayList.add(trimmed);
        }
        return displayList;
    }

    // TODO: determine how to distinguish between different types of actions
    private void showActionEditorPane () {
        myNextLevelField.setVisible(false);
        myUnitActionsBox.setVisible(false);
        myStatsHBox.setVisible(false);

        int idx = myTypeChoiceBox.getSelectionModel().getSelectedIndex();
        Class<?> c = actionList.get(idx);

        if (c.equals(MakePieceAtLocation.class)) {
            myUnitActionsBox.setVisible(true);
        }
        else if (c.equals(DeletePieceAtLocation.class)) {
            myUnitActionsBox.setVisible(true);
        }
        else if (c.equals(LevelChange.class)) {
            myNextLevelField.setVisible(true);
        }
        else if (c.equals(ChangePlayerStat.class)) {
            System.out.println("ChangePlayerStat class detected");
            myStatsHBox.setVisible(true);
        }

    }

    /**
     * Removes the classpath prefixes for each Condition name
     * 
     * @param s
     */
    private String trimClassPaths (String s) {
        int idx = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '.') {
                idx = i;
                break;
            }
        }
        String trimmed = s.substring(idx + 1);
        return trimmed;
    }

    public void loadLambda (Consumer<GlobalAction> okLambda) {
        myDoneLambda = okLambda;
    }

    public void loadData (EventsDataWrapper data) {
        myData = data;
        myRefNameBox.getItems().addAll(myData.getPieceTypes());

    }

    public void loadState (IChangeGameState state) {
        myState = state;
    }
}
