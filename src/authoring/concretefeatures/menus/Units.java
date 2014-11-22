package authoring.concretefeatures.menus;

import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.UnitCreator;
import authoring.data.PieceData;
import authoring_environment.LibraryView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Units extends Menu{
	
	private static final String NAME = "Units";
	private static final String ITEM1 = "New Units";
	LibraryView myLibrary;
	
	public Units(LibraryView library, PieceData pieceData){
		super(NAME);
		myLibrary = library;
		MenuItem unitCreator = new MenuItem(ITEM1);
		
		setAction(unitCreator, pieceData);
		getItems().addAll(unitCreator);
	}
	
	private void setAction(MenuItem item, PieceData pieceData){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				PopupWindow p = new UnitCreator(myLibrary, pieceData);
				p.show();
			}
		});
	}
}