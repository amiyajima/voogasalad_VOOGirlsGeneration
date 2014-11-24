package authoring.concretefeatures.menus;


import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.GameCreator;
import authoring.concretefeatures.IndividualUnitEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
// import authoring.concretefeatures.GameCreator;


public class File extends Menu {

    public File () {
        super("File");
        MenuItem newGame = new MenuItem("New Game");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");

        newGame.setOnAction(new NewHandler());
        open.setOnAction(new OpenHandler());
        save.setOnAction(new SaveHandler());
        exit.setOnAction(new ExitHandler());

        getItems().addAll(newGame, open, save, exit);
    }

    private class NewHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent event) {
            // Restart Game
             PopupWindow p = new GameCreator();
             p.show();
            
        }
    }

    private class OpenHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent event) {
            // Opens a New Game

        }
    }

    private class SaveHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent event) {
            // Saves a New Game
        }
    }

    private class ExitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent event) {
            // Saves a New Game
        }
    }

}