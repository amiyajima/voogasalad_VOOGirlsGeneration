package utilities.leapMotion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertyStorage {

    public static final String ERROR_MESSAGE = "error saving file";
    public static final String COMMENT_STRING = "gesture and mouse binding";

    private Properties myProperties;

    public PropertyStorage (Map<String, String>... propertyArr) {
        myProperties = new Properties();
        for (Map<String, String> propertyMap : propertyArr) {
            propertyMap.keySet().forEach(
                    string -> myProperties.setProperty(string, propertyMap.get(string)));
        }

    }

    public void save (File f) {
        FileOutputStream os;
        if (!(f == null)) {
            try {
                os = new FileOutputStream(f);
                myProperties.store(os, COMMENT_STRING);
                os.close();
            } catch (IOException e) {
                System.out.println(ERROR_MESSAGE);
            }
        }

    }

}
