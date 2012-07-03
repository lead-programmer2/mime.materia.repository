/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.io.InputStream;
import java.net.URL;

/**
 * Library resource manager.
 * @author seph
 */
public class ResourceManager {
    
    /**
     * Creates a new instance of ResourceManager.
     */
    public ResourceManager() {
        
    }
    
    /**
     * Returns the URL of the specified library resource.
     * @param name
     * @return URL of the specified resource.
     */
    public URL getResource(String name) {
        return getClass().getResource(name);
    }
    
    /**
     * Returns the stream representation of the specified library resource.
     * @param name
     * @return Stream representation of the specified resource.
     */
    public InputStream getResourceAsStream(String name) {
        return getClass().getResourceAsStream(name);
    }
    
}
