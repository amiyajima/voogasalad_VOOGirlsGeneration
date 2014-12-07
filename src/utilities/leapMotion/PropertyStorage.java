package utilities.leapMotion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertyStorage{
    Properties myProperties;

    public PropertyStorage(Map<String, String> ... propertyArr){
        myProperties = new Properties();
        for(Map<String, String> propertyMap : propertyArr){
            propertyMap.keySet().forEach(string->myProperties.setProperty(string, propertyMap.get(string)));
        }
      //  myProperties = properties;
        //save()
    }
    
    public void save(File f) throws IOException{
        FileOutputStream os = new FileOutputStream(f);
        
        myProperties.store(os, "gesture and mouse binding");
        
    }
    

}
