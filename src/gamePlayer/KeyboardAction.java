package gamePlayer;

import gamedata.action.Action;
import gameengine.player.HumanPlayer;
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
 * @author Yoonhyung Choi
 *
 */
public class KeyboardAction {
    private static final double ACTION_LABEL_SHADOW_OFFSET = 6.0;
    private static final String TYPE_HUMAN = "Human";
    private List<Action> myActions;
    private Action myCurrentAction;
    private int myActionIndex;
    private Map<KeyCode, Point2D> myKeyMap;

    public KeyboardAction () {
        myActionIndex = 0;
        myActions = new ArrayList<Action>();
        myKeyMap = new HashMap<KeyCode, Point2D>();
    }

    /**
     * 
     * myKeyMap (which maps up,down,left,right,select movements to keycodes) is passed by the game
     * engine. This method creates key event handlers so that when such a keycode is
     * pressed, its corresponding movement is implemented within the Control Tab.
     * Movement is "moving the cursor" and "selecting with cursor" with the keyboard.
     * Here, the selection is defined as Point2D Double (1.0,1.0).
     * 
     * @param myKeyMap
     * @param gameScene
     */
    public void setActionKeyControl (ViewController vc) {

        if ((vc.getActivePiece() != null) &&
            (vc.getGame().getCurrentPlayer().getType().equals(TYPE_HUMAN))) {

            myKeyMap = ((HumanPlayer) vc.getGame().getCurrentPlayer()).getMovementKeyMap();
            myActions = vc.getActivePiece().getActions();

            vc.getGridPane().requestFocus();
            vc.getGridPane().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle (KeyEvent key) {
                    for (KeyCode mkc : myKeyMap.keySet()) {
                        if (key.getCode() == mkc) {

                            if (myKeyMap.get(mkc).equals(new Point2D.Double(1.0, 1.0))) {
                                // Do action here
                                vc.bindAction(myCurrentAction);
                            }

                            else {
                                // Select action here
                                int newActionIndex = myActionIndex - (int) myKeyMap.get(mkc).getY();
                                if ((newActionIndex >= 0) & (newActionIndex < myActions.size())) {
                                    myActionIndex = newActionIndex;
                                }

                                myCurrentAction = myActions.get(myActionIndex);
                            }
                        }
                    }
                    markAction(vc);
                }
            });
        }
    }

    private void markAction (ViewController vc) {
        vc.getcontrolPane().getChildren().forEach(label -> {
            // first, unmark previous action
            label.setEffect(null);
            if (((Label) label).getText() == myCurrentAction.toString()) {
                // now, mark currently selected action
                InnerShadow mark = new InnerShadow();
                mark.setOffsetX(ACTION_LABEL_SHADOW_OFFSET);
                mark.setOffsetY(ACTION_LABEL_SHADOW_OFFSET);
                label.setEffect(mark);
            }
        });
    }
}
