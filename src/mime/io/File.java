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
     * Creates a file at the specified path.
     * @param filename Path of the file to be created.
     * @return StreamWriter associated with the created file.
     */
    public static StreamWriter create(String filename) {
        StreamWriter sw=new StreamWriter(filename);
        sw.write(""); return sw;
    }
    
    /**
     * Deletes the specified file.
     * @param filename Path of the file to be deleted.
     * @return True if file was successfully deleted, otherwise false.
     */
    public static boolean delete(String filename) {
        boolean _deleted=false;
        java.io.File _file=new java.io.File(filename);
        if (_file.exists()) {
            try {
                _deleted=_file.delete();
            }
            catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
        
        return _deleted;
    }
    
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
