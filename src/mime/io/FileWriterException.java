/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

/**
 * Exception thrown relative to file stream writing error.
 * @author seph
 */
public class FileWriterException extends RuntimeException {
    
    /**
     * Creates a new instance of FileWriterException.
     */
    public FileWriterException() {
        super();
    }
    
    /**
     * Creates a new instance of FileWriterException.
     * @param message Error message.
     */
    public FileWriterException(String message) {
        super(message);
    }
    
}
