package authoring.concretefeatures;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.UIspecs;

public class ActionCreator extends PopupWindow{

	public static final int HEIGHT = 400;
	public static final int WIDTH = 400;
	public static final String NAME = "Action Creator";
	
	private List<SingleMultiplierBox> operationsList;
	
	public ActionCreator(){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		initialize();
	}
	
	@Override
	protected void initialize() {
		operationsList = new ArrayList<>();
		
		VBox box = new VBox();
                box.setPadding(UIspecs.allPadding);
                box.setSpacing(5);
                
		VBox name = new VBox();
                name.setPadding(UIspecs.topRightPadding);		
		VBox target = new VBox();
		VBox operations = new VBox();
		VBox conclusion = new VBox();
		
		HBox targetAndStat = new HBox();
		
		//Action name
		Label nameLabel = new Label("Action name");
		TextField nameField = new TextField();
		nameField.setPromptText("Enter action name");
		
		name.getChildren().addAll(nameLabel, nameField);
		
		//Set the Range
		Button setRange=new Button("Set Range...");
		setRange.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				PopupWindow rangeEditor = new RangeEditor();
				rangeEditor.show();
			}
			
		});
		
		//Target of the action
		Label targetLabel = new Label("Action target");
		ChoiceBox<String> targetChoice = new ChoiceBox<>();
		targetChoice.getItems().addAll("Self", "Other");
		
		//Particular statistic modified
		TextField moddedStat = new TextField();
		moddedStat.setPromptText("Stat to be modified");
		
		targetAndStat.getChildren().addAll(targetChoice, moddedStat);
		
		target.getChildren().addAll(targetLabel, targetAndStat);
		
		//Operations
		Label operationsLabel = new Label("Operations to be performed");
		Button newOperation = new Button("New operation");
		operations.getChildren().addAll(operationsLabel, newOperation);
		
		newOperation.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent click) {
				SingleMultiplierBox operation = new SingleMultiplierBox();
				operationsList.add(operation);
				operations.getChildren().add(operation);			
			}	
		});
		
		Button create = new Button("Create new action");
		
		box.getChildren().addAll(name, setRange, target, moddedStat, operations, conclusion, create);

		setScene(new Scene(box));
	}
}
