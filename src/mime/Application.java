/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

/**
 * Mimics VB.Net Application shared class.
 * @author seph
 */
public final class Application {
    
    /**
     * Gets the current application directory path.
     * @return Application directory path
     */
    public static String startUpPath() {
        java.io.File _file=new java.io.File("");
        String _path=_file.getAbsolutePath();
        String _parsedpath=_path;
        if (_path.startsWith("/")) _parsedpath = _path.substring(2);
        return _parsedpath;
    }
     
}
