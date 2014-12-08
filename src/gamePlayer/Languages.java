package gamePlayer;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 * Controls the languages used in the game
 * @author Yoonhyung
 *
 */
public class Languages {
    
    public static final String ENGLISH = "resources.languages.English";
    public static final String CHINESE = "resources.languages.Chinese";
    public static final String KOREAN = "resources.languages.Korean";
    public static final String FRENCH = "resources.languages.French";
    
    private ResourceBundle messages;
    private String currentLanguage;
    
    public Languages() {
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }
    
    /**
     * Checks the currently selected language
     */
    @FXML
    public void checkLanguage() {
            if (EnglishCheck.isSelected()) {
                    currentLanguage = ENGLISH;
            }
            if (FrenchCheck.isSelected()) {
                    currentLanguage = FRENCH;
            }
            if (KoreanCheck.isSelected()) {
                    currentLanguage = KOREAN;
            }
            if (ChineseCheck.isSelected()) {
                    currentLanguage = CHINESE;
            }
            updateLanguages();
    }
    
    /**
     * Updates the game labels according to the selected language in settings
     */
    public void updateLanguages() {
            messages = ResourceBundle.getBundle(currentLanguage);
            showScoreButton.setText(messages.getString("SCORE"));
            controlTab.setText(messages.getString("CONTROL"));
            statsTab.setText(messages.getString("STATS"));
            inventoryTab.setText(messages.getString("INVENTORY"));
            gameMenu.setText(messages.getString("GAMEMENU"));
            restartMenu.setText(messages.getString("RESTART"));
            saveMenu.setText(messages.getString("SAVE"));
            loadMenu.setText(messages.getString("LOAD"));
            mainMenu.setText(messages.getString("MAIN"));
            settingsMenu.setText(messages.getString("SETTINGS"));
            exitMenu.setText(messages.getString("EXIT"));
    }
    
    
    
    
    
//    public void useChinese(){
//        Locale cLocale = new Locale("cn","CN");
//        
//    }
}
