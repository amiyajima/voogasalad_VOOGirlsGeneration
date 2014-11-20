package gamePlayer;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import GameEngine.GameModel;
import java.util.Map;
import javafx.stage.Stage;

public class ViewComponentFactory {

    public static final String COMPONENT_LOCATION = "resources.componentNames";; 
    public static final String COMPONENT_SOURCE = "gamePlayer.";

    private ResourceBundle componentBundle;

    private Stage myStage;
    private GameModel myGameModel;
    private GameDisplay myGameDisplay;

    private Map<String, ViewComponent> myComponents;

    public ViewComponentFactory(Stage stage, GameModel model, GameDisplay gameDisplay){
        myStage = stage;
        myGameModel = model;
        myGameDisplay = gameDisplay;

        componentBundle = ResourceBundle.getBundle(COMPONENT_LOCATION);
        myComponents= new HashMap<>();
    }

    public ViewComponent makeComponent (String name){

        ViewComponent component= null;

        Class<?> c = null;

        try{

            c = Class.forName(COMPONENT_SOURCE + componentBundle.getString(name));
            component = (ViewComponent) c.getDeclaredConstructor(Stage.class, GameModel.class,GameDisplay.class)
                    .newInstance(myStage, myGameModel, myGameDisplay);

            myGameModel.addObserver(component);
            myComponents.put(name, component);

        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | MissingResourceException | SecurityException e) {

        }

        return component;

    }

    public void setObserver(ViewComponent observable, List<String> observers){
        observers.forEach(dependency-> {if(myComponents.containsKey(dependency)){
            observable.addObserver(myComponents.get(dependency));
        }
        });
    }
    
    public GameModel getGameModel(){
        return myGameModel;
    }
    
    public GameDisplay getGameDisplay(){
        return myGameDisplay;
    }

}
