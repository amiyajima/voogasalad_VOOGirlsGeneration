package authoring.concretefeatures;

import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;

import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TotalLogicBox extends VBox {
    
    public static final int MULTIPLIER_BOX_WIDTH = 75;
    public static final int STATS_BOX_WIDTH = 120;
    private static final String STYLESHEET = "resources/stylesheets/singlemultiplierbox_layout.css";

    private ChoiceBox<String> myTargetChoice;
    private TextField myStat;
    private VBox myOperationsVBox;
    
    private List<SingleMultiplierBox> myOperationsList;
    
    public TotalLogicBox() {
        getStylesheets().add(STYLESHEET);
        myTargetChoice = new ChoiceBox<String>();
        myTargetChoice.setPrefWidth(MULTIPLIER_BOX_WIDTH);

        myStat = new TextField();
        myStat.setPrefWidth(STATS_BOX_WIDTH);
        
        initStatsModifier(this, myTargetChoice, myStat);
        // Operations
        myOperationsVBox = new VBox();
        initOperationsBox(myOperationsVBox);
    }
    
    public StatsTotalLogic getStatsLogic () {
        String target = myTargetChoice.getValue();
        String stat = myStat.getText();
        List<StatsSingleMultiplier> multiplierLogic = getSingleMultipliers(myOperationsList);
        return new StatsTotalLogic(target, stat, multiplierLogic);
    }
    
    private void initStatsModifier (VBox targetVBox,
                                    ChoiceBox<String> targetChoice, TextField moddedStat) {
        Label targetLabel = new Label("Action target");
        targetChoice.getItems().addAll("actor", "receiver");

        // Particular statistic modified
        moddedStat.setPromptText("Stat to be modified");
        HBox targetAndStatHBox = new HBox();
        targetAndStatHBox.setSpacing(10);
        targetAndStatHBox.getChildren().addAll(targetChoice, moddedStat);
        targetVBox.getChildren().addAll(targetLabel, targetAndStatHBox);
    }

    private void initOperationsBox (VBox operationsBox) {
        Label operationsLabel = new Label("Operations to be performed");
        Button newOperation = new Button("New operation");
        operationsBox.setSpacing(10);
        operationsBox.getChildren().addAll(operationsLabel, newOperation);

        newOperation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                SingleMultiplierBox operation = new SingleMultiplierBox();
                operation.setSpacing(5);
                myOperationsList.add(operation);
                Button delStatBtn = makeDeleteButton(operationsBox, operation);
                
                HBox opEntryBox = new HBox();
                opEntryBox.getChildren().addAll(operation, delStatBtn);
                operationsBox.getChildren().addAll(opEntryBox);
            }
        });
    }
    
    private Button makeDeleteButton (VBox operationsBox, SingleMultiplierBox operation) {
        Button delBtn = new Button("-");
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myOperationsList.remove(operation);
                operationsBox.getChildren().remove(operation);
                operationsBox.getChildren().remove(delBtn);
            }
        });
        return delBtn;
    }

    private List<StatsSingleMultiplier> getSingleMultipliers (List<SingleMultiplierBox> smbList) {
        List<StatsSingleMultiplier> ssmList = new LinkedList<StatsSingleMultiplier>();
        for (SingleMultiplierBox smb : smbList) {
            ssmList.add(smb.getSingleMultipler());
        }
        return ssmList;
    }
}
