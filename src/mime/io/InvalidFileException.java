/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

/**
 *Exception thrown relative to invalid file accessibility or information errors.
 * @author seph
 */
public class InvalidFileException extends RuntimeException {
    
    /**
     * Creates a new instance of InvalidFileException.
     */
    public InvalidFileException() {
        super();
    }
    
    /**
     * Creates a new instance of InvalidFileException.
     * @param message Error message
     */
    public InvalidFileException(String message) {
        super(message);
    }
    
}
