package fxml_main;

import gamedata.action.Action;
import java.awt.geom.Point2D;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import authoring_environment.UIspecs;


/**
 * @author seungwonlee
 *
 */
public class ActionViewer extends Pane {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 150;
    private static final String VIEWER_TITLE = "Action Viewer";
    private static final String NAME_LABEL = "Name: ";
    private static final String ACTION_RANGE = "Action Range Coordinates: ";
    private static final String EFFECT_RANGE = "Effect Range Coordinates: ";
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";
    private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);

    private String myName;
    private List<Point2D> myEffectRange;
    private List<Point2D> myActionRange;

    public ActionViewer (Action action) {
        myName = action.toString();
        myEffectRange = action.getEffectRange();
        myActionRange = action.getActionRange();
        setHeight(HEIGHT);
        setWidth(WIDTH);
        initialize();
    }

    protected void initialize () {

        VBox box = new VBox();
        box.setPadding(MARGINS);
        box.setSpacing(10);

        HBox labelBox = new HBox();
        Label eventsLabel = new Label(VIEWER_TITLE);
        eventsLabel.setStyle(LABEL_CSS);
        labelBox.getChildren().add(eventsLabel);

        HBox names = createHBox();
        HBox aRange = createHBox();
        HBox eRange = createHBox();

        names.getChildren().add(new Label(NAME_LABEL + myName));
        aRange.getChildren().add(new Label(ACTION_RANGE));
        eRange.getChildren().add(new Label(EFFECT_RANGE));

        for (Point2D point : myActionRange) {
//            System.out.println(("( " + point.getX() + "," + point.getY() + " )"));
            aRange.getChildren().add(new Label("( " + point.getX() + "," + point.getY() + " )"));
        }

        for (Point2D point : myEffectRange) {
            eRange.getChildren().add(new Label("( " + point.getX() + "," + point.getY() + " )"));
        }

        box.getChildren().addAll(labelBox, names, aRange, eRange);
        getChildren().addAll(box);
    }

    public HBox createHBox () {
        HBox box = new HBox();
        box.setPadding(UIspecs.allPadding);
        box.setSpacing(5);
        return box;
    }
}
