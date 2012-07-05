/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.util.prefs.Preferences;
import mime.resources.ResourceManager;

/**
 * Global library class.
 * @author seph
 */
public class Mime {
    
    /**
     * Gets the registry key value associated with the specified application, application section and key.
     * @param appname Application name
     * @param section Registry section
     * @param keyvalue Registry key
     * @param defaulvalue Default value to returns when there is no value associated with the specified key.
     * @return Value stored in the system registry pertaining to the specified registry key, otherwise returns the default string value specified if 
     * there is no such value found associated with the specified registry key.
     */
    public static String getSetting(String appname, String section, String key, String defaulvalue) {
        String _setting=defaulvalue;
        
        Preferences _preferences=null;
        
        try {
            _preferences=Preferences.userNodeForPackage(Mime.class);
            String _key=appname + "/" + section + "/" + key;
            _setting=_preferences.get(_key, defaulvalue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        return _setting;
    }
    
    /**
     * Returns the library resources.
     */
    public static final ResourceManager resources=new ResourceManager();

    /**
     * Sets the registry key value associated with the specified application, application section and key.
     * @param appname Application name
     * @param section Registry section
     * @param key Registry key
     * @param value Registry key value
     */
    public static void setSetting(String appname, String section, String key, String value) {
        Preferences _preferences=null;
        
        try {
            _preferences=Preferences.userNodeForPackage(Mime.class);
            String _key=appname + "/" + section + "/" + key;
            _preferences.put(_key, value);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
}

