/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

/**
 * Exception thrown relative to File IO error.
 * @author seph
 */
public class FileUnavailableException extends RuntimeException {
    
    /**
     * Creates a new instance of FileUnavailableException.
     */
    public FileUnavailableException () {
        super();
    }
        
    /**
     * Creates a new instance of FileUnavailableException.
     * @param message Error message
     */
    public FileUnavailableException(String message) {
        super(message);
    }
}
