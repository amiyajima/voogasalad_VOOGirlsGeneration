package authoring.concretefeatures;

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
import authoring_environment.GridView;

public class RangeEditor extends PopupWindow{
	private static final int MIN_TILE_SIZE=50;

	private static final int RANGE_EDITOR_HEIGHT = 800;
	private static final int RANGE_EDITOR_WIDTH = 600;
	private static final String NAME = "Range Editor";
	
	private int myGridSizeNumber;
	private int myGridWidth=RANGE_EDITOR_WIDTH-30;
	private int myTileSize=40;
	
	public RangeEditor(){
		setHeight(RANGE_EDITOR_HEIGHT);
		setWidth(RANGE_EDITOR_WIDTH);
		setTitle(NAME);
		initialize();
	}
	@Override
	protected void initialize() {
		VBox box= new VBox();
		
		VBox selection=new VBox();
		selection.setMinHeight(50);
		VBox sizeChooser=new VBox();
		sizeChooser.setMinHeight(50);


		//Range Selections
		Label targetLabel = new Label("Select Range");
		ChoiceBox<String> targetChoice = new ChoiceBox<>();
		targetChoice.getItems().addAll("Column","Row","Column&Row Cross",
										"Radius","Custom");
		selection.getChildren().addAll(targetLabel,targetChoice);
		
		//generate default grid
//		GridView grid=new GridView(myGridWidth, myGridWidth, myTileSize);

		//Choose the Size
		Label nameLabel = new Label("Radius");
		TextField radius = new TextField();
		radius.setMaxWidth(50);
		Button enter=new Button("Enter");
		enter.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				myGridSizeNumber=Integer.parseInt(radius.getText())*2+1;
				int calculatedTileSize=myGridWidth/myGridSizeNumber;
				
				myTileSize=(calculatedTileSize<MIN_TILE_SIZE)? MIN_TILE_SIZE:calculatedTileSize;
				
				box.getChildren().clear();
				GridView grid=new GridView(myGridWidth,myGridWidth,myTileSize);
				grid.update(myGridSizeNumber, myGridSizeNumber,myTileSize);
				box.getChildren().addAll(selection,sizeChooser,grid);
			}	
		});
		sizeChooser.getChildren().addAll(nameLabel,radius,enter);
		

		
		box.getChildren().addAll(selection,sizeChooser);
		setScene(new Scene(box));
		
		
		

	}
	

}
