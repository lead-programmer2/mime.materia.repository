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
     * Performs file compression / archiving to a certain file.
     * @param filename Path of the file to be compressed.
     * @return File information of the output file, otherwise null if something went wrong.
     */
    public static java.io.File compress(String filename) {
        java.io.File _file=null;
        
        Archiver  _archiver=new Archiver();
        _file = _archiver.archive(filename);
        _archiver.dispose();
        
        return _file; 
    }
    
    /**
     * Performs file compression / archiving to a certain file.
     * @param filename Path of the file to be compressed.
     * @param destination Output archive file destination
     * @return  File information of the output file, otherwise null if something went wrong.
     */
    public static java.io.File compress(String filename, String destination) {
        java.io.File _file=null;
        
        Archiver  _archiver=new Archiver();
        _file = _archiver.archive(filename, destination);
        _archiver.dispose();
        
        return _file;
    }
    
    /**
     * Performs file extraction from a certain compressed file.
     * @param filename Path of the compressed file to be extracted
     * @return Directory information to where the contents of the compressed file will be extracted, otherwise null if something went wrong.
     */
    public static java.io.File decompress(String filename) {
         java.io.File _file=null;
        
        Archiver  _archiver=new Archiver();
        _file = _archiver.extract(filename);
        _archiver.dispose();
        
        return _file;
    }
    
    /**
     * Performs file extraction from a certain compressed file.
     * @param filename Path of the compressed file to be extracted
     * @param destination Output directory path.
     * @return Directory information to where the contents of the compressed file will be extracted, otherwise null if something went wrong.
     */
    public static java.io.File decompress(String filename, String destination) {
        java.io.File _file=null;
        
        Archiver  _archiver=new Archiver();
        _file = _archiver.extract(filename, destination);
        _archiver.dispose();
        
        return _file;
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
