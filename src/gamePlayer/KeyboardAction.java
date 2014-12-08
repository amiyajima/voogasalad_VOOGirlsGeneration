package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    Map<KeyCode, Point2D> movementKeyMap;

    public KeyboardAction () {
        myActionIndex = 0;
        myActions = new ArrayList<Action>();

//        //this is my default map...
        movementKeyMap = new HashMap<KeyCode, Point2D>();
        movementKeyMap.put(KeyCode.W, new Point2D.Double(0.0, 1.0));
        movementKeyMap.put(KeyCode.S, new Point2D.Double(0.0, -1.0));
    }

    public void setActionKeyControl (ViewController vc) {
        System.out.println("KeyboardAction ON");
        
        // using the movement key map to determine which keys=up&down
//        myCurrentPlayer = vc.getGame().getCurrentPlayer();
//       if (((HumanPlayer)myCurrentPlayer).getMovementKeyMap()!=null){
//           movementKeyMap = 
//       };
        
        if ((vc.getActivePiece() != null) && (vc.getGame().getCurrentPlayer().getType().equals("Human")))  {
            Piece activePiece = vc.getActivePiece();
            myActions = activePiece.getActions();

            vc.getGridPane().requestFocus();
            
            vc.getGridPane().setOnKeyPressed(new EventHandler<KeyEvent>() {
                Set<KeyCode> movementKeyList = movementKeyMap.keySet();

                @Override
                public void handle (KeyEvent key) {

                    // Do action here
                    if (key.getCode() == KeyCode.F) {
                        System.out.println("Selected action with key");
                        vc.bindAction(myCurrentAction);
                    }

                    // Select action here
                    for (KeyCode kc : movementKeyList) {
                        if (key.getCode() == kc) {
                            int newActionIndex = myActionIndex - (int) movementKeyMap.get(kc).getY();
                            if ((newActionIndex >= 0) & (newActionIndex < myActions.size())) {
                                myActionIndex = newActionIndex;
                            }

                            myCurrentAction = myActions.get(myActionIndex);
                            System.out.println("myActionIndex: " + myActionIndex);
                        }
                    }

                    markAction(vc);
                }
            });
        }
    }

    public void markAction (ViewController vc) {
        unmarkAction(vc);
        vc.getcontrolPane().getChildren().forEach(label -> {
            Label l = (Label) label;
            if (l.getText() == myCurrentAction.toString()) {
                InnerShadow is = new InnerShadow();
                is.setOffsetX(ACTION_LABEL_SHADOW_OFFSET);
                is.setOffsetY(ACTION_LABEL_SHADOW_OFFSET);
                l.setEffect(is);
            }
        });
    }

    public void unmarkAction (ViewController vc) {
        vc.getcontrolPane().getChildren().forEach(label -> {
            label.setEffect(null);
        });
    }
}
