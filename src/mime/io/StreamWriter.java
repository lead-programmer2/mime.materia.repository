/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Mimics VB.Net System.IO.StreamWriter class.
 * @author seph
 */
public class StreamWriter {
    
    private BufferedWriter _writer=null;
    private java.io.File _file;
    private boolean _append=true;
    
    /**
     * Creates a new instance of StreamWriter
     * @param filename Path of the file for the output.
     */
    public StreamWriter(String filename) {
        this(new java.io.File(filename));
    }
    
    /**
     * Creates a new instance of StreamWriter
     * @param filename Path of the file for the output.
     * @param append Determines whether to append written contents into the file. If 
     * append is set to True and the file is existing, the written contents will just be
     * appended / added into the existing file contents. Otherwise, written contents will
     * replace the existing file contents.
     */
    public StreamWriter(String filename, boolean append) {
        this(new java.io.File(filename), append);
    }
    
    /**
     * Creates a new instance of StreamWriter
     * @param file File to be written
     */
    public StreamWriter(java.io.File file) {
        this(file, true);
    }
    
    /**
     * Creates a new instance of StreamWriter
     * @param file File to be written
     * @param append Determines whether to append written contents into the file. If 
     * append is set to True and the file is existing, the written contents will just be
     * appended / added into the existing file contents. Otherwise, written contents will
     * replace the existing file contents.
     */
    public StreamWriter(java.io.File file, boolean append) {
        if (!file.isFile()) throw new InvalidFileException(file.getPath() + " is not a valid file.");
        _file=file; _append=append;
    }
    
    /**
     * Closes the stream for the initialized file.
     */
    public void close() {
        try {  
            if (_writer!=null) {
                try {
                    _writer.close();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    _writer=null;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current class already called this method, check isDispose
     * method.
     */
    public void dispose() {
         _isdisposed=true;
        try {
            close();
            finalize();
            StreamWriter _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Gets the initialized file for the writer.
     * @return Path of the file initialized in the writer.
     */
    public String fileName() {
        return _file.getPath();
    }
    
    /**
     * Gets whether contents to be written on the file will be appended or not.
     * @return True if contents to be written will be appended, otherwise false.
     */
    public boolean getAppend() {
        return _append;
    }
    
    private void init() {
        try {
            if (_writer==null) _writer=new BufferedWriter(new FileWriter(_file));
        }
        catch (Exception ex) {
            throw new FileWriterException(ex.getMessage());
        }
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current class has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Sets whether contents to be written on the file will appended or not.
     * @param append 
     */
    public void setAppend(boolean  append) {
        _append=append;
    }
    
    /**
     * Writes the specified contents into the initialized file.
     * @param contents Contents to be written.
     * @return True if specified contents were written successfully, otherwise false.
     */
    public boolean write(String contents) {
        boolean _written=false;
        if (!_file.exists()) {
            try {
                _file.createNewFile();
            }
            catch (Exception ex) {
                throw new FileWriterException(ex.getMessage());
            }
        }
        
        init();
        
        try {
            if (_append) _writer.append(contents);
            else _writer.write(contents);
            _written=true;
        }
        catch (Exception ex) {
            throw new FileWriterException(ex.getMessage());
        }
        
        return _written;
    }
    
    /**
     * Writes the specified contents into a new line in the specified file, appending the existing file contents.
     * @param contents Contents to be written
     * @return True if specified contents were written successfully, otherwise false.
     */
    public boolean writeLine(String contents) {
        boolean _written=false;
        if (!_file.exists()) {
            try {
                _file.createNewFile();
            }
            catch (Exception ex) {
                throw new FileWriterException(ex.getMessage());
            }
        }
        
        init();
        
        try {
            _writer.newLine();
            _writer.append(contents);
            _written=true;
        }
        catch (Exception ex) {
            throw new FileWriterException(ex.getMessage());
        }
        
        return _written;
    }
    
}
