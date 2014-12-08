package gamePlayer;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * Controls the languages used in the game
 * @author Yoonhyung
 *
 */
public class Languages {
    
    public static final String DEFAULT_LANGUAGE = "resources.languages.English";
//    public static final String CHINESE = "resources.languages.Chinese";
//    public static final String KOREAN = "resources.languages.Korean";
//    public static final String FRENCH = "resources.languages.French";
    
    private ResourceBundle messages;
    private String currentLanguage;
    private Button showScoreButton;
    private MenuButton gameMenu;
    private TabPane tabPane;
    private AnchorPane languagesPane;

    
    public Languages(AnchorPane  ap, TabPane tp, MenuButton mb, Button b) {
        showScoreButton = b;
        gameMenu = mb;
        tabPane = tp;
        languagesPane = ap;
        currentLanguage = DEFAULT_LANGUAGE;
    }
    
    /**
     * Checks the currently selected language
     */
    public void findCurrentLanguage() {
        for (Node n: languagesPane.getChildren()){
            if ( ((CheckBox)n).isSelected()){
                currentLanguage = n.getId();
            }
        }
            updateLabels();
    }

    /**
     * Updates the game labels according to the selected language in settings
     */
    public void updateLabels() {
            messages = ResourceBundle.getBundle(currentLanguage);
            showScoreButton.setText(messages.getString(showScoreButton.getId()));
            gameMenu.setText(messages.getString(gameMenu.getId()));
            for (Tab t: tabPane.getTabs()){
                t.setText(messages.getString(t.getId()));
            }
            for (MenuItem i: gameMenu.getItems()){
                i.setText(messages.getString(i.getId()));
            }
    }
}
