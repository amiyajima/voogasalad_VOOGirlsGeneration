package authoring.createedit;

import authoring.abstractfeatures.PopupWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class GamePropertiesEditor extends PopupWindow{

	private static final int DEFAULT_PLAYER_NUMBER = 1;
	private static final String DEFAULT_GRID_SHAPE = "Square Grid";
	
	private int myNumPlayer=DEFAULT_PLAYER_NUMBER;
	private String myGridShape=DEFAULT_GRID_SHAPE;
	
	public GamePropertiesEditor(){
		initialize();
	}

	protected void initialize() {
		 VBox box = new VBox();
		 Scene scene = new Scene(box,300,300);
		 
	     Label numPlayer =new Label("Number of Player:");
//	     numPlayer.setCenterShape(true);
		 TextField number=new TextField();
		 
		 Label gridShape=new Label("Grid Shape:");
		 ChoiceBox<String> shape=new ChoiceBox<String>();
		 shape.getItems().addAll(DEFAULT_GRID_SHAPE,"Hexagon Grid","Circle Grid");
		 
		 Button select=new Button("Select");
		 select.setOnAction(new selectHandler(this,number,shape));
		 
		 box.getChildren().addAll(numPlayer,number,gridShape,shape,select);
		 
		 this.setScene(scene);
		
	}
	
	private class selectHandler implements EventHandler<ActionEvent>{
		TextField numPlayer;
		ChoiceBox<String> gridShape;
		GamePropertiesEditor editorWindow;
		public selectHandler(GamePropertiesEditor current, TextField number, ChoiceBox<String> shape) {
			numPlayer=number;	
			gridShape=shape;
			editorWindow=current;
		}
		@Override
		public void handle(ActionEvent event) {
			try { 
				myNumPlayer=Integer.parseInt(numPlayer.getText()); 
				
				System.out.println(myNumPlayer);
				
		    } catch(NumberFormatException e) { 
		        System.out.println("Type in an integer please:) "
		        					+ "Or you will play alone :("); 
		    }
			myGridShape=gridShape.getValue().toString();
			
			System.out.println(myGridShape);
			
			editorWindow.close();
			
		}
		
	}
	
	public int getNumPlayer(){
		return myNumPlayer;
	}
	
	public String getGridShape(){
		return myGridShape;
	}

}
