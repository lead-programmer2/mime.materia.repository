/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

/**
 * Exception thrown relative to file stream reader errors.
 * @author seph
 */
public class FileReaderException extends RuntimeException {
    
    /**
     * Creates a new instance of FileReaderException.
     */
    public FileReaderException() {
        super();
    }
    
    /**
     * Creates a new instance of FileReaderException.
     * @param message Error message.
     */
    public FileReaderException(String message) {
        super(message);
    }
    
}
