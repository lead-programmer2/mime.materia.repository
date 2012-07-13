/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Mimics VB.Net System.IO.StreamReader class.
 * @author seph
 */
public class StreamReader {
    
    private Scanner _reader=null;
    private java.io.File _file;
    private String _charset="";
    
    /**
     * Creates a new instance of StreamReader.
     * @param file Path of the file to be red.
     */
    public StreamReader(String filename) {
        this(filename, "");
    }
    
    /**
     * Creates a new instance of StreamReader.
     * @param file Path of the file to be red.
     * @param charset Character encoding.
     */
    public StreamReader(String filename, String charset) {
        this(new java.io.File(filename), charset);
    }

    /**
     * Creates a new instance of StreamReader.
     * @param file File to be red.
     */
    public StreamReader(java.io.File file) {
        this(file,"");
    }
    
    /**
     * Creates a new instance of StreamReader.
     * @param file File to be red.
     * @param charset Character encoding.
     */
    public StreamReader(java.io.File file, String charset) {
        if (!file.isFile()) throw new InvalidFileException(file.getPath() + " is not a valid file."); 
        if (!file.exists()) throw new FileUnavailableException(file.getPath() + " is not available.");
       
        _file=file; _charset=charset;
    }
    
    /**
     * Closes the stream for the initialized file.
     */
    public void close() {
        try {  
            if (_reader!=null) {
                try {
                    _reader.close();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    _reader=null;
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
            close(); finalize();
            StreamReader _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    private void init() {
        try {
           if (_reader==null) {
               if (_charset.equals(""))  _reader=new Scanner(new FileInputStream(_file));
               else  _reader=new Scanner(new FileInputStream(_file), _charset);
           }
        }
        catch (Exception ex) {
            throw new FileReaderException(ex.getMessage());
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
     * Reads the current line of text to where the reader's cursor resides.
     * @return Text from the reader's current cursor, otherwise null if something went wrong.
     */
    public String read() {
        String _line=null;
        init(); 
        
        try {
            if (_reader.hasNextLine()) _line=_reader.nextLine();
        }
        catch (Exception ex) {
            throw new FileReaderException(ex.getMessage());
        }
        
        return _line;
    }
    
    /**
     * Gets all the text contents of the initialized file.
     * @return Interpreted contents of file.
     */
    public String realAll() {
        StringBuilder _builder=new StringBuilder();
        close(); init();
        
         try {
             String _newline=System.getProperty("line.separator");
             if (_reader.hasNextLine())  _builder.append(_reader.nextLine());
             while (_reader.hasNextLine()) _builder.append(_newline + _reader.nextLine());
        }
        catch (Exception ex) {
            throw new FileReaderException(ex.getMessage());
        }
        
        return _builder.toString();
    }
    
}
