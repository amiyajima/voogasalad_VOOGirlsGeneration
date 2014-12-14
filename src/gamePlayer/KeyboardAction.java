package gamePlayer;

import gamedata.action.Action;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Allows users to use keyboard to "move the cursor" up and down the available actions in the
 * Controls panel.
 * KeyboardAction is strictly used for selecting actions in the Controls.
 * 
 * @author Yoonhyung
 *
 */
public class KeyboardAction {
    private static final double ACTION_LABEL_SHADOW_OFFSET = 6.0;
    Player myCurrentPlayer;
    Point2D myCurrentLocation;
    GameGridEffect myGameGridEffect;
    List<Action> myActions;
    Action myCurrentAction;
    int myActionIndex;
    Map<KeyCode, Point2D.Double> myKeyMap;

    public KeyboardAction () {
        myActionIndex = 0;
        myActions = new ArrayList<Action>();
        myKeyMap = new HashMap<KeyCode, Point2D.Double>();
    }

    public void setActionKeyControl (ViewController vc) {
        System.out.println("KeyboardAction ON");

        useDefaultMap();

//        if ((vc.getActivePiece() != null) &&
//            (vc.getGame().getCurrentPlayer().getType().equals("Human"))) {
        if ((vc.getActivePiece() != null)) {

            myActions = vc.getActivePiece().getActions();
            vc.getGridPane().requestFocus();
            vc.getGridPane().setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle (KeyEvent key) {

                    for (KeyCode mkc : myKeyMap.keySet()) {
                        if (key.getCode() == mkc) {
                            // Do action here
                            if (myKeyMap.get(mkc).equals(new Point2D.Double(1.0, 1.0))) {
                                System.out.println("Selected action with key");
                                vc.bindAction(myCurrentAction);
                            }

                            else {
                                // Select action here
                                int newActionIndex = myActionIndex - (int) myKeyMap.get(mkc).getY();
                                if ((newActionIndex >= 0) & (newActionIndex < myActions.size())) {
                                    myActionIndex = newActionIndex;
                                }

                                myCurrentAction = myActions.get(myActionIndex);
                                System.out.println("myActionIndex: " + myActionIndex);
                            }
                        }
                    }

                    markAction(vc);
                }
            });
        }
    }

    public void markAction (ViewController vc) {
        vc.getcontrolPane().getChildren().forEach(label -> {
            label.setEffect(null);
            Label l = (Label) label;
            if (l.getText() == myCurrentAction.getName()) {
                InnerShadow is = new InnerShadow();
                is.setOffsetX(ACTION_LABEL_SHADOW_OFFSET);
                is.setOffsetY(ACTION_LABEL_SHADOW_OFFSET);
                l.setEffect(is);
            }
        });
    }


    public void useDefaultMap () {
        // this is just for testing
        myKeyMap.put(KeyCode.A, new Point2D.Double(-1.0, 0.0));
        myKeyMap.put(KeyCode.D, new Point2D.Double(1.0, 0.0));
        myKeyMap.put(KeyCode.W, new Point2D.Double(0.0, 1.0));
        myKeyMap.put(KeyCode.S, new Point2D.Double(0.0, -1.0));
        myKeyMap.put(KeyCode.F, new Point2D.Double(1.0, 1.0));
        System.out.println("Using Default Keyboard Map");
    }
}
