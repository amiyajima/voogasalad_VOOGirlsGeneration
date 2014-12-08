package gamePlayer;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    public Language() {
//        Locale aLocale = new Locale("en","US");
        Locale currentLocale = new Locale("en", "US");
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }
    
    public void useChinese(){
        Locale cLocale = new Locale("cn","CN");
        
    }
}
