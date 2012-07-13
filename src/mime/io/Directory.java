/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

/**
 * Mimics VB.Net System.IO.Directory class.
 * @author seph
 */
public final class Directory {
    
    /**
     * Creates a new directory thru the specified path.
     * @param directory Path of the directory to be created.
     * @return Directory information class for the created directory.
     */
    public static java.io.File create(String directory) {
        java.io.File _dir=new java.io.File(directory);
        
        try {
            _dir.mkdir(); 
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        return _dir;
    }
    
    /**
     * Deletes the specified directory along with each and every files inside the specified path.
     * @param directory Path of the directory to be deleted.
     * @return True if the directory was successfully deleted, otherwise false.
     */
    public static boolean delete(String directory) {
        boolean _deleted=false;
        
        java.io.File _dir=new java.io.File(directory);
        
        try {
            java.io.File[] _files = _dir.listFiles();
            if (_files!=null) {
                for (java.io.File _file:_files) _file.delete();
            }
            _deleted=_dir.delete();
        }
        catch (Exception ex) {
            _dir=null; System.gc();
            throw new RuntimeException(ex.getMessage());
        }
        
        _dir=null; System.gc();
        
        return _deleted;
    }
    
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
    
    /**
     * Gets all the files inside the specified directory path.
     * @param directory Path of the directory to be red.
     * @return Array of file information class which pertains to each of the files inside the specified directory, otherwise null if something
     * went wrong.
     */
    public static java.io.File[] getFiles(String directory) {
        java.io.File[] _files=null;
        
        java.io.File _dir=new java.io.File(directory);
        
        try {
            _files=_dir.listFiles();
        }
        catch (Exception ex) {
            _dir=null; System.gc();
            throw new RuntimeException(ex.getMessage());
        }
        _dir=null; System.gc();
        
        return _files;
    }
    
}
