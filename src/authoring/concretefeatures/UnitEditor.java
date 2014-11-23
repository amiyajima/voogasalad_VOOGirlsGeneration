package authoring.concretefeatures;

import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import authoring.abstractfeatures.PopupWindow;

/**
 * @author Martin Tamayo, Jennie Ju
 * 
 * GUI component for editing the stats of a given unit.
 * This can be accessed by clicking on the hyperlink of
 * the unit's name in the LibraryView of the game
 * authoring environment.
 */
public class UnitEditor extends PopupWindow {
	
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Unit Editor";
	private Piece myUnit;
	
	private Stats myStats;
	private List<Action> myActions;
	
	/**
	 * Constructor that sets the dimensions of the UnitEditor
	 * GUI component and initializes it.
	 * 
	 * @param unit : Piece class of the unit to edit.
	 */
	public UnitEditor(Piece unit){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		myUnit = unit;
		
		myStats = myUnit.getStats();
		myActions = myUnit.getActions();
		
		initialize();
	}
	
	@Override
	protected void initialize() {
		ScrollPane root = new ScrollPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add(STYLESHEET);
		
		VBox mainVBox = new VBox();
		
		Button setStatsBtn = new Button("Set Stats...");
		setStatsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StatsTotalEditor edit = new StatsTotalEditor(myStats);
				edit.show();
			}
		});
		
		mainVBox.getChildren().addAll(setStatsBtn);
		root.setContent(mainVBox);
		setScene(scene);
	}
}