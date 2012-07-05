/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

/**
 * Mimics VB.Net Application shared class.
 * @author seph
 */
public class Application {
    
    /**
     * Gets the current application directory path.
     * @return Application directory path
     */
    public static String startUpPath() {
        java.io.File _file=new java.io.File("");
        return _file.getAbsolutePath();
    }
     
}
