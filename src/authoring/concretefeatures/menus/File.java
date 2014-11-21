package authoring.concretefeatures.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class File extends Menu {

	public File() {
		super("File");
		MenuItem newGame = new MenuItem("New Game");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem exit = new MenuItem("Exit");

		getItems().addAll(newGame, open, save, exit);
	}

	private class NewHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//Restart Game
		}
	}

}
