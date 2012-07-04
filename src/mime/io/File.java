/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

/**
 * Mimics VB.Net System.IO.File class.
 * @author seph
 */
public class File {
    
    /**
     * Gets whether the specified file exists or not.
     * @param filename 
     * @return True if the file exist, otherwise false.
     */
    public static boolean exists(String filename) {
        boolean _exists=false;
        
        java.io.File _file=new java.io.File(filename);
        _exists=(_file.exists() && _file.isFile());
        _file=null; System.gc();
        
        return _exists;
    }
    
}
