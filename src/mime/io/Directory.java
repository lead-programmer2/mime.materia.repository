/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

/**
 * Mimics VB.Net System.IO.Directory class.
 * @author seph
 */
public class Directory {
    
        /**
     * Gets whether the specified directory exists or not.
     * @param directory
     * @return True if the directory exist, otherwise false.
     */
    public static boolean exists(String directory) {
        boolean _exists=false;
        
        java.io.File _file=new java.io.File(directory);
        _exists=(_file.exists() && _file.isDirectory());
        _file=null; System.gc();
        
        return _exists;
    }
    
}
